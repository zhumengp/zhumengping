package org.com.tianzmp.service;

import org.com.tianzmp.vo.TianChannelVO;

import java.util.List;

public interface TianChannelService {

    /**查询所有频道*/
    List<TianChannelVO> selectAll();
}
