package com.yinlie.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.yinlie.constants.SystemConstants;
import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.entity.Article;
import com.yinlie.domain.entity.Category;
import com.yinlie.domain.vo.ArticleDetailVo;
import com.yinlie.domain.vo.ArticleListVo;
import com.yinlie.domain.vo.HotArticleVo;
import com.yinlie.domain.vo.PageVo;
import com.yinlie.mapper.ArticleMapper;
import com.yinlie.service.ArticleService;
import com.yinlie.service.CategoryService;
import com.yinlie.utils.BeanCopyUtils;
import com.yinlie.utils.RedisCache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Oyerlicent
 * @create 2022-12-05 19:49
 **/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章，封装成ResponseResult返回
        //必须是正式文章，按照浏览量排序，最多显示10条
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(SystemConstants.PAGE_DISPLAY_NUMBER,SystemConstants.DATA_DISPLAY_NUMBER);
        page(page,queryWrapper);

        for (Article article : page.getRecords()) {
            Integer cacheMapValue = redisCache.getCacheMapValue("article:viewCout", article.getId().toString());
            article.setViewCount(cacheMapValue.longValue());
        }


        List<Article> articles = page.getRecords();
        //bean拷贝
//        List<HotArticleVo> articleVos = new ArrayList<>();
//        for (Article article : articles) {
//            HotArticleVo vo =new HotArticleVo();
//            BeanUtils.copyProperties(article,vo);
//            articleVos.add(vo);
//        }
        List<HotArticleVo> articleVos = BeanCopyUtils.CopyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(articleVos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //如果查询参数要求了categoryId，要求查询时传入的要和要求参数相同
        queryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        //查询到的文章要是正式发布的
        queryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);
        //查询categoryName
        List<Article> records = page.getRecords();
        //articleId去查询articleName进行设置
        records.stream()
                .map(article ->
                        article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());

//        for (Article article:records) {
//            Category categoryServiceById = categoryService.getById(article.getCategoryId());
//            article.setCategoryName(categoryServiceById.getName());
//        }
        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.CopyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos,page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);

        //从redis中获取viewCount
        Integer cacheMapValue = redisCache.getCacheMapValue("article:viewCout", id.toString());
        article.setViewCount(cacheMapValue.longValue());
        //转换成vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category categoryServiceById = categoryService.getById(categoryId);
        if (categoryServiceById == null) {
            articleDetailVo.setCategoryName(categoryServiceById.getName());
        }
        //封装成响应返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应id的浏览量
        redisCache.incrementCacheMapValue("article:viewCout",id.toString(),1);
        return ResponseResult.okResult();
    }
}
