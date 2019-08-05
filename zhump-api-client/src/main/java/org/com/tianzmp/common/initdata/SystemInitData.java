package org.com.tianzmp.common.initdata;

import java.util.List;

import javax.servlet.ServletContext;

import org.com.tianzmp.cache.RedisUtils;
import org.com.tianzmp.common.Constant;
import org.com.tianzmp.dto.ZhumpCategoryDTO;
import org.com.tianzmp.service.ZhumpCategoryService;
import org.com.tianzmp.util.wx.WxUtils;
import org.com.tianzmp.vo.ZhumpCategoryVO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

public class SystemInitData  implements InitializingBean, ServletContextAware{

	@Autowired
	private ZhumpCategoryService tianCategoryService;
	
	@Autowired
	RedisUtils redisUtils;
	
	@Autowired
	WxUtils wxUtils;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		ZhumpCategoryDTO tianCategoryDTO = new ZhumpCategoryDTO();
		List<ZhumpCategoryVO> list = tianCategoryService.selectAll(tianCategoryDTO);
		for(ZhumpCategoryVO tianCategoryVO : list) {
			tianCategoryDTO.setpId(tianCategoryVO.getId());
			List<ZhumpCategoryVO> childer_list = tianCategoryService.selectAll(tianCategoryDTO);
			tianCategoryVO.setChilder(childer_list);
		}
		redisUtils.setRedis_List(Constant.redisEnumKey.CATEGORY.getKey(), list);
		//缓存微信 access_token;
		//wxUtils.getAccessToken();
	}

}
