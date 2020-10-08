package bao.xy.service;

import bao.xy.model.Application;
import bao.xy.utils.PageDate;
import bao.xy.utils.TableData;

/**
 * @Description:
 * @CreateTime: 2020-10-07-19-23
 */
public interface ApplicationService {

    /**
     * 读取需要的申请表的数据
     * @param pd
     * @return
     */
    TableData<Application> paging(PageDate pd);


    /**
     * 查询资产状态
     * @param finda 资产id
     * @return code
     */
    String finda(String finda);

    /**
     * 添加申请业务数据
     * @param app
     * @return
     */
    String add(Application app);

    /**
     * 申请归还
     * @param app
     * @return
     */
    String ret(Application app);

    /**
     * 申请报废
     * @param app
     * @return
     */
    String scrap(Application app);
}
