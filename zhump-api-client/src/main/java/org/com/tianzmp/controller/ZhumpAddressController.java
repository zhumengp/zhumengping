package org.com.tianzmp.controller;

import javax.annotation.Resource;

import org.com.tianzmp.common.result.ZhumpResultBase;
import org.com.tianzmp.common.result.ZhumpResultStatus;
import org.com.tianzmp.dto.ZhumpAddressDTO;
import org.com.tianzmp.service.ZhumpAddressService;
import org.com.tianzmp.vo.ZhumpAddressVO;
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
public class ZhumpAddressController {
	
	private final Logger log = LoggerFactory.getLogger(ZhumpAddressController.class);
	
	@Resource(name="tianAddressService")
	private ZhumpAddressService tianAddressService;

	/**
	 * 地址列表
	 */
	@RequestMapping(value="/list",method =  RequestMethod.GET)
	@ResponseBody
	public Object list(ZhumpAddressDTO zhumpAddressDTO) {
		try {
			if(zhumpAddressDTO.getPage() == null) {
				return new ZhumpResultBase(ZhumpResultStatus.PARMSERROR, null);
			}
			List<ZhumpAddressVO> list = tianAddressService.findByUserAddress(zhumpAddressDTO);
			return new ZhumpResultBase(ZhumpResultStatus.SUCCESS, list);
		} catch (Exception e) {
			log.error("系统异常",e);
			return new ZhumpResultBase(ZhumpResultStatus.ERROR, null);
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
	public Object insert(ZhumpAddressDTO tianAddressDTO) {
		try {
			if(tianAddressDTO.getAddress()== null
					|| tianAddressDTO.getCity() == null
					|| tianAddressDTO.getProvince() == null
					|| tianAddressDTO.getPage() == null) {
				return new ZhumpResultBase(ZhumpResultStatus.PARMSERROR, null);
			}
			Boolean result = tianAddressService.insert(tianAddressDTO);
			return new ZhumpResultBase(ZhumpResultStatus.SUCCESS, result);
		} catch (Exception e) {
			log.error("系统异常",e);
			return new ZhumpResultBase(ZhumpResultStatus.ERROR, null);
		}
	}
	
	/**
	 * 修改地址
	 * @param tianAddressDTO
	 * @return
	 */
	@RequestMapping(value="/edit",method =  RequestMethod.POST)
	@ResponseBody
	public Object edit(ZhumpAddressDTO tianAddressDTO) {
		try {
			if(tianAddressDTO.getUserId() == null || tianAddressDTO.getId() == null) {
				return new ZhumpResultBase(ZhumpResultStatus.PARMSERROR, null);
			}
			boolean result = tianAddressService.update(tianAddressDTO);
			if (!result){
				return new ZhumpResultBase(ZhumpResultStatus.FALI, null);
			}
			return new ZhumpResultBase(ZhumpResultStatus.SUCCESS, null);
		} catch (Exception e) {
			log.error("系统异常",e);
			return new ZhumpResultBase(ZhumpResultStatus.ERROR, null);
		}
	}
}
