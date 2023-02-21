package com.yinlie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yinlie.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;


/**
 * 分类表(SgCategory)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-06 22:28:55
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}

