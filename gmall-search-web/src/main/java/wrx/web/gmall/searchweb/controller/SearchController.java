package wrx.web.gmall.searchweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import wrx.web.gmall.bean.PmsSearchParam;
import wrx.web.gmall.bean.PmsSearchSkuInfo;
import wrx.web.gmall.service.SearchService;
import wrx.web.gmall.service.SkuService;

import java.util.List;

@Controller
public class SearchController {

    @Reference
    SearchService searchService;

    // 在list.html界面的搜素框中输入，“硅谷”，“华为” 等数据
    // 页面刷新出搜素内容，并出现搜素关键字高亮
    @RequestMapping("list.html")
    public String list(PmsSearchParam pmsSearchParam, ModelMap modelMap){// 三级分类id、关键字、

        // 调用搜索服务，返回搜索结果
        List<PmsSearchSkuInfo> pmsSearchSkuInfos = searchService.list(pmsSearchParam);
        modelMap.put("skuLsInfoList",pmsSearchSkuInfos);

        return "list";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
