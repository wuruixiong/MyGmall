package wrx.web.gmall.user.mapper;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import wrx.web.gmall.bean.UmsMember;

import java.util.List;

@Repository
public interface UserMapper extends Mapper<UmsMember> {
    List<UmsMember> selectAllUsers();
}
