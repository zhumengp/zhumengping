package org.com.tianzmp.controller.wx;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeCahtCheckTokenController {

	private final Logger log = LoggerFactory.getLogger(WeCahtCheckTokenController.class);

	/**
	 * 在微信端校验令牌
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/checkToken",method = RequestMethod.GET)
	@ResponseBody
	public void checkToken(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if(StringUtils.isBlank(signature) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(nonce) || StringUtils.isBlank(echostr)){
			out.print("微信返回参数为空");
		}
		List<String> list = new ArrayList<String>();
		list.add("zhump");
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);
		StringBuffer sb = new StringBuffer();
		for(String string : list) {
			sb.append(string);
		}
		String str = DigestUtils.sha1Hex(sb.toString());
		boolean flag = str.equals(signature);
		if(flag) {
			log.info("【微信配置】接入微信公众平台成功");
			out.print(echostr);
			out.flush();
		}else{
			log.error("【微信配置】接入微信公众平台失败");
		}
	}
}
