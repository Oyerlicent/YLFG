package com.yinlie.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinlie.domain.entity.Tag;
import com.yinlie.mapper.TagMapper;
import com.yinlie.service.TagService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-02-09 22:10:38
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}

