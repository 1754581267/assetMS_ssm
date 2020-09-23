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

    List<Asset> paging(RowBounds rb, @Param("assetClass") String assetClass, @Param("state") String state);

    Integer listCount(@Param("assetClass") String assetClass, @Param("state") String state);

    Integer add(@Param("asset") Asset asset);

    Integer updt(@Param("asset") Asset asset);
}
