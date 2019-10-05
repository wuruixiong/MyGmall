package wrx.web.gmall.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wrx.web.gmall.bean.UmsMember;
import wrx.web.gmall.bean.UmsMemberReceiveAddress;
import wrx.web.gmall.user.mapper.UmsMemberReceiveAddressMapper;
import wrx.web.gmall.user.mapper.UserMapper;
import wrx.web.gmall.service.UserService;

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
