package wrx.web.gmall.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import wrx.web.gmall.bean.UmsMember;
import wrx.web.gmall.bean.UmsMemberReceiveAddress;
import wrx.web.gmall.service.UserService;
import wrx.web.gmall.user.mapper.UmsMemberReceiveAddressMapper;
import wrx.web.gmall.user.mapper.UserMapper;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UmsMemberReceiveAddressMapper umsAddressMapper;

    @Override
    public List<UmsMember> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public List<UmsMemberReceiveAddress> getAllUserAddress() {
        return umsAddressMapper.selectAll();
    }

    @Override
    public List<UmsMemberReceiveAddress> selectById(String memberId) {
        UmsMemberReceiveAddress address = new UmsMemberReceiveAddress();
        address.setMemberId(memberId);
        return umsAddressMapper.select(address);

       /* Example example = new Example(UmsMemberReceiveAddress.class);
        example.createCriteria().andEqualTo("memberId", memberId);
        return umsAddressMapper.selectByExample(example);*/

    }

}