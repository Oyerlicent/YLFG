package com.yinlie.controller;

import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.entity.LoginUser;
import com.yinlie.domain.entity.User;
import com.yinlie.domain.vo.AdminUserInfoVo;
import com.yinlie.domain.vo.RoutersVo;
import com.yinlie.domain.vo.UserInfoVo;
import com.yinlie.enums.AppHttpCodeEnum;
import com.yinlie.exception.SystemException;
import com.yinlie.service.*;
import com.yinlie.utils.BeanCopyUtils;
import com.yinlie.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Oyerlicent
 * @create 2023-02-10 20:58
 **/
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private GetInfoService getInfoService;
    @Autowired
    private GetRoutersService getRoutersService;


    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
       return getInfoService.getInfo();
    }

    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        return getRoutersService.getRouters();
    }

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
