package com.yinlie.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Oyerlicent
 * @create 2022-12-05 23:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {
    private Long id;

    private String title;
    private Long viewCount;

}
