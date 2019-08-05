package org.com.tianzmp.base;

import java.util.ArrayList;
import java.util.List;

public class PageInfo<T> {
	
	/** 当前页数据*/
	private Integer pageSize = 10;
	/**当前页*/
	private Integer currentPage = 1;
	/**总条数*/
	private Integer totalCount = 0;
	/** 总页数*/
	private Integer totalPage = 0;
	/**分页数据*/
	private List<T> data = new ArrayList<T>();
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize+1);
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
