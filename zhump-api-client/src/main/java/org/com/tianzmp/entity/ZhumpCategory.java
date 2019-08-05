package org.com.tianzmp.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @Title: ZhumpCategory.java
* @Package dell
* @Description:
* @author zhump
* @date 2019/7/31 14:56
* @version V1.0
*/
public class ZhumpCategory implements Serializable {
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 类目名称
	 */
	private String name;
	/***
	 * 父类id
	 */
	private Long pId;
	/***
	 * 0:下线 1：上线
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**
	 * 更新时间
	 */
	private Timestamp updateTime;
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
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "ZhumpCategory{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pId=" + pId +
				", status=" + status +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
