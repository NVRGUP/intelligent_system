/**
 * @Type com.swjt.cattle.utils
 * @DESC
 * @Author Create By DaiRui
 * @TIME 2021/9/17  22:59
 * @VERSION Version 1.0
 **/

package com.swjt.cattle.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    /**
     * 构建下载类
     *
     * @param
     * @return
     * @throws
     */
    public static ResponseEntity<byte[]> buildResponseEntity(byte[] bytes, MediaType mediaType) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        // 设置文件类型
        headers.setContentType(mediaType);
        // 设置Http状态码
        HttpStatus statusCode = HttpStatus.OK;
        // 返回数据
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(bytes, headers, statusCode);

        return entity;
    }
}

