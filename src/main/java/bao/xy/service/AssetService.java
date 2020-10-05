package bao.xy.service;

import bao.xy.model.Asset;
import bao.xy.utils.PageDate;
import bao.xy.utils.TableData;

import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-05-13-40
 */
public interface AssetService {

    /**
     *
     * @param pd
     * @return
     */
    TableData<Asset> paging(PageDate pd);

    /**
     * 查找财务id
     *
     * @param ffid 财务id
     * @return code
     */
    String ffid(String ffid, String id);


    /**
     * 资源id查询
     *
     * @param fpid
     * @return
     */
    String fpid(String fpid, String id);

    /**
     * 添加资产信息
     *
     * @param asset
     * @return
     */
    String add(Asset asset);

    /**
     * 更改信息
     *
     * @param asset
     * @return
     */
    String updt(Asset asset);
}
