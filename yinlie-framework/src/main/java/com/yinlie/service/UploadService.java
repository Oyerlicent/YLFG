package com.yinlie.service;

import com.yinlie.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Oyerlicent
 * @create 2023-02-04 18:56
 **/
 public interface UploadService {
    public ResponseResult uploadImg(MultipartFile img);
}
