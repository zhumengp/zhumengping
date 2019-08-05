package org.com.tianzmp.base;

import java.io.Serializable;

/**
* @Title: TianBaseDTO.java
* @Package dell
* @Description:
* @author zhump  
* @date 2019/7/31 14:59
* @version V1.0  
*/
public class TianBaseDTO {
	
	/**数据库分页参数使用*/
	private Integer start;
	/**当前页，前端传输数据使用*/
	private Integer page = 1;
	/**当前页数据*/
	private Integer rows = 10;
	public Integer getPage() {
		if(page < 1) {
			page =1;
		}
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getStart() {
		start = (this.getPage() -1) * this.getRows();
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}


	@Override
	public String toString() {
		return "TianBaseDTO{" +
				"start=" + start +
				", page=" + page +
				", rows=" + rows +
				'}';
	}
}
