package wrx.web.gmall.manage.mapper;

import tk.mybatis.mapper.common.Mapper;
import wrx.web.gmall.bean.PmsSkuInfo;

import java.util.List;

public interface PmsSkuInfoMapper extends Mapper<PmsSkuInfo>{
    List<PmsSkuInfo> selectSkuSaleAttrValueListBySpu(String productId);
}
