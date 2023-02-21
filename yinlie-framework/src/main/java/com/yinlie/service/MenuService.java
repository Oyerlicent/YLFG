package com.yinlie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinlie.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-02-10 22:39:44
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermByUserId(Long id);

    List<Menu> selectRouterMenuByUserId(Long userId);
}

