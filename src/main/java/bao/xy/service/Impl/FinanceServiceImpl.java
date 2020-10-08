package bao.xy.service.Impl;

import bao.xy.dao.FinanceMapper;
import bao.xy.model.Finance;
import bao.xy.service.FinanceService;
import bao.xy.service.JdbcService;
import bao.xy.utils.PageDate;
import bao.xy.utils.PageUtils;
import bao.xy.utils.TableData;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-10-05-13-06
 */
@Service
public class FinanceServiceImpl implements FinanceService {

    @Resource
    private FinanceMapper mapper;

    @Resource
    private JdbcService jdbcService;

    /**
     * 分页
     *
     * @param pd 搜索数据
     * @return td
     */
    @Override
    @Transactional( rollbackFor = Exception.class) // 出现异常指令回滚(rollback) 不带注解直接提交(commit)
    public TableData<Finance> paging(PageDate pd) {
        TableData td = new TableData(pd);
        String proof = null;
        String state = null;
        JSONObject sd = pd.getScoutData();
        if (sd != null) {
            proof = sd.getString("proof");
            state = sd.getString("state");
        }
        Integer count = mapper.listCount(proof, state);
        td.setDataCount(count);
        if (count <= 0) {
            return td;
        }

        List<Finance> list = mapper.paging(PageUtils.getRowBounds(pd.getPageIndex(), pd.getPageSize()), proof, state);
        td.setDataList(list);
        return td;
    }

    /**
     * 添加财务信息
     * @param finance
     * @return string
     */
    @Override
    @Transactional( rollbackFor = Exception.class) // 出现异常指令回滚(rollback) 不带注解直接提交(commit)
    public String add(Finance finance) {
        Integer add = mapper.add(finance);
        if (add > 0) {
            return "addSuc";
        } else {
            return "addErr";
        }

    }

    /**
     * 修改财产状态
     * @param finance
     * @return string
     */
    public String updt(Finance finance) {
        boolean updt = jdbcService.updt(1, "finance", "state", finance.getState(), "id", finance.getId());
        if (updt) {
            // 成功
            return "updtSuc";
        }
        // 失败
        return "updtErr";
    }
}
