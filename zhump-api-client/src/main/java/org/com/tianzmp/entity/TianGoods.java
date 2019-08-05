package org.com.tianzmp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
/**
* @Title: TianGoods.java
* @Package dell
* @Description:
* @author zhump  
* @date 2019/7/31 14:57
* @version V1.0  
*/
public class TianGoods implements Serializable {
	
	/**主键*/
	private Long id;
	/**名称*/
	private String name;
	/**单价*/
	private BigDecimal price;
	/**图片*/
	private String picture;
	/**库存*/
	private Integer inventory;
	/**创建时间*/
	private Timestamp createTime;
	/**更新时间*/
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
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
		return "TianGoods{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", picture='" + picture + '\'' +
				", inventory=" + inventory +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
