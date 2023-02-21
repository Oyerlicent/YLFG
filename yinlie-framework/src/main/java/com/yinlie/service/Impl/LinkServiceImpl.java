package com.yinlie.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinlie.constants.SystemConstants;
import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.entity.Link;
import com.yinlie.domain.vo.LinkVo;
import com.yinlie.mapper.LinkMapper;
import com.yinlie.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.yinlie.service.LinkService;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2022-12-09 23:35:33
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> list = list(queryWrapper);

        List<LinkVo> linkVos = BeanCopyUtils.CopyBeanList(list, LinkVo.class);

        return ResponseResult.okResult(linkVos);
    }
}

