package me.vliupro.smb.utils;

import java.util.List;

/**
 * Created by vliupro on 16-5-23.
 */
public class Page<T> {

    private int everyPage = 10; // 每页显示的条数
    private int totalCount; // 总数
    private int totalPage; // 总的页数
    private int currentPage = 1; // 将要显示第几页
    private boolean hasPrePage; // 有没有下一页
    private boolean hasNextPage;// 有没有上一页
    private List<T> items; // 返回的list

    public int getEveryPage() {
        return everyPage;
    }

    public void setEveryPage(int everyPage) {
        this.everyPage = everyPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int beginPage) {
        this.currentPage = beginPage;
    }

    public boolean isHasPrePage() {
        return hasPrePage;
    }

    public void setHasPrePage(boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
