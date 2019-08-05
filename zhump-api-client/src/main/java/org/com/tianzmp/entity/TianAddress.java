package org.com.tianzmp.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
* @Title: TianAddress.java
* @Package dell
* @Description: 地址实体类
* @author zhump
* @date 2019/7/31 14:43
* @version V1.0
*/
public class TianAddress implements Serializable {
	
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 市区
	 */
	private String city;
	/**
	 * 详细地址
	 */
	private String address;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return "TianAddress{" +
				"id=" + id +
				", userId=" + userId +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", address='" + address + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
