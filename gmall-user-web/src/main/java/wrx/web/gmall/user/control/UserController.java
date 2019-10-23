package wrx.web.gmall.user.control;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wrx.web.gmall.bean.UmsMember;
import wrx.web.gmall.bean.UmsMemberReceiveAddress;
import wrx.web.gmall.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Reference
    UserService userService;

    @RequestMapping("user/getAll")
    @ResponseBody
    public List<UmsMember> getAllUsers () {
        List<UmsMember> umsMembers = userService.getAllUsers();
        return umsMembers;
    }

    @RequestMapping("user/getAddressById")
    // http://127.0.0.1:8080/user/getAddressById?memberId=1
    @ResponseBody
    public List<UmsMemberReceiveAddress> getAllAddress (String memberId) {
        List<UmsMemberReceiveAddress> umsMembers = userService.selectById(memberId);
        return umsMembers;
    }


    @RequestMapping("user/index")
    @ResponseBody
    public String index () {
        return "hello my user";
    }

}
