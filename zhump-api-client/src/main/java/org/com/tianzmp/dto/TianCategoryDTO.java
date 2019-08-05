package org.com.tianzmp.dto;


import org.com.tianzmp.base.TianBaseDTO;
/**
 * @author zhump
 */
public class TianCategoryDTO extends TianBaseDTO{
	
	private Long id;
	
	/**
	 * 主键
	 */
	private String name;
	
	private Long pId;
	
	private Integer status;

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
