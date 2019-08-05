package org.com.tianzmp.service.impl;

import java.util.List;

import org.com.tianzmp.dao.ZhumpUserDao;
import org.com.tianzmp.dto.ZhumpAddressDTO;
import org.com.tianzmp.dto.ZhumpUserDTO;
import org.com.tianzmp.exception.BusinessException;
import org.com.tianzmp.service.ZhumpAddressService;
import org.com.tianzmp.service.ZhumpUserService;
import org.com.tianzmp.vo.ZhumpUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("tianUserService")
public class ZhumpUserServiceImpl implements ZhumpUserService {

	private final Logger log = LoggerFactory.getLogger(ZhumpUserServiceImpl.class);
	
	@Autowired
	private ZhumpUserDao tianUserDao;

	@Autowired
	private ZhumpAddressService tianAddressService;

	@Override
	public boolean save(ZhumpUserDTO tianUserDTO)throws Exception {
		Integer insert = tianUserDao.insert(tianUserDTO);
		return insert > 0  ? true : false;
	}

	@Override
	public List<ZhumpUserVO> getAll() {
		List<ZhumpUserVO> list = tianUserDao.getAll();
		return list;
	}

	@Override
	public ZhumpUserVO findById(Long id) {
		ZhumpUserVO tianUser = tianUserDao.findById(id);
		return tianUser;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean insertUserToAddress(ZhumpUserDTO tianUserDTO, ZhumpAddressDTO tianAddressDTO) throws Exception{
		Integer insert = tianUserDao.insert(tianUserDTO);
		if (insert < 0){
			log.error("【微信处理】：微信用户基本信息入库失败");
			return false;
		}
		tianAddressDTO.setUserId(tianUserDTO.getId());
		boolean result = tianAddressService.insert(tianAddressDTO);
		if (!result){
			log.error("【地址处理】：用户地址基本信息入库失败");
			throw  new BusinessException("新增地址数据失败");
		}
		return result;
	}
}
