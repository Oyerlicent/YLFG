package com.yinlie.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinlie.constants.SystemConstants;
import com.yinlie.domain.entity.Menu;
import com.yinlie.mapper.MenuMapper;
import com.yinlie.service.MenuService;
import com.yinlie.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-02-10 22:39:44
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectPermByUserId(Long id) {
        //如果是管理员，返回所有权限
        if (SecurityUtils.isAdmin()){
            LambdaQueryWrapper<Menu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(Menu::getMenuType, SystemConstants.MENU,SystemConstants.BUTTON);
            lambdaQueryWrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
            List<Menu> menuList = list(lambdaQueryWrapper);
            List<String> perms = menuList.stream().map(Menu::getPerms).collect(Collectors.toList());
            return perms;
        }
        //否则返回其具有的权限
        return getBaseMapper().selectPermsByUserId(id);

    }

    @Override
    public List<Menu> selectRouterMenuByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        //判断是否是管理员
        if (SecurityUtils.isAdmin()){
            //如果是，返回所有符合要求的menu
            menus = menuMapper.selectAllRouterMenu();

        }else {
            //否则返回当前用户所具有的menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);

        }
        //构建tree
        //先找出第一层的菜单，再去找子菜单并设置到children属性中
        List<Menu> menuTree = builderMenuTree(menus,0L);

        return menuTree;
    }

    private List<Menu> builderMenuTree(List<Menu> menus, long parentId) {
        List<Menu> menuTree = menus.stream().filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    /**
     * 获取传入参数的子menu
     *
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList = menus.stream().filter(m -> m.getParentId().equals(menu.getId())).collect(Collectors.toList());
        return childrenList;
    }
}

