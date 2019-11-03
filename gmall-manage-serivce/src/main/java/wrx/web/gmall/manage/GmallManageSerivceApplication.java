package wrx.web.gmall.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "wrx.web.gmall.manage.mapper")
public class GmallManageSerivceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallManageSerivceApplication.class, args);
    }

}
