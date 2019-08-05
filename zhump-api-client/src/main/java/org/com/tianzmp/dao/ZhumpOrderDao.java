package org.com.tianzmp.dao;

import org.com.tianzmp.dto.ZhumpOrderDTO;
import org.com.tianzmp.vo.ZhumpOrderVO;

import java.util.List;

public interface ZhumpOrderDao {

    /**查询所有订单*/
    List<ZhumpOrderVO> selectAll(ZhumpOrderDTO tianOrderDTO);
    /**创建订单*/
    Integer insert(ZhumpOrderDTO tianOrderDTO);
    /**查询单个订单*/
    ZhumpOrderVO findById(Long id);
    /**修改订单*/
    Integer update(ZhumpOrderDTO tianOrderDTO);
    /**删除订单*/
    Integer delete(Long id);
}
