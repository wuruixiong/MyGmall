package wrx.web.gmall.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wrx.web.gmall.user.bean.UmsMember;
import wrx.web.gmall.user.mapper.UserMapper;
import wrx.web.gmall.user.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UmsMember> getAllUsers() {
        return userMapper.selectAll();
    }


}
