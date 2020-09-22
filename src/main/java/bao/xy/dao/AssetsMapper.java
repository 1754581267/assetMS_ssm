package bao.xy.dao;

import bao.xy.model.Asset;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-22-08-33
 */
public interface AssetsMapper {

    List<Asset> paging(RowBounds rb, @Param("assetsClass") String assetsClass, @Param("state") String state);

    Integer listCount(@Param("assetsClass") String assetsClass, @Param("state") String state);

}
