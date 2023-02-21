package com.yinlie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-02-01 23:23:44
 */
public interface CommentService extends IService<Comment> {
    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}

