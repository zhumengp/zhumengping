package org.com.tianzmp.service;

import org.com.tianzmp.vo.ZhumpBannerVO;

import java.util.List;


public interface ZhumpBannerService {


    /**查询所有轮播图*/
    List<ZhumpBannerVO> selectAll();
}
