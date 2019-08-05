package org.com.tianzmp.util.wx;


import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.com.tianzmp.cache.RedisUtils;
import org.com.tianzmp.joggle.third.response.WeChatUserInfo;
import org.com.tianzmp.util.HttpRequestUtil;
import org.com.tianzmp.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@Component("WxUtils")
public class WxUtils {
	
	private final Logger log = LoggerFactory.getLogger(WxUtils.class);
	
	@Autowired
	RedisUtils redisUtils;
	
	/**
	 * 获取微信token
	 * token:是请求微信接口唯一票据
	 * @return
	 */
	public String getAccessToken() {
		try {
			String token = redisUtils.getRedis("ACCESS_TOKEN");
			if(StringUtils.isNotEmpty(token)) {
				return token;
			}
			String url = "https://api.weixin.qq.com/cgi-bin/token";
			Map<String,String> map = new HashMap<String, String>();
			map.put("grant_type","client_credential");
			map.put("appid",WxConstant.APP_ID);
			map.put("secret",WxConstant.APPSECRET);
			String result = HttpRequestUtil.getInstance().doGet(url, map,null);
			if(result == null) {
				return null;
			}
			JSONObject json = JSON.parseObject(result);
			String access_token = json.getString("access_token");
			Long expires_in = json.getLong("expires_in");
			redisUtils.setRedisTime("ACCESS_TOKEN", access_token, expires_in);
			return access_token;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("【微信接口】:获取微信AccessToken异常");
		}
		return null;
	}
	
	/**
	 * 根据code获取用户信息
	 * @param appId 微信
	 * @param secret 微信
	 * @param code 授权返回code
	 */
	public WeChatUserInfo getWeChatAuthO2UserInfo(String appId, String secret, String code) {
		if(StringUtils.isNotEmpty(code)) {
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
			Map<String,String> map = new HashMap<String, String>();
			map.put("grant_type","client_credential");
			map.put("appid",appId);
			map.put("secret",secret);
			map.put("code",code);
			map.put("grant_type","authorization_code");
			//获取网页授权access_token
            String accessTokenUrl = HttpRequestUtil.getInstance().doGet(url,map,null);
            JSONObject json = JSONObject.parseObject(accessTokenUrl);
            String access_token = json.getString("access_token");
            String openId = json.getString("openid");
            //根据access_token和openId获取用户信息
            String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openId + "&lang=zh_CN";
			Map<String,String> map1 = new HashMap<String, String>();
			map1.put("access_token",access_token);
			map1.put("openid",openId);
			map1.put("lang","zh_CN");
            String userInfoJson = HttpRequestUtil.getInstance().doGet(userInfoUrl,map1,null);
			WeChatUserInfo weChatUserInfo = JsonMapper.getInstance().fromJson(userInfoJson, WeChatUserInfo.class);
            return weChatUserInfo;
        }
        return null;

	}
}
