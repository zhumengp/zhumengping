package org.com.tianzmp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* @Title: TianCart.java
* @Package dell
* @Description: 购物车实体类
* @author zhump  
* @date 2019/7/31 14:47
* @version V1.0  
*/
public class TianCart implements Serializable {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 商品id
	 */
	private Long goodsId;
	/**
	 * 购买商品数量
	 */
	private Integer goodsNum;
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

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
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
		return "TianCart{" +
				"id=" + id +
				", userId=" + userId +
				", goodsId='" + goodsId + '\'' +
				", goodsNum=" + goodsNum +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
