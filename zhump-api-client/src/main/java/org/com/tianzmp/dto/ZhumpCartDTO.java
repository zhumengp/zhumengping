package org.com.tianzmp.dto;


import org.com.tianzmp.base.ZhumpBaseDTO;

/**
* @Title: ZhumpCartDTO.java
* @Package dell
* @Description:
* @author zhump  
* @date 2019/7/31 15:01
* @version V1.0  
*/
public class ZhumpCartDTO extends ZhumpBaseDTO {
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

	@Override
	public String toString() {
		return "ZhumpCartDTO{" +
				"id=" + id +
				", userId=" + userId +
				", goodsId=" + goodsId +
				", goodsNum=" + goodsNum +
				'}';
	}
}
