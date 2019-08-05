package org.com.tianzmp.service;

import org.com.tianzmp.vo.TianBannerVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TianBannerService {


    /**查询所有轮播图*/
    List<TianBannerVO> selectAll();
}
