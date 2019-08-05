package org.com.tianzmp.service;

import java.util.List;

import org.com.tianzmp.dto.TianAddressDTO;
import org.com.tianzmp.dto.TianUserDTO;
import org.com.tianzmp.entity.TianAddress;
import org.com.tianzmp.exception.BusinessException;
import org.com.tianzmp.vo.TianUserVO;

public interface TianUserService {
	
	//新增一条数据
	boolean save(TianUserDTO tianUserDTO) throws Exception;
	
	//查询所有数据
	List<TianUserVO> getAll();
	
	//查询一条数据
	TianUserVO findById(Long id);

	//获取微信用户同时写入数据库
	boolean insertUserToAddress(TianUserDTO tianUserDTO, TianAddressDTO tianAddressDTO) throws Exception;
}
