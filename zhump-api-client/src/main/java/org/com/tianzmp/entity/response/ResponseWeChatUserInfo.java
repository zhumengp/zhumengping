package org.com.tianzmp.entity.response;


public class ResponseWeChatUserInfo {
	
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 头像
	 */
	private String img;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 微信唯一id
	 */
	private String open_id;

	private Integer sex;

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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
}
