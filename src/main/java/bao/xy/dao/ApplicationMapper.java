package bao.xy.dao;

import bao.xy.model.Application;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-10-07-18-58
 */
public interface ApplicationMapper {

    List<Application> paging(RowBounds rb, @Param("name") String name, @Param("appTime") String appTime, @Param("retTime") String retTime, @Param("state") String state);

    Integer listCount(@Param("name") String name, @Param("appTime") String appTime, @Param("retTime") String retTime, @Param("state") String state);

    Integer add(@Param("app") Application app);

}
