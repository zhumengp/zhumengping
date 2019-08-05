package org.com.tianzmp.dao;

import org.com.tianzmp.vo.ZhumpBannerVO;

import java.util.List;

public interface ZhumpBannerDao {

    /**查询所有轮播图*/
    List<ZhumpBannerVO> selectAll();
}
