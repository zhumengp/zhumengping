package org.com.tianzmp.service;

import org.com.tianzmp.dto.TianOrderDetailDTO;
import org.com.tianzmp.entity.TianOrderDetail;
import org.com.tianzmp.vo.TianOrderDetailVO;

import java.util.List;

public interface TianOrderDetailService {


    /**查询用户订单列表*/
    List<TianOrderDetailVO> selectAll(Long userId);

    /**查询单个订单明细*/
    List<TianOrderDetailVO> findByOrderId(Long orderId);

}
