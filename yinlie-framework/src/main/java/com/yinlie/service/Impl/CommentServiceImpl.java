package com.yinlie.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinlie.constants.SystemConstants;
import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.entity.Comment;
import com.yinlie.domain.vo.CommentVo;
import com.yinlie.domain.vo.PageVo;
import com.yinlie.enums.AppHttpCodeEnum;
import com.yinlie.exception.SystemException;
import com.yinlie.mapper.CommentMapper;
import com.yinlie.service.CommentService;
import com.yinlie.service.UserService;
import com.yinlie.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-02-01 23:23:45
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //对articleId进行判断
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId);
        //根评论rootId为-1
        queryWrapper.eq(Comment::getRootId,-1);

        //评论类型
        queryWrapper.eq(Comment::getType,commentType);

        //分页查询
        Page<Comment> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);

        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());

        //查询所有根评论对应的子评论集合，并赋值给对应的属性
        for (CommentVo vo:
             commentVoList) {
            //查询对应的子评论
            List<CommentVo> children = getChildren(vo.getId());
            //赋值
            vo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }

        save(comment);

        return ResponseResult.okResult();
    }

    /**
     * 根据根评论的id查询对应的子评论集合
     * @param id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);
        List<CommentVo> commentVos = toCommentVoList(comments);
        return commentVos;
    }

    private List<CommentVo> toCommentVoList(List<Comment> list){
        List<CommentVo> commentVos = BeanCopyUtils.CopyBeanList(list, CommentVo.class);
        //遍历vo集合
        for (CommentVo vo:
             commentVos) {
            //通过creatBy查询用户昵称并赋值
            String nickName = userService.getById(vo.getCreateBy()).getNickName();
            vo.setUsername(nickName);
            //通过toCommentUserId查询用户昵称并赋值
            //如果toCommentUserId不为-1才进行查询
            if (vo.getToCommentUserId()!=-1){
                String name = userService.getById(vo.getToCommentUserId()).getNickName();
                vo.setToCommentUserName(name);
            }
        }
        return commentVos;

    }
}

