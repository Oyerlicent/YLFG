package com.yinlie.job;

import com.yinlie.domain.entity.Article;
import com.yinlie.service.ArticleService;
import com.yinlie.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Oyerlicent
 * @create 2023-02-06 23:38
 **/
@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateViewCount(){
        //获取redis中的浏览量
        Map<String, Integer> cacheMap = redisCache.getCacheMap("article:viewCout");

        List<Article> articleList = cacheMap.entrySet().stream().map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        //更新到数据库中
        articleService.updateBatchById(articleList);

    }
}
