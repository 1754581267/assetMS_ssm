package bao.xy.service.Impl;

import bao.xy.dao.DetailMapper;
import bao.xy.model.Details;
import bao.xy.service.DetailsService;
import bao.xy.utils.PageDate;
import bao.xy.utils.PageUtils;
import bao.xy.utils.TableData;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-10-06-15-38
 */
@Service
public class DetailsServiceImpl implements DetailsService {

    @Resource
    private DetailMapper mapper;

    /**
     * 读取数据
     * @param pd
     * @return
     */
    public TableData<Details> paging(PageDate pd) {
        TableData<Details> td = new TableData<>(pd);
        String proof = null;
        String assetClass = null;
        String productName = null;
        String assetsState = null;
        JSONObject sd = pd.getScoutData();
        if (sd != null) {
            assetClass = sd.getString("assetClass");
            proof = sd.getString("proof");
            productName = sd.getString("productName");
            assetsState = sd.getString("assetsState");
        }

        Integer count = mapper.listCount(assetClass, assetsState, proof, productName);
        td.setDataCount(count);
        if (count <= 0) {
            return td;
        }

        List<Details> list = mapper.paging(PageUtils.getRowBounds(pd.getPageIndex(), pd.getPageSize()), assetClass, assetsState, proof, productName);
        td.setDataList(list);
        return td;
    }
}
