package org.com.tianzmp.joggle.third.response;

import java.io.Serializable;

/**
 * @author zhump
 */
public class WeChatUserInfo implements Serializable {

    /**微信唯一标识*/
    private String openid;
    /**昵称*/
    private String nickname;
    /**微信唯一标识*/
    private Integer sex;
    /**头像*/
    private String headimgurl;
    /**省份*/
    private String province;
    /**乡镇*/
    private String city;
    /**市级城市*/
    private String country;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "WeChatUserInfo{" +
                "openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", headimgurl='" + headimgurl + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
