package com.jrtech.templates.vo;

/**
 * Created by jiangliang on 2016/6/26.boostrap-table 参数实体，elus
 */
public class TableGetDataParameters {
    private int pageNumber;//页码
    private int limit;//每页显示行数
    private String order;//排序升序、降序
    private int offset;//偏移
    private String sort;//排序字段
    private String search;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
