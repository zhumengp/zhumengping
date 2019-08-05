package org.com.tianzmp.dao;

import org.com.tianzmp.dto.TianOrderDTO;
import org.com.tianzmp.vo.TianOrderVO;

import java.util.List;

public interface TianOrderDao {

    /**查询所有订单*/
    List<TianOrderVO> selectAll(TianOrderDTO tianOrderDTO);
    /**创建订单*/
    Integer insert(TianOrderDTO tianOrderDTO);
    /**查询单个订单*/
    TianOrderVO findById(Long id);
    /**修改订单*/
    Integer update(TianOrderDTO tianOrderDTO);
}
