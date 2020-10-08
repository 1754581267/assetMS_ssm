package bao.xy.service.Impl;

import bao.xy.dao.ProductMapper;
import bao.xy.model.Product;
import bao.xy.service.ProductService;
import bao.xy.utils.PageDate;
import bao.xy.utils.PageUtils;
import bao.xy.utils.TableData;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-10-05-18-13
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper mapper;

    /**
     * 提取/搜索产品表中的数据
     * @param pd
     * @return
     */
    public TableData<Product> paging(PageDate pd) {
        TableData td = new TableData(pd);
        String name = null;
        String specification = null;
        String srorageTime = null;
        JSONObject sd = pd.getScoutData();
        if (sd != null) {
            name = sd.getString("name");
            specification = sd.getString("specification");
            srorageTime = sd.getString("srorageTime");
        }
        Integer count = mapper.listCount(name, specification, srorageTime);
        td.setDataCount(count);
        if (count <= 0) {
            return td;
        }

        List<Product> list = mapper.paging(PageUtils.getRowBounds(pd.getPageIndex(), pd.getPageSize()), name, specification, srorageTime);
        td.setDataList(list);
        return td;
    }

    /**
     * 添加产品数据
     * @param product
     * @return
     */
    public String add(Product product) {
        Integer add = mapper.add(product);
        if (add > 0) {
            return "addSuc";
        }
        return "addErr";
    }

    /**
     * 修改产品信息
     * @param product
     * @return
     */
    public String updt(Product product) {
        Integer updt = mapper.updt(product);
        if (updt > 0) {
            return "updtSuc";
        }
        return "updtErr";
    }
}
