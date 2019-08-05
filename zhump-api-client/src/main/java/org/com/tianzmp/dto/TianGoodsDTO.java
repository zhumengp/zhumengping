package org.com.tianzmp.dto;
import java.math.BigDecimal;
import org.com.tianzmp.base.TianBaseDTO;

/**
 * @author zhump
 */
public class TianGoodsDTO extends TianBaseDTO {
	
	/**主键*/
	private Long id;
	/**名称*/
	private String name;
	/**单价*/
	private BigDecimal price;
	/**类目id*/
	private Long categoryId;
	/**图片*/
	private String picture;
	/**库存*/
	private Integer inventory;

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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
}
