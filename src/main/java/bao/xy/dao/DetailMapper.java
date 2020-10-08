package bao.xy.dao;

import bao.xy.model.Details;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import javax.xml.soap.Detail;
import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-10-06-10-17
 */
public interface DetailMapper {

    List<Details> paging(RowBounds rb, @Param("assetClass") String assetClass, @Param("assetsState") String assetsState, @Param("proof") String proof, @Param("productName") String productName);

    Integer listCount(@Param("assetClass") String assetClass, @Param("assetsState") String assetsState, @Param("proof") String proof, @Param("productName") String productName);
}
