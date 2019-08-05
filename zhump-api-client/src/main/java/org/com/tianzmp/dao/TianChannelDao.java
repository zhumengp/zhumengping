package org.com.tianzmp.dao;

import org.com.tianzmp.vo.TianChannelVO;

import java.util.List;

/**
 * 频道管理
 */
public interface TianChannelDao {

    /**查询所有频道*/
    List<TianChannelVO> selectAll();
}
