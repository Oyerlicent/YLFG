package com.yinlie.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinlie.constants.SystemConstants;
import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.entity.Article;
import com.yinlie.domain.entity.Category;
import com.yinlie.domain.vo.CategoryVo;
import com.yinlie.mapper.CategoryMapper;
import com.yinlie.service.ArticleService;
import com.yinlie.service.CategoryService;
import com.yinlie.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(SgCategory)表服务实现类
 *
 * @author makejava
 * @since 2022-12-06 22:28:55
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService{

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        //查询文章表，状态为已发
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(queryWrapper);
        //获取文章的分类id，并且去重
        Set<Long> categoryIds = articleList.stream().map(o -> o.getCategoryId()).collect(Collectors.toSet());
        //查询分类表,并且分类表正常
        List<Category> categoryList = listByIds(categoryIds);
        categoryList.stream().filter(category-> SystemConstants.STATUS_NORMAL.equals(category.getStatus())).collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.CopyBeanList(categoryList, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}

