package org.com.tianzmp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.com.tianzmp.cache.RedisUtils;
import org.com.tianzmp.common.Constant;
import org.com.tianzmp.dao.ZhumpCartDao;
import org.com.tianzmp.dto.ZhumpCartDTO;
import org.com.tianzmp.service.ZhumpCartService;
import org.com.tianzmp.service.ZhumpGoodsService;
import org.com.tianzmp.util.JsonMapper;
import org.com.tianzmp.vo.ZhumpCartVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;


@Service("tianCartService")
public class ZhumpCartServiceImpl implements ZhumpCartService {

	private final Logger log = LoggerFactory.getLogger(ZhumpCartServiceImpl.class);

	@Autowired
	private ZhumpCartDao tianCartDao;
	
	@Resource(name="tianGoodsService")
	private ZhumpGoodsService tianGoodsService;
	
	@Autowired
	RedisUtils redisUtils;
	
	
	@Override
	public boolean save(Long user_id,Long goodsId, Integer goodsNum) throws Exception{
		ZhumpCartVO zhumpCartVO = this.findByGoodsId(goodsId,user_id);
		if(zhumpCartVO != null) {
			ZhumpCartDTO zhumpCartDTO = new ZhumpCartDTO();
			zhumpCartDTO.setGoodsId(goodsId);
			zhumpCartDTO.setGoodsNum(goodsNum);
			Integer result = tianCartDao.updateCartGoodsNum(zhumpCartDTO);
			if (result < 0){
				log.error("【购物车处理】:修改购物车商品数量失败");
				return false;
			}
		//如果不存在，直接新增	
		}else {
			ZhumpCartDTO tianCartDTO2 = new ZhumpCartDTO();
			tianCartDTO2.setGoodsId(goodsId);
			tianCartDTO2.setGoodsNum(goodsNum);
			tianCartDTO2.setUserId(user_id);
			Integer result = tianCartDao.insert(tianCartDTO2);
			if(result < 0 ) {
				log.error("【购物车处理】:加入购物车失败");
				return false;
			}
			redisUtils.delRedis(Constant.redisEnumKey.USER_CART.getKey()+user_id);
			return true;
		}
		return true;
	}


	@Override
	public List<ZhumpCartVO> pageInfoDTO(ZhumpCartDTO zhumpCartDTO){
		Integer count = tianCartDao.count(zhumpCartDTO);
		List<ZhumpCartVO> list = new ArrayList<ZhumpCartVO>();
		String redis_List = redisUtils.getRedis_List(Constant.redisEnumKey.USER_CART.getKey()+zhumpCartDTO.getUserId());
		if(StringUtils.isBlank(redis_List) && count > 0) {
			list = tianCartDao.pageInfoDTO(zhumpCartDTO);
			redisUtils.setRedis_List(Constant.redisEnumKey.USER_CART.getKey()+zhumpCartDTO.getUserId(), list);
		}else {
			list = JsonMapper.getInstance().fromJson(redis_List, JsonMapper.getInstance().createCollectionType(List.class, ZhumpCartVO.class));
			
		}
		return list;
	}

	@Override
	public ZhumpCartVO findByGoodsId(Long goodsId,Long userId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("goodsId",goodsId);
		map.put("userId",userId);
		ZhumpCartVO tianCartVO = tianCartDao.findByGoodsId(map);
		return tianCartVO;
	}

	@Override
	public ZhumpCartVO findById(Long id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id",id);
		return tianCartDao.findById(map);
	}
}
