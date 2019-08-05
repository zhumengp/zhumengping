package org.com.tianzmp.dto;


import org.com.tianzmp.base.TianBaseDTO;
/**
 * @author zhump
 */
public class TianAddressDTO extends TianBaseDTO{
	
	/**主键*/
	private Long id;
	/**用户id*/
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

	@Override
	public String toString() {
		return "TianAddressDTO{" +
				"id=" + id +
				", user_id=" + userId +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
