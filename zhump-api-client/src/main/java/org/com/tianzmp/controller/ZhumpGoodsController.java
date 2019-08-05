package org.com.tianzmp.controller;

import javax.annotation.Resource;

import org.com.tianzmp.base.PageInfo;
import org.com.tianzmp.common.result.Result;
import org.com.tianzmp.common.result.ResultStatus;
import org.com.tianzmp.dto.ZhumpGoodsDTO;
import org.com.tianzmp.service.ZhumpGoodsService;
import org.com.tianzmp.vo.ZhumpGoodsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="goods")
public class ZhumpGoodsController {
	
	private final Logger log = LoggerFactory.getLogger(ZhumpGoodsController.class);
	
	@Resource(name="tianGoodsService")
	private ZhumpGoodsService tianGoodsService;
	
	/**
	 * 商品列表
	 */
	@RequestMapping(value="/list",method =  RequestMethod.GET)
	@ResponseBody
	public Object list(ZhumpGoodsDTO zhumpGoodsDTO) {

		try {
			if(zhumpGoodsDTO.getPage() == null) {
				return new Result(ResultStatus.PARMSERROR, null);
			}
			PageInfo<ZhumpGoodsVO> list = tianGoodsService.pageInfoDTO(zhumpGoodsDTO);
			return new Result(ResultStatus.SUCCESS, list);
		} catch (Exception e) {
			log.error("系统异常",e);
			return new Result(ResultStatus.ERROR, null);
		}
	}
	
	
	/**
	 * 测试添加页面
	 * @return
	 */
	@RequestMapping(value="/add",method =  RequestMethod.GET)
	public String add() {
		return "goods/add";
	}
	
	/**
	 * 测试添加商品
	 * @param tianGoodsDTO
	 * @return
	 */
	@RequestMapping(value="/insert",method =  RequestMethod.POST)
	@ResponseBody
	public Object insert(ZhumpGoodsDTO tianGoodsDTO) {
		try {
			if(tianGoodsDTO.getName() == null || tianGoodsDTO.getPrice() == null || tianGoodsDTO.getInventory() == null || tianGoodsDTO.getPage() == null) {
				return new Result(ResultStatus.PARMSERROR, null);
			}
			Boolean result = tianGoodsService.insert(tianGoodsDTO);
			return new Result(ResultStatus.SUCCESS, result);
		} catch (Exception e) {
			log.error("系统异常",e);
			return new Result(ResultStatus.ERROR, null);
		}
	}
	
	@RequestMapping(value="/info",method =  RequestMethod.GET)
	@ResponseBody
	public Object info(Long id) {
		try {
			if(id == null) {
				return new Result(ResultStatus.PARMSERROR, null);
			}
			ZhumpGoodsVO tianGoodsVO = tianGoodsService.findById(id);
			return new Result(ResultStatus.SUCCESS, tianGoodsVO);
		} catch (Exception e) {
			log.error("系统异常",e);
			return new Result(ResultStatus.ERROR, null);
		}
	}
	
	
}
