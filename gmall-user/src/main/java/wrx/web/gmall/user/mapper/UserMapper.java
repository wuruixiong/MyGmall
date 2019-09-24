package wrx.web.gmall.user.mapper;

import tk.mybatis.mapper.common.Mapper;
import wrx.web.gmall.user.bean.UmsMember;

import java.util.List;

public interface UserMapper extends Mapper<UmsMember> {
    List<UmsMember> selectAllUsers();
}
