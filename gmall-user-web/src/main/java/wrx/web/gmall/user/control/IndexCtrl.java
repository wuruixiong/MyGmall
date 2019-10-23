package wrx.web.gmall.user.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexCtrl {

    @RequestMapping("/index")
    public String index () {
        return "hello g-mall";
    }

}
