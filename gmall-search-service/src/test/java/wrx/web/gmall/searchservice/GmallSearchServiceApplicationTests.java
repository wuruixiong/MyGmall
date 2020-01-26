package wrx.web.gmall.searchservice;

import com.alibaba.dubbo.config.annotation.Reference;
import io.searchbox.client.JestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wrx.web.gmall.service.SkuService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallSearchServiceApplicationTests {

    @Reference
    SkuService skuService;// 查询mysql

    @Autowired
    JestClient jestClient;

    @Test
    public void contextLoads() {

        System.out.print("");
        System.out.print("");
        System.out.print("");

    }

}
