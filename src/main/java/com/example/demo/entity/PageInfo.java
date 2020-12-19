package com.example.demo.entity;

public class PageInfo {

    private int pageNum;
    private int pageSize;
    private long total;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageName) {
        this.pageNum = pageName;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}
