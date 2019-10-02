package wrx.web.gmall.user.service;

import org.springframework.stereotype.Service;
import wrx.web.gmall.user.bean.UmsMember;
import wrx.web.gmall.user.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUsers();
    List<UmsMemberReceiveAddress> getAllUserAddress();
    List<UmsMemberReceiveAddress> selectById(String memberId);
}
