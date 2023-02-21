package com.yinlie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinlie.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-02-10 23:03:55
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}

