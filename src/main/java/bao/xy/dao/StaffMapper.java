package bao.xy.dao;

import bao.xy.model.Staff;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-16-16-24
 */
public interface StaffMapper {

    Integer del(@Param("ids") List<Integer> ids);

    List<Staff> paging(RowBounds rb, @Param("work") String work, @Param("name") String name, @Param("uname") String uname);

    Integer listCount(@Param("work") String work, @Param("name") String name, @Param("uname") String uname);

    Integer add(@Param("staff") Staff staff);

    Integer updp(@Param("id") String id, @Param("powers") String powers);

    Integer updt(@Param("staff") Staff staff);

}
