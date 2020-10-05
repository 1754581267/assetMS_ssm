package bao.xy.dao;

import bao.xy.model.Asset;
import bao.xy.model.Finance;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-10-05-13-07
 */
public interface FinanceMapper {

    List<Finance> paging(RowBounds rb, @Param("proof") String proof, @Param("state") String state);

    Integer listCount(@Param("proof") String proof, @Param("state") String state);

    Integer add(@Param("finance") Finance finance);
}
