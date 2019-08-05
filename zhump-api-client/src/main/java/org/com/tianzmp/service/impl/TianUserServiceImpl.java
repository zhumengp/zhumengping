package org.com.tianzmp.service.impl;

import java.util.List;

import org.com.tianzmp.dao.TianUserDao;
import org.com.tianzmp.dto.TianAddressDTO;
import org.com.tianzmp.dto.TianUserDTO;
import org.com.tianzmp.exception.BusinessException;
import org.com.tianzmp.service.TianAddressService;
import org.com.tianzmp.service.TianUserService;
import org.com.tianzmp.vo.TianUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("tianUserService")
public class TianUserServiceImpl implements TianUserService{

	private final Logger log = LoggerFactory.getLogger(TianUserServiceImpl.class);
	
	@Autowired
	private TianUserDao tianUserDao;

	@Autowired
	private TianAddressService tianAddressService;

	@Override
	public boolean save(TianUserDTO tianUserDTO)throws Exception {
		Integer insert = tianUserDao.insert(tianUserDTO);
		return insert > 0  ? true : false;
	}

	@Override
	public List<TianUserVO> getAll() {
		List<TianUserVO> list = tianUserDao.getAll();
		return list;
	}

	@Override
	public TianUserVO findById(Long id) {
		TianUserVO tianUser = tianUserDao.findById(id);
		return tianUser;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean insertUserToAddress(TianUserDTO tianUserDTO, TianAddressDTO tianAddressDTO) throws Exception{
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
