package com.yinlie.service;

import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.vo.RoutersVo;

/**
 * @author Oyerlicent
 * @create 2023-02-11 22:21
 **/
public interface GetRoutersService {
    ResponseResult<RoutersVo> getRouters();
}
