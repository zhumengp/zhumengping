package org.com.tianzmp.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.com.tianzmp.common.result.ZhumpResultBase;
import org.com.tianzmp.common.result.ZhumpResultStatus;
import org.com.tianzmp.dto.ZhumpAddressDTO;
import org.com.tianzmp.dto.ZhumpUserDTO;
import org.com.tianzmp.entity.response.ResponseWeChatUserInfo;
import org.com.tianzmp.exception.BusinessException;
import org.com.tianzmp.joggle.third.response.WeChatUserInfo;
import org.com.tianzmp.service.ZhumpUserService;
import org.com.tianzmp.util.wx.WxConstant;
import org.com.tianzmp.util.wx.WxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.net.URLEncoder;


/**
 * 微信工具类
 * @author zhump
 *
 */
@Controller
@RequestMapping(value="weChat")
public class ZhumpWeChatControoler {

	private final Logger log = LoggerFactory.getLogger(ZhumpWeChatControoler.class);

	@Resource(name="tianUserService")
	private ZhumpUserService tianUserService;


	@Autowired
	WxUtils wxUtils;


	/***
	 * 微信获取code
	 */
	@RequestMapping(value = "/accreditUrl",method = RequestMethod.GET)
	@ResponseBody
	public Object getAccreditUrl() {
		String callbackUrl = "http://tianzmp.free.idcfengye.com/api-client/weChat/weChatUserInfo.action";
		try {
			String	url = "https://open.weixin.qq.com/connect/oauth2/authorize"
					+ "?appid="+WxConstant.APP_ID
					+ "&redirect_uri="+URLEncoder.encode(callbackUrl,"utf-8")
					+ "&response_type=code"
					+ "&scope=snsapi_userinfo"
					+ "&state=STATE#wechat_redirect";
			return new ZhumpResultBase(ZhumpResultStatus.SUCCESS, url);
		} catch (Exception e) {
			log.error("获取回调地址异常",e);
			return new ZhumpResultBase(ZhumpResultStatus.ERROR, null);
		}
	}


	/**
	 * 根据code获取用户信息
	 */
	@RequestMapping(value = "/weChatUserInfo",method = RequestMethod.GET)
	@ResponseBody
	public Object getUserInfo(String code) {
		if(StringUtils.isBlank(code)) {
			return new ZhumpResultBase(ZhumpResultStatus.PARMSERROR, null);
		}
		try {
			WeChatUserInfo weChatUserInfo = wxUtils.getWeChatAuthO2UserInfo(WxConstant.APP_ID, WxConstant.APPSECRET, code);
			ZhumpUserDTO tianUserDTO = new ZhumpUserDTO();
			ZhumpAddressDTO tianAddressDTO = new ZhumpAddressDTO();
			tianUserDTO.setImg(weChatUserInfo.getHeadimgurl());
			tianUserDTO.setOpenId(weChatUserInfo.getOpenid());
			tianUserDTO.setName(weChatUserInfo.getNickname());
			tianUserDTO.setSex(weChatUserInfo.getSex());
			tianAddressDTO.setCity(weChatUserInfo.getCity());
			tianAddressDTO.setProvince(weChatUserInfo.getProvince());
			tianAddressDTO.setAddress(weChatUserInfo.getCity());
			ResponseWeChatUserInfo responseWeChatUserInfo = new ResponseWeChatUserInfo();
			boolean save = tianUserService.insertUserToAddress(tianUserDTO,tianAddressDTO);
			BeanUtils.copyProperties(tianUserDTO, responseWeChatUserInfo);
			if(save) {
				return new ZhumpResultBase(ZhumpResultStatus.SUCCESS, responseWeChatUserInfo);
			}
			return new ZhumpResultBase(ZhumpResultStatus.FALI, null);
		} catch (Exception e) {
			log.error("系统异常",e);
			if (e instanceof BusinessException){
				return new ZhumpResultBase(ZhumpResultStatus.FALI, e.getMessage());
			}
			return new ZhumpResultBase(ZhumpResultStatus.ERROR, null);
		}
	}
	/**
	 * 测试添加页面
	 * @return
	 */
	@RequestMapping(value="/add",method =  RequestMethod.GET)
	public String add() {
		return "wx/test";
	}

}
