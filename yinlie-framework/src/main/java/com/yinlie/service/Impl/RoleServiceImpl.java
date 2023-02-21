package com.yinlie.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinlie.domain.entity.Role;
import com.yinlie.mapper.RoleMapper;
import com.yinlie.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-02-10 23:03:56
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否为管理员，如果是返回集合中只需返回admin这一角色
        if (id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则将对应角色全部返回
        return getBaseMapper().selectRoleKeyByUserId(id);
    }
}

