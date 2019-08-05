package org.com.tianzmp.service.impl;

import java.util.List;

import org.com.tianzmp.dao.ZhumpAddressDao;
import org.com.tianzmp.dto.ZhumpAddressDTO;
import org.com.tianzmp.service.ZhumpAddressService;
import org.com.tianzmp.vo.ZhumpAddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tianAddressService")
public class ZhumpAddressServiceImpl implements ZhumpAddressService {
	
	
	@Autowired
	private ZhumpAddressDao tianAddressDao;
	
	@Override
	public ZhumpAddressVO findById(Long id) {
		ZhumpAddressVO findById = tianAddressDao.findById(id);
		return findById;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean insert(ZhumpAddressDTO tianAddressDTO) throws Exception{
		Integer result = tianAddressDao.insert(tianAddressDTO);
		System.out.println("result:"+result);
		return result > 0 ? true :false;
	}

	@Override
	public List<ZhumpAddressVO> findByUserAddress(ZhumpAddressDTO tianAddressDTO){
		List<ZhumpAddressVO> list = tianAddressDao.findByUserAddress(tianAddressDTO);
		return list;
	}

	@Override
	public boolean update(ZhumpAddressDTO tianAddressDTO) throws Exception {
		Integer result = tianAddressDao.update(tianAddressDTO);
		return result > 0 ? true : false;
	}

}
