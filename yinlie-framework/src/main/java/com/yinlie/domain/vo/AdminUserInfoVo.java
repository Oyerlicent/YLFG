package com.yinlie.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Oyerlicent
 * @create 2023-02-10 23:11
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserInfoVo {
//    所有菜单类型为C或者F的
    private List<String> permissions;

    private List<String> roles;

    private UserInfoVo user;
}
