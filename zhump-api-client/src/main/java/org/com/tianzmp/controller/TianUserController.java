package org.com.tianzmp.controller;

import javax.annotation.Resource;

import org.com.tianzmp.common.result.Result;
import org.com.tianzmp.common.result.ResultStatus;
import org.com.tianzmp.dto.TianAddressDTO;
import org.com.tianzmp.dto.TianUserDTO;
import org.com.tianzmp.exception.BusinessException;
import org.com.tianzmp.service.TianUserService;
import org.com.tianzmp.vo.TianUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value="user")
public class TianUserController {
	
	private final Logger log = LoggerFactory.getLogger(TianUserController.class);
	
	@Resource(name="tianUserService")
	private TianUserService tianUserService;
	
	@RequestMapping(value="/info")
	@ResponseBody
	public Object info(Long id) {
		try {
			TianUserVO tianUsre = tianUserService.findById(id);
			return new Result(ResultStatus.SUCCESS,tianUsre);
		} catch (Exception e) {
			log.error("系统异常",e);
			return new Result(ResultStatus.ERROR, null);
		}
	}

	//新增用户和地址测试
	@RequestMapping(value="/addUserToAddress")
	@ResponseBody
	public Object addUserToAddress(){
		try {
			TianUserDTO tianUserDTO = new TianUserDTO();
			TianAddressDTO tianAddressDTO = new TianAddressDTO();
			tianUserDTO.setImg("123");
			tianUserDTO.setOpenId("12");
			tianUserDTO.setName("昵称");
			tianUserDTO.setSex(1);
			tianAddressDTO.setCity("深圳");
			tianAddressDTO.setProvince("是的");
			tianAddressDTO.setAddress("123");
			boolean save = tianUserService.insertUserToAddress(tianUserDTO,tianAddressDTO);
			return new Result(ResultStatus.SUCCESS,save);
		} catch (Exception e) {
			log.error("系统异常",e);
			if (e instanceof BusinessException){
				return new Result(ResultStatus.FALI,e.getMessage());
			}
			return new Result(ResultStatus.ERROR, null);
		}
	}

}
