package org.com.tianzmp.service;

import java.util.List;

import org.com.tianzmp.dto.ZhumpAddressDTO;
import org.com.tianzmp.dto.ZhumpUserDTO;
import org.com.tianzmp.vo.ZhumpUserVO;

public interface ZhumpUserService {
	
	//新增一条数据
	boolean save(ZhumpUserDTO tianUserDTO) throws Exception;
	
	//查询所有数据
	List<ZhumpUserVO> getAll();
	
	//查询一条数据
	ZhumpUserVO findById(Long id);

	//获取微信用户同时写入数据库
	boolean insertUserToAddress(ZhumpUserDTO tianUserDTO, ZhumpAddressDTO tianAddressDTO) throws Exception;
}
