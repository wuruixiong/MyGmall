package wrx.web.gmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "wrx.web.gmall.otherservice.mapper")
public class GmallOtherServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallOtherServiceApplication.class, args);
    }

}
