package wrx.web.gmall.manage.mapper;


import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import wrx.web.gmall.bean.PmsBaseAttrInfo;

import java.util.List;

public interface PmsBaseAttrInfoMapper extends Mapper<PmsBaseAttrInfo> {
    List<PmsBaseAttrInfo> selectAttrValueListByValueId(@Param("valueIdStr") String valueIdStr);
}
