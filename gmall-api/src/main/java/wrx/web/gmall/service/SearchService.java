package wrx.web.gmall.service;


import wrx.web.gmall.bean.PmsSearchParam;
import wrx.web.gmall.bean.PmsSearchSkuInfo;

import java.util.List;

public interface SearchService {
    List<PmsSearchSkuInfo> list(PmsSearchParam pmsSearchParam);
}
