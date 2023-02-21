package com.yinlie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yinlie.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Oyerlicent
 * @create 2022-12-05 19:46
 **/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
