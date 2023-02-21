package com.yinlie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-12-09 23:35:33
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}

