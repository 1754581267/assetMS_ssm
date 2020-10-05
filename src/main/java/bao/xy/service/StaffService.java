package bao.xy.service;

import bao.xy.model.Staff;
import bao.xy.utils.PageDate;
import bao.xy.utils.TableData;
import javafx.scene.control.Tab;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-05-21-40
 */
public interface StaffService {

    /**
     * 查询
     *
     * @param pd
     * @return
     */
    TableData<Staff> paging(PageDate pd);

    /**
     * 删除
     *
     * @param id 员工id
     * @return
     */
    String del(List<Integer> id);


    String add(Staff staff);


    /**
     * 修改权限
     *
     * @param id 员工id
     * @param powers 权限
     * @return code
     */
    String updp(List<Integer> id, String powers);

    /**
     * * 修改员工信息
     * @param staff 员工信息
     * @return
     */
    String updt(Staff staff);
}
