package org.com.tianzmp.controller;

import org.com.tianzmp.common.result.Result;
import org.com.tianzmp.common.result.ResultStatus;
import org.com.tianzmp.service.ZhumpBannerService;
import org.com.tianzmp.vo.ZhumpBannerVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 轮播图控制类
 */
@Controller
@RequestMapping(value="banner")
public class ZhumpBannerController {

    private final Logger log = LoggerFactory.getLogger(ZhumpBannerController.class);

    @Resource(name="tianBannerService")
    private ZhumpBannerService tianBannerService;

    /**
     * 轮播图列表
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(){
        try {
            List<ZhumpBannerVO> tianBannerVOS = tianBannerService.selectAll();
            return new Result(ResultStatus.SUCCESS,tianBannerVOS);
        } catch (Exception e) {
            log.error("系统异常",e);
            return new Result(ResultStatus.ERROR,null);
        }
    }
}