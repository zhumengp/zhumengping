package org.com.tianzmp.controller;

import javax.annotation.Resource;

import org.com.tianzmp.base.PageInfo;
import org.com.tianzmp.common.result.Result;
import org.com.tianzmp.common.result.ResultStatus;
import org.com.tianzmp.dto.TianAddressDTO;
import org.com.tianzmp.service.TianAddressService;
import org.com.tianzmp.vo.TianAddressVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 地址控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="address")
public class TianAddressController {
	
	private final Logger log = LoggerFactory.getLogger(TianAddressController.class);
	
	@Resource(name="tianAddressService")
	private TianAddressService tianAddressService;

	/**
	 * 地址列表
	 */
	@RequestMapping(value="/list",method =  RequestMethod.GET)
	@ResponseBody
	public Object list(TianAddressDTO tianAddressDTO) {
		try {
			if(tianAddressDTO.getPage() == null) {
				return new Result(ResultStatus.PARMSERROR, null);
			}
			List<TianAddressVO> list = tianAddressService.findByUserAddress(tianAddressDTO);
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
		return "address/add";
	}
	
	/**
	 * 添加地址
	 * @param tianAddressDTO
	 * @return
	 */
	@RequestMapping(value="/insert",method =  RequestMethod.POST)
	@ResponseBody
	public Object insert(TianAddressDTO tianAddressDTO) {
		try {
			if(tianAddressDTO.getAddress()== null
					|| tianAddressDTO.getCity() == null
					|| tianAddressDTO.getProvince() == null
					|| tianAddressDTO.getPage() == null) {
				return new Result(ResultStatus.PARMSERROR, null);
			}
			Boolean result = tianAddressService.insert(tianAddressDTO);
			return new Result(ResultStatus.SUCCESS, result);
		} catch (Exception e) {
			log.error("系统异常",e);
			return new Result(ResultStatus.ERROR, null);
		}
	}
	
	/**
	 * 修改地址
	 * @param tianAddressDTO
	 * @return
	 */
	@RequestMapping(value="/edit",method =  RequestMethod.POST)
	@ResponseBody
	public Object edit(TianAddressDTO tianAddressDTO) {
		try {
			if(tianAddressDTO.getUserId() == null || tianAddressDTO.getId() == null) {
				return new Result(ResultStatus.PARMSERROR, null);
			}
			boolean result = tianAddressService.update(tianAddressDTO);
			if (!result){
				return new Result(ResultStatus.FALI, null);
			}
			return new Result(ResultStatus.SUCCESS, null);
		} catch (Exception e) {
			log.error("系统异常",e);
			return new Result(ResultStatus.ERROR, null);
		}
	}
}
