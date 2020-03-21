package wrx.web.gmall.gware.mq;

import com.alibaba.fastjson.JSON;

import wrx.web.gmall.gware.bean.OmsOrder;

import wrx.web.gmall.gware.bean.OmsOrderItem;
import wrx.web.gmall.gware.bean.WareOrderTaskDetail;
import wrx.web.gmall.gware.util.ActiveMQUtil;
import wrx.web.gmall.gware.bean.WareOrderTask;
import wrx.web.gmall.gware.enums.TaskStatus;
import wrx.web.gmall.gware.mapper.WareOrderTaskDetailMapper;
import wrx.web.gmall.gware.mapper.WareOrderTaskMapper;
import wrx.web.gmall.gware.mapper.WareSkuMapper;
import wrx.web.gmall.gware.service.GwareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.*;

/**
 * @param
 * @return
 */
@Component
public class WareConsumer {

    @Autowired
    WareOrderTaskMapper wareOrderTaskMapper;

    @Autowired
    WareOrderTaskDetailMapper wareOrderTaskDetailMapper;

    @Autowired
    WareSkuMapper wareSkuMapper;

    @Autowired
    ActiveMQUtil activeMQUtil;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    GwareService gwareService;

    @JmsListener(destination = "ORDER_PAY_QUEUE", containerFactory = "jmsQueueListener")
    public void receiveOrder(TextMessage textMessage) throws JMSException {
        String orderTaskJson = textMessage.getText();

        /***
         * 转化并保存订单对象
         */
        OmsOrder orderInfo = JSON.parseObject(orderTaskJson, OmsOrder.class);

        // 将order订单对象转为订单任务对象
        WareOrderTask wareOrderTask = new WareOrderTask();
        wareOrderTask.setConsignee(orderInfo.getReceiverName());
        wareOrderTask.setConsigneeTel(orderInfo.getReceiverPhone());
        wareOrderTask.setCreateTime(new Date());
        wareOrderTask.setDeliveryAddress(orderInfo.getReceiverDetailAddress());
        wareOrderTask.setOrderId(orderInfo.getId());
        ArrayList<WareOrderTaskDetail> wareOrderTaskDetails = new ArrayList<>();

        // 打开订单的商品集合
        List<OmsOrderItem> orderDetailList = orderInfo.getOmsOrderItems();
        for (OmsOrderItem orderDetail : orderDetailList) {
            WareOrderTaskDetail wareOrderTaskDetail = new WareOrderTaskDetail();

            wareOrderTaskDetail.setSkuId(orderDetail.getProductSkuId());
            wareOrderTaskDetail.setSkuName(orderDetail.getProductName());
            wareOrderTaskDetail.setSkuNum(orderDetail.getProductQuantity());
            wareOrderTaskDetails.add(wareOrderTaskDetail);

        }
        wareOrderTask.setDetails(wareOrderTaskDetails);
        wareOrderTask.setTaskStatus(TaskStatus.PAID);
        gwareService.saveWareOrderTask(wareOrderTask);

        textMessage.acknowledge();

        // 检查该交易的商品是否有拆单需求
        List<WareOrderTask> wareSubOrderTaskList = gwareService.checkOrderSplit(wareOrderTask);// 检查拆单

        // 库存削减
        if (wareSubOrderTaskList != null && wareSubOrderTaskList.size() >= 2) {
            for (WareOrderTask orderTask : wareSubOrderTaskList) {
                gwareService.lockStock(orderTask);
            }
        } else {
            gwareService.lockStock(wareOrderTask);
        }


    }

}
