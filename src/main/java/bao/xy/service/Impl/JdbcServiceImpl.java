package bao.xy.service.Impl;

import bao.xy.dao.JdbcUtilsMapper;
import bao.xy.service.JdbcService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-20-15-39
 */
@Service
public class JdbcServiceImpl implements JdbcService {

    @Resource
    private JdbcUtilsMapper mapper;

    @Override
    public String isOrNot(String work, String suffix) {
        String code = "";
        String ework = new LoginServiceImpl().work;
        if (ework.equals(work)) {
            code = "is" + suffix;
        } else {
            code = "not" + suffix;
        }
        return code;
    }

    /**
     * 将id数组组成的字符串分割添加进list
     * @param ids
     * @return
     */
    public List<Integer> idList(String ids) {
        String[] idListStr = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String isStr : idListStr) {
            int id1 = Integer.parseInt(isStr);
            idList.add(id1);
        }
        return idList;
    }

    @Override
    @Transactional( rollbackFor = Exception.class) // 出现异常指令回滚(rollback) 不带注解直接提交(commit)
    public boolean updt(Integer num, String table, String k, String v, String wherek, String wherev) {

        Integer updt = mapper.updt(table, k, v, wherek, wherev);
        System.out.println(updt);
        if (updt >= num) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询 一个约束
     * @param table 表名
     * @param k 键
     * @param k1 约束键
     * @param v1 约束值
     * @return string
     */
    @Override
    public String selStr(String table, String k, String k1, String v1) {
        return selectStr(table, k, k1, v1, null, null);
    }

    /**
     * 查询 两个约束
     * @param table 表名
     * @param k 键
     * @param k1 约束键1
     * @param v1 约束值1
     * @param k2 约束键2
     * @param v2 约束值2
     * @return string
     */
    @Override
    @Transactional( rollbackFor = Exception.class)
    public String selectStr(String table, String k, String k1, String v1, String k2, String v2) {
        return mapper.selectStr(table, k, k1, v1, k2, v2);
    }

    /**
     * 查询 一个约束
     * @param table 表名
     * @param k1 约束键
     * @param v1 约束值
     * @return integer
     */
    @Override
    public Integer sel(String table, String k1, String v1) {
        return select(table, k1, v1, null, null);
    }

    /**
     * 查询 两个约束
     * @param table 表名
     * @param k1 约束键1
     * @param v1 约束值1
     * @param k2 约束键2
     * @param v2 约束值2
     * @return integer
     */
    @Override
    @Transactional( rollbackFor = Exception.class)
    public Integer select(String table, String k1, String v1, String k2, String v2) {
        return mapper.select(table, k1, v1, k2, v2);
    }

    /**
     * 根据id删除数据
     * @param table 表名
     * @param id id[]
     * @return integer
     */
    @Override
    @Transactional( rollbackFor = Exception.class)
    public String delIds(String table, List<Integer> id) {
        Integer integer = mapper.delIds(table, id);
        if (integer > 0) {
            return "delSuc";
        } else {
            return "delErr";
        }
    }

}
