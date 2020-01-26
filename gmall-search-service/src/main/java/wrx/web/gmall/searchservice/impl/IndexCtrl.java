package wrx.web.gmall.searchservice.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wrx.web.gmall.service.SkuService;

@RestController
public class IndexCtrl {

    @Reference
    SkuService skuService;

    @RequestMapping("/index")
    public String index () {
        return "hello g-mall";
    }

}
