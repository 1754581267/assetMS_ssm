package bao.xy.service.Impl;

import bao.xy.dao.StaffMapper;
import bao.xy.model.Staff;
import bao.xy.service.StaffService;
import bao.xy.utils.PageDate;
import bao.xy.utils.PageUtils;
import bao.xy.utils.TableData;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-05-21-40
 */
@Service
public class StaffServiceImpl implements StaffService {


    @Resource
    private StaffMapper mapper;

    /**
     *
     * @param pd
     * @return
     */
    @Override
    @Transactional( rollbackFor = Exception.class)
    public TableData<Staff> paging(PageDate pd) {
        TableData<Staff> td = new TableData(pd);
        String work = null;
        String name = null;
        String uname = null;
        JSONObject sd = pd.getScoutData();
        if (sd != null) {
            work = sd.getString("work");
            name = sd.getString("name");
            uname = sd.getString("uname");
        }

        Integer count = mapper.listCount(work, name, uname);
        td.setDataCount(count);
//        td.setPageCount(PageUtils.getLastPage(count, size));

        if (count <= 0) {
            return td;
        }

        List<Staff> list = mapper.paging(PageUtils.getRowBounds(pd.getPageIndex(), pd.getPageSize()), work, name, uname);
        td.setDataList(list);
        return td;

    }

    /**
     * 修改权限
     * @param id 员工id
     * @param powers 权限
     * @return
     */
    @Override
    @Transactional( rollbackFor = Exception.class)
    public String updp(List<Integer> id, String powers) {
//        System.out.println(id);
        String code = "";
        Integer updp = mapper.updp(id, powers);
        if (updp > 0) {
            code = "updtSuc";
//            if (id.equals(LoginServiceImpl.id) && powers.equals("已锁定")) {
//                code = "myself";
//            }
        } else {
            code = "updtErr";
        }
        return code;
    }

    /**
     * * 修改员工信息
     * @param staff 员工信息
     * @return
     */

//    @Override
    @Transactional( rollbackFor = Exception.class)
    public String updt(Staff staff) {
        String code = "";
        Integer updp = mapper.updt(staff);
        if (updp > 0) {
            code = "uptSuc";
            if (staff.getId().equals(LoginServiceImpl.id)) {
                code = "myself";
            }
        } else {
            code = "uptErr";
        }
        return code;
    }

    @Override
    @Transactional( rollbackFor = Exception.class)
    public String add(Staff staff) {
        Integer add = mapper.add(staff);
        if (add > 0) {
            return "addSuc";
        } else {
            return "addErr";
        }
    }



    @Override
    @Transactional( rollbackFor = Exception.class)
    public String del(List<Integer> id) {
        Integer del = mapper.del(id);
        if (del > 0) {
            return  "delSuc";
        } else {
            return  "delErr";
        }
    }

}
