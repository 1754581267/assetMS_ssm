package bao.xy.service;

import bao.xy.model.Asset;
import bao.xy.utils.TableData;

import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-05-13-40
 */
public interface AssetService {

    /**
     * 分页
     *
             * @param index 页数
     * @param assetClass 资产类别
     * @param state 资产状态
     * @return data
     */
    TableData<Asset> paging(Integer index, String assetClass, String state);

    /**
     * 查找财务id
     *
     * @param ffid 财务id
     * @return code
     */
    String ffid(String ffid);


    /**
     * 资源id查询
     *
     * @param fpid
     * @return
     */
    String fpid(String fpid);
}
