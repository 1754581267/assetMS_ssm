package bao.xy.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description:
 * @CreateTime: 2020-10-04-11-57
 */
public class PageDate {

    // 默认分页数
    private final static Integer DEFAULT_PAGE_SIZE = 5;
    // 页码
    private Integer pageIndex;
    // 分页数显
    private Integer pageSize;
    // 搜索数据
    private String scoutData;

    public PageDate() {
    }

    public PageDate(Integer pageIndex, Integer pageSize, String scoutData) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.scoutData = scoutData;
    }

    public Integer getPageIndex() {
        if (this.pageIndex == null || this.pageIndex < 1) {
            this.pageIndex = 1;
        }
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        if (this.pageSize == null || this.pageSize < 1) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public JSONObject getScoutData() {
        if (StringUtil.isNotEmpty(this.scoutData)) {
            try {
                JSONObject parse = (JSONObject) JSONObject.parse(this.scoutData);
                return parse;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setScoutData(String scoutData) {
        this.scoutData = scoutData;
    }

    @Override
    public String toString() {
        return "PageDate{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", scoutData='" + scoutData + '\'' +
                '}';
    }
}
