package wrx.web.gmall.searchservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.JsonObject;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wrx.web.gmall.bean.PmsSearchSkuInfo;
import wrx.web.gmall.bean.PmsSkuInfo;
import wrx.web.gmall.service.SkuService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallSearchServiceApplicationTests2 {

    @Autowired
    JestClient jestClient;

    @Test
    public void contextLoads() throws IOException {
        postBean();
        //deleteBean();
    }

    public void postBean() {
        Book article = new Book();
        article.setId(1);
        article.setAuthor("Tom");
        article.setContent("hello Tom!");
        article.setTitle("好消息");
        Index update = new Index.Builder(article).index("jest").type("news").id("101").build();
        try {
            JestResult result = jestClient.execute(update);
            System.out.println("数据修改:"+ result.isSucceeded());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteBean() {

        Delete delete = new Delete.Builder("101").index("jest").type("news").build();
        JestResult result = null;
        try {
            result = jestClient.execute(delete);
            System.out.println("数据删除:"+ result.isSucceeded());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
