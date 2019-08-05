package org.com.tianzmp.service;

import java.util.List;

import org.com.tianzmp.dto.ZhumpCartDTO;
import org.com.tianzmp.vo.ZhumpCartVO;

public interface ZhumpCartService {
	
	
	/**根据作品id去加入购物车*/
	boolean save(Long user_id,Long goodsId,Integer num)throws Exception;
	/**购物车列表*/
	List<ZhumpCartVO> pageInfoDTO(ZhumpCartDTO tianCartDTO);

	ZhumpCartVO findByGoodsId(Long goodsId,Long userId);

	ZhumpCartVO findById(Long id);

}
