package org.com.tianzmp.service;

import org.com.tianzmp.dto.TianOrderDTO;
import org.com.tianzmp.vo.TianOrderVO;

import java.util.List;

/**
 * 订单操作服务层
 * @author zhump
 */
public interface TianOrderService {

    /**查询所有订单*/
    List<TianOrderVO> selectAll(TianOrderDTO tianOrderDTO);
    /**创建订单*/
    boolean createOrder(Long userId,List<Long> cartIds) throws Exception;
    /**查询所有订单*/
    TianOrderVO findById(Long id);
    /**修改订单*/
    Integer update(TianOrderDTO tianOrderDTO);

}
