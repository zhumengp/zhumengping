package org.com.tianzmp.dao;

import org.com.tianzmp.vo.TianBannerVO;

import java.util.List;

public interface TianBannerDao {

    /**查询所有轮播图*/
    List<TianBannerVO> selectAll();
}
