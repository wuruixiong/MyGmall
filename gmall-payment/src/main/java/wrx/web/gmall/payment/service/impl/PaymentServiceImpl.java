package wrx.web.gmall.payment.service.impl;

import com.alibaba.dubbo.config.annotation.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import wrx.web.gmall.bean.PaymentInfo;
import wrx.web.gmall.payment.mapper.PaymentInfoMapper;
import wrx.web.gmall.service.PaymentService;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentInfoMapper paymentInfoMapper;

    @Override
    public void savePaymentInfo(PaymentInfo paymentInfo) {
        paymentInfoMapper.insertSelective(paymentInfo);
    }


    @Override
    public void updatePayment(PaymentInfo paymentInfo) {
        String orderSn = paymentInfo.getOrderSn();

    }


}
