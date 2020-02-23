package wrx.web.gmall.service;

import wrx.web.gmall.bean.UmsMember;
import wrx.web.gmall.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUsers();
    List<UmsMemberReceiveAddress> getAllUserAddress();
    List<UmsMemberReceiveAddress> selectById(String memberId);

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);
    List<UmsMember> getAllUser();

    UmsMember login(UmsMember umsMember);

    void addUserToken(String token, String memberId);
}
