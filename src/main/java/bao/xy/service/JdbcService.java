package bao.xy.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-20-15-38
 */
public interface JdbcService {

    String isOrNot(String work, String suffix);

    boolean updt(Integer num, String table, String k, String v, String wherek, String wherev);

    /**
     * 将id数组组成的字符串分割添加进list
     * @param ids
     * @return
     */
    List<Integer> idList(String ids);

    /**
     * 查询 一个约束
     * @param table 表
     * @param k 键
     * @param k1 约束键
     * @param v1 约束值
     * @return String
     */
    String selStr(String table, String k, String k1, String v1);

    /**
     * 查询 两个约束
     * @param table 表名
     * @param k 键
     * @param k1 约束键1
     * @param v1 约束值1
     * @param k2 约束键2
     * @param v2 约束值2
     * @return String
     */
    String selectStr(String table, String k, String k1, String v1, String k2, String v2);

    /**
     * 查询 一个约束
     * @param table 表
     * @param k1 约束键
     * @param v1 约束值
     * @return integer
     */
    Integer sel(String table, String k1, String v1);

    /**
     * 查询 两个约束
     * @param table 表名
     * @param k1 约束键1
     * @param v1 约束值1
     * @param k2 约束键2
     * @param v2 约束值2
     * @return integer
     */
    Integer select(String table, String k1, String v1, String k2, String v2);

    /**
     * 根据id删除数据
     * @param table 表名
     * @param idList id[]
     * @return integer
     */
    String delIds(String table, List<Integer> idList);
}
