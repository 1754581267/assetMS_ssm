package bao.xy.service.Impl;

import bao.xy.dao.ApplicationMapper;
import bao.xy.model.Application;
import bao.xy.service.ApplicationService;
import bao.xy.service.JdbcService;
import bao.xy.utils.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-10-07-19-24
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Resource
    private ApplicationMapper mapper;

    @Resource
    private JdbcService jdbcService;

    String code = null;
    /**
     * 读取需要的申请表的数据
     * @param pd
     * @return
     */
    public TableData<Application> paging(PageDate pd) {
        TableData<Application> td = new TableData<>(pd);
        String name = null;
        String appTime = null;
        String retTime = null;
        String state = null;
        JSONObject sd = pd.getScoutData();
        if (sd != null) {
            name = sd.getString("name");
            appTime = sd.getString("appTime");
            retTime = sd.getString("retTime");
            state = sd.getString("state");
        }
        Integer count = mapper.listCount(name, appTime, retTime, state);
        td.setDataCount(count);
        if (count <= 0) {
            return td;
        }
        List<Application> list = mapper.paging(PageUtils.getRowBounds(pd.getPageIndex(), pd.getPageSize()), name, appTime, retTime, state);
        td.setDataList(list);
        return td;
    }

    /**
     * 查询资产状态
     * @param finda 资产id
     * @return code
     */
    public String finda(String finda) {
        System.out.println(finda);
        Integer sel = jdbcService.sel("assets", "id", finda);
        if (sel >= 1 && StringUtil.isNotEmpty(finda)) {
            Integer select = jdbcService.select("assets", "id", finda, "state", "可用");
            System.out.println(select);
            if (select >= 1) {
                code = "isok";
            } else {
                code = "isnot";
            }
        } else {
            code = "notfinda";
        }
        return code;
    }

    /**
     * 添加申请业务数据
     * @param app
     * @return
     */
    public String add(Application app) {
        app.setApplicationTime(DateUtils.Date());
        app.setStaffId(LoginServiceImpl.id);
        app.setState("使用中");
        Integer add = mapper.add(app);
        boolean updt = jdbcService.updt(1, "assets", "state", "使用中", "id", app.getAssetsId());
        if (add > 0 && updt) {
            return "addSuc";
        }
        return "addErr";
    }

    /**
     * 申请归还
     * @param app
     * @return
     */
    public String ret(Application app) {
        if (LoginServiceImpl.id.equals(app.getStaffId())) {
            boolean updt = jdbcService.updt(1, "assets", "state", "可用", "id", app.getAssetsId());
            boolean updt1 = jdbcService.updt(1, "application", "return_time", DateUtils.Date(), "id", app.getId());
            boolean updt2 = jdbcService.updt(1, "application", "state", "已归还", "id", app.getId());

            if (updt && updt1 && updt2) {
                return "retok";
            } else {
                return "reterr";
            }
        } else {
            return "notsid";
        }
    }

    /**
     * 申请报废
     * @param app
     * @return
     */
    public String scrap(Application app) {
        if (LoginServiceImpl.id.equals(app.getStaffId())) {
            boolean updt = jdbcService.updt(1, "assets", "state", "报废", "id", app.getAssetsId());
            boolean updt1 = jdbcService.updt(1, "application", "return_time", DateUtils.Date(), "id", app.getId());
            boolean updt2 = jdbcService.updt(1, "application", "state", "已报废", "id", app.getId());
            String id = jdbcService.selStr("assets", "finance_id", "id", app.getAssetsId());
            boolean updt3 = jdbcService.updt(1, "finance", "state", "销账", "id", id);
            if (updt && updt1 && updt2 && updt3) {
                return "scrapok";
            } else {
                return "scraperr";
            }
        } else {
            return "notsid";
        }
    }
}
