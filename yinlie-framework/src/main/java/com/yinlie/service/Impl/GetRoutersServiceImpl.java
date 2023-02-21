package com.yinlie.service.Impl;

import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.entity.Menu;
import com.yinlie.domain.vo.RoutersVo;
import com.yinlie.service.GetRoutersService;
import com.yinlie.service.MenuService;
import com.yinlie.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oyerlicent
 * @create 2023-02-11 22:22
 **/
@Service("GetRoutersService")
public class GetRoutersServiceImpl implements GetRoutersService {

    @Autowired
    private MenuService menuService;

    @Override
    public ResponseResult<RoutersVo> getRouters() {
        //查询用户id
        Long userId = SecurityUtils.getUserId();
        //查询menu表结果为tree形式
        List<Menu> menus = menuService.selectRouterMenuByUserId(userId);
        //封装数据返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }
}
