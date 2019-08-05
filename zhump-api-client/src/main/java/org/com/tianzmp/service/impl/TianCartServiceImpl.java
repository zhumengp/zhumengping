package org.com.tianzmp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.com.tianzmp.base.PageInfo;
import org.com.tianzmp.cache.RedisUtils;
import org.com.tianzmp.common.Constant;
import org.com.tianzmp.dao.TianCartDao;
import org.com.tianzmp.dto.TianCartDTO;
import org.com.tianzmp.service.TianCartService;
import org.com.tianzmp.service.TianGoodsService;
import org.com.tianzmp.util.IdUtils;
import org.com.tianzmp.util.JsonMapper;
import org.com.tianzmp.vo.TianCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;


@Service("tianCartService")
public class TianCartServiceImpl implements TianCartService{

	@Autowired
	private TianCartDao tianCartDao;
	
	@Resource(name="tianGoodsService")
	private TianGoodsService tianGoodsService;
	
	@Autowired
	RedisUtils redisUtils;
	
	
	@Override
	public boolean save(Long user_id,Long goodsId, Integer num) throws Exception{
		TianCartDTO tianCartDTO = new TianCartDTO();
		tianCartDTO.setGoodsId(goodsId);
		Integer result = 0;
		List<TianCartVO> list = tianCartDao.selectAll(tianCartDTO);
		if(CollectionUtils.isNotEmpty(list)) {
			TianCartVO tianCartVO = list.get(0);
			Integer goodsNum = tianCartVO.getGoodsNum() + num;
			TianCartDTO tianCartDTO1 = new TianCartDTO();
			tianCartDTO1.setId(tianCartVO.getId());
			tianCartDTO1.setGoodsNum(goodsNum);
			result = tianCartDao.update(tianCartDTO1);
		//如果不存在，直接新增	
		}else {
			TianCartDTO tianCartDTO2 = new TianCartDTO();
			tianCartDTO2.setGoodsId(goodsId);
			tianCartDTO2.setGoodsNum(num);
			tianCartDTO2.setUserId(user_id);
			result = tianCartDao.insert(tianCartDTO2);
		}
		if(result > 0 ) {
			redisUtils.delRedis(Constant.redisEnumKey.USER_CART.getKey()+user_id);
		}
		return result > 0 ? true : false;
	}


	@Override
	public List<TianCartVO> pageInfoDTO(TianCartDTO tianCartDTO){
		Integer count = tianCartDao.count(tianCartDTO);
		List<TianCartVO> list = new ArrayList<TianCartVO>();
		String redis_List = redisUtils.getRedis_List(Constant.redisEnumKey.USER_CART.getKey()+tianCartDTO.getUserId());
		if(StringUtils.isBlank(redis_List) && count > 0) {
			list = tianCartDao.pageInfoDTO(tianCartDTO);
			redisUtils.setRedis_List(Constant.redisEnumKey.USER_CART.getKey()+tianCartDTO.getUserId(), list);
		}else {
			list = JsonMapper.getInstance().fromJson(redis_List, JsonMapper.getInstance().createCollectionType(List.class, TianCartVO.class));
			
		}
		return list;
	}

	@Override
	public TianCartVO findByGoodsId(Long goodsId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("goodsId",goodsId);
		TianCartVO tianCartVO = tianCartDao.findById(map);
		return tianCartVO;
	}

	@Override
	public TianCartVO findById(Long id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id",id);
		return tianCartDao.findById(map);
	}
}
