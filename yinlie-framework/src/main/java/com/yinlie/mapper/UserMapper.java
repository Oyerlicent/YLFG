package com.yinlie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yinlie.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-10 22:33:54
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

