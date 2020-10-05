package bao.xy.service.Impl;

import bao.xy.dao.AssetsMapper;
import bao.xy.model.Asset;
import bao.xy.service.AssetService;
import bao.xy.service.JdbcService;
import bao.xy.utils.PageDate;
import bao.xy.utils.PageUtils;
import bao.xy.utils.TableData;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-05-13-40
 */
@Service
public class AssetServiceImpl implements AssetService {

    @Resource
    private AssetsMapper mapper;

    private String code = "";

    @Resource
    private JdbcService jdbcService;

    /**
     * 分页
     *
     * @param pd 搜索数据
     * @return data
     */
    public TableData<Asset> paging(PageDate pd) {
        TableData td = new TableData(pd);
        String assetClass = null;
        String state = null;
        JSONObject sd = pd.getScoutData();
        if (sd != null) {
            assetClass = sd.getString("assetClass");
            state = sd.getString("state");
        }
        Integer count = mapper.listCount(assetClass, state);
        td.setDataCount(count);
        if (count <= 0) {
            return td;
        }

        List<Asset> list = mapper.paging(PageUtils.getRowBounds(pd.getPageIndex(), pd.getPageSize()), assetClass, state);
        td.setDataList(list);
        return td;
    }

    /**
     * 查找财务id
     *
     * @param ffid 财务id
     * @return code
     */
    public String ffid(String ffid, String id) {
        Integer sel = jdbcService.sel("finance", "id", ffid);
        int i = 1;
        if (!"".equals(id)) {
            i = 2;
        }
        if (sel >= 1) {
            Integer sel1 = jdbcService.sel("assets", "finance_id", ffid);
            if (sel1 >= i) {
                code = "fidUsed";
            } else {
                code = "findfid";
            }
        } else {
            code = "notfindfid";
        }
        return code;
    }

    /**
     * 资源id查询
     *
     * @param fpid
     * @return
     */
    public String fpid(String fpid, String id) {
        Integer sel = jdbcService.sel("product", "id", fpid);
        int i = 1;
        if (!"".equals(id)) {
            i = 2;
        }
        if (sel >= 1) {
            Integer sel1 = jdbcService.sel("assets", "product_id", fpid);
            if (sel1 >= i) {
                code = "pidUsed";
            } else {
                code = "findpid";
            }
        } else {
            code = "notfindpid";
        }
        return code;
    }

    public String del(List<Integer> idList) {
        code = jdbcService.delIds("assets", idList);
        return code;
    }

    /**
     * 添加资产信息
     *
     * @param asset
     * @return
     */
    public String add(Asset asset) {
        Integer add = mapper.add(asset);
        if (add > 0) {
            return "addSuc";
        } else {
            return "addErr";
        }
    }

    /**
     * 更改信息
     *
     * @param asset
     * @return
     */
    public String updt(Asset asset) {
        System.out.println(asset.toString());
        Integer updt = mapper.updt(asset);
        if (updt > 0) {
            return "updtSuc";
        } else {
            return "updtErr";
        }
    }
}
