package bao.xy.utils;

import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-21-19-44
 */
public class TableData<T> {

    // flag
    private String code;

    // 页面索引
    private Integer pageIndex;

    // 页面数据条数
    private Integer pageSize;

    // 总页数
    private Integer pageCount;

    // 总数据条数
    private Integer dataCount;

    // 数据列表
    private List<T> dataList;

    public TableData() {
    }

    public TableData(String code, Integer pageIndex, Integer pageSize, Integer pageCount, Integer dataCount, List<T> dataList) {
        this.code = code;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.dataCount = dataCount;
        this.dataList = dataList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "TableData{" +
                "code='" + code + '\'' +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", pageCount=" + pageCount +
                ", dataCount=" + dataCount +
                ", dataList=" + dataList +
                '}';
    }
}
