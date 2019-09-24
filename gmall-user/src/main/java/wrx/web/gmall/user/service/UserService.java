package wrx.web.gmall.user.service;

import org.springframework.stereotype.Service;
import wrx.web.gmall.user.bean.UmsMember;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUsers();
}
