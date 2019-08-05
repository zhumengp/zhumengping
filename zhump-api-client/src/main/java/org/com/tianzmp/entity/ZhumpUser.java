package org.com.tianzmp.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @Title: ZhumpUser.java
* @Package dell
* @Description:
* @author zhump
* @date 2019/7/31 14:57
* @version V1.0
*/
public class ZhumpUser implements Serializable {
	
	/**主键*/
	private Long id;
	/**姓名*/
	private String name;
	/**头像*/
	private String img;
	/**手机号*/
	private String phone;
	/**微信唯一id*/
	private String openId;
	/**创建时间*/
	private Timestamp createTime;
	/**更新时间*/
	private Timestamp updateTime;
	/**0：未知 1：男 2：女*/
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "ZhumpUser{" +
				"id=" + id +
				", name='" + name + '\'' +
				", img='" + img + '\'' +
				", phone='" + phone + '\'' +
				", openId='" + openId + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", sex=" + sex +
				'}';
	}
}
