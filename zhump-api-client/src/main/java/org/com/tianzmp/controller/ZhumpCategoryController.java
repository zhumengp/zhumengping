package org.com.tianzmp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.com.tianzmp.common.result.ZhumpResultBase;
import org.com.tianzmp.common.result.ZhumpResultStatus;
import org.com.tianzmp.service.ZhumpCategoryService;
import org.com.tianzmp.vo.ZhumpCategoryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 分类控制类
 * @author zhump
 *
 */
@Controller
@RequestMapping(value="category")
public class ZhumpCategoryController {

	private final Logger log = LoggerFactory.getLogger(ZhumpCategoryController.class);

	@Resource(name="tianCategoryService")
	private ZhumpCategoryService tianCategoryService;
	
	@RequestMapping(value="/insert")
	@ResponseBody
	public Object insert(Long pId,String name,Integer status) {
		try {
			boolean result = tianCategoryService.save(pId,name,status);
			if(result) {
				return new ZhumpResultBase(ZhumpResultStatus.SUCCESS, true);
			}else {
				return new ZhumpResultBase(ZhumpResultStatus.FALI, false);
			}
		} catch (Exception e) {
			log.error("系统异常",e);
			return new ZhumpResultBase(ZhumpResultStatus.ERROR, null);
		}
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list() {
		try {
			List<ZhumpCategoryVO> list = tianCategoryService.findByPid();
			return new ZhumpResultBase(ZhumpResultStatus.SUCCESS, list);
		} catch (Exception e) {
			log.error("系统异常",e);
			return new ZhumpResultBase(ZhumpResultStatus.ERROR, null);
		}
	}
}
