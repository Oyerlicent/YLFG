package com.yinlie.utils;

import com.yinlie.domain.entity.Article;
import com.yinlie.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oyerlicent
 * @create 2022-12-06 20:51
 **/
public class BeanCopyUtils {
    private BeanCopyUtils() {
    }

    public static <V> V copyBean(Object source,Class <V> clazz) {
        //创建目标对象
        V result;
        try {
            result = clazz.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //返回结果
        return result;
    }

    public static <O,V> List<V> CopyBeanList(List<O> list,Class <V> clazz){
        return list.stream().map(o -> copyBean(o, clazz)).collect(Collectors.toList());
    }
//    测试
//    public static void main(String[] args){
//        Article article = new Article();
//        article.setId(1L);
//        article.setTitle("为什么人要吃饭");
//
//        HotArticleVo vo = copyBean(article, HotArticleVo.class);
//        System.out.println(vo);
//    }
}
