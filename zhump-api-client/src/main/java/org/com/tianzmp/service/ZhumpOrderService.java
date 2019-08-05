package org.com.tianzmp.service;

import org.com.tianzmp.dto.ZhumpOrderDTO;
import org.com.tianzmp.vo.ZhumpOrderVO;

import java.util.List;

/**
 * 订单操作服务层
 * @author zhump
 */
public interface ZhumpOrderService {

    /**查询所有订单*/
    List<ZhumpOrderVO> selectAll(ZhumpOrderDTO tianOrderDTO);
    /**创建订单*/
    boolean createOrder(Long userId,List<Long> cartIds) throws Exception;
    /**查询所有订单*/
    ZhumpOrderVO findById(Long id);
    /*取消订单*/
    boolean cancelOrder(Long id);
    /**删除订单*/
    boolean deleteOrder(Long id);

}
