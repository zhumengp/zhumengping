package org.com.tianzmp.service;

import org.com.tianzmp.vo.ZhumpOrderDetailVO;

import java.util.List;

public interface ZhumpOrderDetailService {


    /**查询用户订单列表*/
    List<ZhumpOrderDetailVO> selectAll(Long userId);

    /**查询单个订单明细*/
    List<ZhumpOrderDetailVO> findByOrderId(Long orderId);

}
