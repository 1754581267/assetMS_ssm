package bao.xy.service.Impl;

import bao.xy.dao.AssetsMapper;
import bao.xy.model.Asset;
import bao.xy.service.AssetService;
import bao.xy.service.JdbcService;
import bao.xy.utils.PageUtils;
import bao.xy.utils.TableData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-05-13-40
 */
@Service("assetService")
public class AssetServiceImpl implements AssetService {

    @Value("${size}")
    private int size;

    @Resource
    private AssetsMapper mapper;

    private String code = "";

    @Resource
    private JdbcService jdbcService;

    /**
     * 分页
     *
     * @param index 页数
     * @param assetClass 资产类别
     * @param state 资产状态
     * @return data
     */
    public TableData<Asset> paging(Integer index, String assetClass, String state) {
        TableData td = new TableData();
        td.setPageSize(size);
        td.setPageIndex(index);
        Integer count = mapper.listCount(assetClass, state);
        td.setDataCount(count);
        td.setPageCount(PageUtils.getLastPage(count, size));
        if (count < 0) {
            return td;
        }

        List<Asset> list = mapper.paging(PageUtils.getRowBounds(index, size), assetClass, state);
        td.setDataList(list);

        return null;
    }

    /**
     * 查找财务id
     *
     * @param ffid 财务id
     * @return code
     */
    public String ffid(String ffid) {
        Integer sel = jdbcService.sel("finance", "id", ffid);
        if (sel >= 1) {
            Integer sel1 = jdbcService.sel("assets", "finance_id", ffid);
            if (sel1 >= 1) {
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
    public String fpid(String fpid) {
        Integer sel = jdbcService.sel("product", "id", fpid);
        if (sel >= 1) {
            Integer sel1 = jdbcService.sel("assets", "product_id", fpid);
            if (sel1 >= 1) {
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
}
