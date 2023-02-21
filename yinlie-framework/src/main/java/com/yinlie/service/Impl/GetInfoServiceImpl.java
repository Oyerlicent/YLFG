package com.yinlie.service.Impl;

import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.entity.LoginUser;
import com.yinlie.domain.vo.AdminUserInfoVo;
import com.yinlie.domain.vo.UserInfoVo;
import com.yinlie.service.GetInfoService;
import com.yinlie.service.MenuService;
import com.yinlie.service.RoleService;
import com.yinlie.utils.BeanCopyUtils;
import com.yinlie.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oyerlicent
 * @create 2023-02-10 23:32
 **/
@Service("GetInfoService")
public class GetInfoServiceImpl implements GetInfoService {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Override
    public ResponseResult getInfo() {
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        //封装数据返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList,userInfoVo);

        return ResponseResult.okResult(adminUserInfoVo);
    }
}
