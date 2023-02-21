package com.yinlie.service.Impl;

import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.entity.LoginUser;
import com.yinlie.domain.entity.User;
import com.yinlie.domain.vo.BlogUserLoginVo;
import com.yinlie.domain.vo.UserInfoVo;
import com.yinlie.service.BlogLoginService;
import com.yinlie.service.LoginService;
import com.yinlie.utils.BeanCopyUtils;
import com.yinlie.utils.JwtUtil;
import com.yinlie.utils.RedisCache;
import com.yinlie.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;


/**
 * @author Oyerlicent
 * @create 2023-01-29 22:30
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名和密码错误");
        }
        //获取userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息先存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //再把token和userid封装返回
        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);

        return ResponseResult.okResult(map);

    }

    @Override
    public ResponseResult logout() {
        Long userId = SecurityUtils.getUserId();
        redisCache.deleteObject("login:"+userId);
        return ResponseResult.okResult();
    }
}
