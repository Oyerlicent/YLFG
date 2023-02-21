package com.yinlie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yinlie.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;


/**
 * 友链(Link)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-09 23:35:34
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}

