package bao.xy.service;

import bao.xy.model.Product;
import bao.xy.utils.PageDate;
import bao.xy.utils.TableData;

/**
 * @Description:
 * @CreateTime: 2020-10-05-18-13
 */
public interface ProductService {

    /**
     * 提取/搜索产品表中的数据
     * @param pd
     * @return
     */
    TableData<Product> paging(PageDate pd);

    /**
     * 添加产品数据
     * @param product
     * @return
     */
    String add(Product product);

    /**
     * 修改产品信息
     * @param product
     * @return
     */
    String updt(Product product);
}
