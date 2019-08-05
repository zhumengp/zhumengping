package org.com.tianzmp.controller;

import org.com.tianzmp.common.result.Result;
import org.com.tianzmp.common.result.ResultStatus;
import org.com.tianzmp.service.impl.ZhumpChannelServiceImpl;
import org.com.tianzmp.vo.ZhumpChannelVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="channel")
public class ZhumpChannelController {

    private final Logger log = LoggerFactory.getLogger(ZhumpChannelController.class);

    @Autowired
    private ZhumpChannelServiceImpl tianChannelService;

    /**
     * 频道列表
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(){
        try {
            List<ZhumpChannelVO> zhumpChannelVOS = tianChannelService.selectAll();
            return new Result(ResultStatus.SUCCESS,zhumpChannelVOS);
        } catch (Exception e) {
            log.error("系统异常",e);
            return new Result(ResultStatus.ERROR,null);
        }
    }
}
