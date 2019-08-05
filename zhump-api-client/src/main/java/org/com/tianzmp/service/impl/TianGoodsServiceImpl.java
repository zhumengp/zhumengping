package org.com.tianzmp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.com.tianzmp.base.PageInfo;
import org.com.tianzmp.cache.RedisUtils;
import org.com.tianzmp.common.Constant;
import org.com.tianzmp.dao.TianGoodsDao;
import org.com.tianzmp.dto.TianGoodsDTO;
import org.com.tianzmp.service.TianGoodsService;
import org.com.tianzmp.util.IdUtils;
import org.com.tianzmp.util.JsonMapper;
import org.com.tianzmp.vo.TianGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import org.springframework.transaction.annotation.Transactional;


@Service("tianGoodsService")
public class TianGoodsServiceImpl implements TianGoodsService{
	
	@Autowired
	private TianGoodsDao tianGoodsDao;
	
	@Autowired
	RedisUtils redisUtils;


	@Override
	public TianGoodsVO findById(Long id){
		String  result = redisUtils.getRedis("goods_"+id);
		if(StringUtil.isEmpty(result)) {
			TianGoodsVO tianGoodsVO = tianGoodsDao.findById(id);
			redisUtils.setRedis(Constant.redisEnumKey.GOODS.getKey()+id, JsonMapper.getInstance().toJson(tianGoodsVO));
			return tianGoodsVO; 
		}
		TianGoodsVO tianGoodsVO = JsonMapper.getInstance().fromJson(result, TianGoodsVO.class);
		return tianGoodsVO; 
	}


	@Override
	public PageInfo<TianGoodsVO> pageInfoDTO(TianGoodsDTO tianGoodsDTO)throws Exception {
		Integer count = tianGoodsDao.count(tianGoodsDTO);
		List<TianGoodsVO> list = new ArrayList<TianGoodsVO>();
		//缓存拿数据
		String redis_List = redisUtils.hGetRedis(Constant.redisEnumKey.GOODS_LIST.getKey(), tianGoodsDTO.getPage().toString());
		if(StringUtil.isEmpty(redis_List) && count > 0) {
			list = tianGoodsDao.pageInfoDTO(tianGoodsDTO);
			redisUtils.hSetRedis(Constant.redisEnumKey.GOODS_LIST.getKey(), tianGoodsDTO.getPage().toString(), list);
		}else {
			list = JsonMapper.getInstance().fromJson(redis_List, JsonMapper.getInstance().createCollectionType(List.class, TianGoodsVO.class));
		}
		PageInfo<TianGoodsVO> pg = new PageInfo<TianGoodsVO>();
		pg.setCurrentPage(tianGoodsDTO.getPage());
		pg.setData(list);
		pg.setTotalCount(count);
		pg.setPageSize(tianGoodsDTO.getRows());
		return pg;
	}


	@Override
	public Boolean insert(TianGoodsDTO tianGoodsDTO) throws Exception {
		tianGoodsDTO.setId(IdUtils.getInstance().nextId());
		Integer result = tianGoodsDao.insert(tianGoodsDTO);
		if(result > 0) {
			TianGoodsVO findById = tianGoodsDao.findById(tianGoodsDTO.getId());
			//缓存单个商品
			redisUtils.setRedis(Constant.redisEnumKey.GOODS.getKey()+tianGoodsDTO.getId(), JsonMapper.getInstance().toJson(findById));
			//删除所有商品
			redisUtils.delRedis(Constant.redisEnumKey.GOODS_LIST.getKey());
		}
		return result > 0 ? true : false;
	}


	@Override
	public boolean edit(String name, Integer num, String picture, BigDecimal price, Long categoryId) throws Exception {
		TianGoodsDTO tianGoodsDTO = new TianGoodsDTO();
		tianGoodsDTO.setName(name);
		tianGoodsDTO.setPrice(price);
		tianGoodsDTO.setPicture(picture);
		tianGoodsDTO.setInventory(num);
		tianGoodsDTO.setCategoryId(categoryId);
		Integer result = tianGoodsDao.update(tianGoodsDTO);
		return result > 0 ? true : false;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean addGoodsNum(Integer num, Long goodsId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("goodsNum",num);
		map.put("id",goodsId);
		Integer result = tianGoodsDao.addGoodsNum(map);
		return result > 0 ? true : false;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean reduceGoodsNum(Integer num, Long goodsId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("goodsNum",num);
		map.put("id",goodsId);
		Integer result = tianGoodsDao.reduceGoodsNum(map);
		return result > 0 ? true : false;
	}

}
