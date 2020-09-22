package bao.xy.dao;

import bao.xy.model.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-15-21-11
 */
public interface LoginMapper {

    List<Staff> login(@Param("uname") String uname);

}
