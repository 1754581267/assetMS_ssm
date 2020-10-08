package bao.xy.service;

import bao.xy.model.Details;
import bao.xy.utils.PageDate;
import bao.xy.utils.TableData;

/**
 * @Description:
 * @CreateTime: 2020-10-06-15-38
 */
public interface DetailsService {

    /**
     * 读取数据
     * @param pd
     * @return
     */
    TableData<Details> paging(PageDate pd);

}
