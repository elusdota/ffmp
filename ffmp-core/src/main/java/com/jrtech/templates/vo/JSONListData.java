package com.jrtech.templates.vo;

import java.util.List;

/**
 * bootstrap-table加载数据,数据结构虚拟对象elus
 */
public class JSONListData<T> {
	/**
	 * 总行数
	 */
    private Long total = 0L;
    /**
     * 数据
     */
    private List<T> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
