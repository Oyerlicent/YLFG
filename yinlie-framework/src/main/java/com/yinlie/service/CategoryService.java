package com.yinlie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.entity.Category;


/**
 * 分类表(SgCategory)表服务接口
 *
 * @author makejava
 * @since 2022-12-06 22:28:54
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

