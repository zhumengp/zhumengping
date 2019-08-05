package org.com.tianzmp.service.impl;

import java.util.List;

import org.com.tianzmp.dao.TianAddressDao;
import org.com.tianzmp.dto.TianAddressDTO;
import org.com.tianzmp.service.TianAddressService;
import org.com.tianzmp.vo.TianAddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tianAddressService")
public class TianAddressServiceImpl implements TianAddressService {
	
	
	@Autowired
	private TianAddressDao tianAddressDao;
	
	@Override
	public TianAddressVO findById(Long id) {
		TianAddressVO findById = tianAddressDao.findById(id);
		return findById;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean insert(TianAddressDTO tianAddressDTO) throws Exception{
		Integer result = tianAddressDao.insert(tianAddressDTO);
		System.out.println("result:"+result);
		return result > 0 ? true :false;
	}

	@Override
	public List<TianAddressVO> findByUserAddress(TianAddressDTO tianAddressDTO){
		List<TianAddressVO> list = tianAddressDao.findByUserAddress(tianAddressDTO);
		return list;
	}

	@Override
	public boolean update(TianAddressDTO tianAddressDTO) throws Exception {
		Integer result = tianAddressDao.update(tianAddressDTO);
		return result > 0 ? true : false;
	}

}
