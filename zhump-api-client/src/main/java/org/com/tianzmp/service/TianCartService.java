package org.com.tianzmp.service;

import java.util.List;

import org.com.tianzmp.dto.TianCartDTO;
import org.com.tianzmp.entity.TianCart;
import org.com.tianzmp.vo.TianCartVO;

public interface TianCartService {
	
	
	/**根据作品id去加入购物车*/
	boolean save(Long user_id,Long goodsId,Integer num)throws Exception;
	/**购物车列表*/
	List<TianCartVO> pageInfoDTO(TianCartDTO tianCartDTO);

	TianCartVO findByGoodsId(Long goodsId);

	TianCartVO findById(Long id);

}
