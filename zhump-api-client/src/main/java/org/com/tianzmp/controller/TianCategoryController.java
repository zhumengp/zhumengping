package org.com.tianzmp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.com.tianzmp.common.result.Result;
import org.com.tianzmp.common.result.ResultStatus;
import org.com.tianzmp.dto.TianCategoryDTO;
import org.com.tianzmp.service.TianCategoryService;
import org.com.tianzmp.vo.TianCategoryVO;
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
public class TianCategoryController {

	private final Logger log = LoggerFactory.getLogger(TianCategoryController.class);

	@Resource(name="tianCategoryService")
	private TianCategoryService tianCategoryService;
	
	@RequestMapping(value="/insert")
	@ResponseBody
	public Object insert(Long pId,String name,Integer status) {
		try {
			boolean result = tianCategoryService.save(pId,name,status);
			if(result) {
				return new Result(ResultStatus.SUCCESS, true);
			}else {
				return new Result(ResultStatus.FALI, false);
			}
		} catch (Exception e) {
			log.error("系统异常",e);
			return new Result(ResultStatus.ERROR, null);
		}
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list() {
		try {
			List<TianCategoryVO> list = tianCategoryService.findByPid();
			return new Result(ResultStatus.SUCCESS, list);
		} catch (Exception e) {
			log.error("系统异常",e);
			return new Result(ResultStatus.ERROR, null);
		}
	}
}
