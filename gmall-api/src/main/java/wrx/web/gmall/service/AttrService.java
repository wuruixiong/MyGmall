package wrx.web.gmall.service;


import wrx.web.gmall.bean.PmsBaseAttrInfo;
import wrx.web.gmall.bean.PmsBaseAttrValue;
import wrx.web.gmall.bean.PmsBaseSaleAttr;

import java.util.List;


public interface AttrService {

    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();
}
