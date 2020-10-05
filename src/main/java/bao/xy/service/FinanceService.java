package bao.xy.service;

import bao.xy.model.Finance;
import bao.xy.utils.PageDate;
import bao.xy.utils.TableData;

/**
 * @Description:
 * @CreateTime: 2020-10-05-13-05
 */
public interface FinanceService {
    /**
     * 分页
     *
     * @param pd 搜索数据
     * @return td
     */
    TableData<Finance> paging(PageDate pd);

    /**
     * 添加财务信息
     * @param finance
     * @return string
     */
    String add(Finance finance);

    /**
     * 修改财产状态
     * @param finance
     * @return
     */
    String updt(Finance finance);
}
