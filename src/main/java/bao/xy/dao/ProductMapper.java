package bao.xy.dao;

import bao.xy.model.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import javax.swing.*;
import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-10-05-18-15
 */
public interface ProductMapper {

    Integer add(@Param("product") Product product);

    Integer updt(@Param("product") Product product);

    List<Product> paging(RowBounds rb, @Param("name") String name, @Param("specification") String specification, @Param("srorageTime") String srorageTime);

    Integer listCount(@Param("name") String name, @Param("specification") String specification, @Param("srorageTime") String srorageTime);
}
