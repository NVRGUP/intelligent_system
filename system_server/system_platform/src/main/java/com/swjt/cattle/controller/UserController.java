package com.swjt.cattle.controller;

import com.swjt.cattle.service.UserService;
import com.swjt.cattle.utils.VerifyCode;
import com.swjt.cattle.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@Api(value = "智能系统",tags = "用户接口")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "添加用户",notes="添加用户，名字，密码，权限选择")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> addUser(@RequestBody UserVo u){
        return new ResponseEntity<>(userService.addUser(u), HttpStatus.CREATED);
    }

    @ApiOperation(value = "查询所有用户",notes="看所有用户")
    @RequestMapping(value = "/findAllUser",method = RequestMethod.GET)
    public HttpEntity<?> findAllUser() {
        return new ResponseEntity<>(userService.queryAllUser(),HttpStatus.OK);
    }

    @ApiOperation(value = "用户名密码检测登录",notes="登录验证")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public HttpEntity<?> login(@RequestBody Map map, HttpServletRequest request) {
        String userName = (String) map.get("userName");
        String userPwd = (String) map.get("userPwd");
        String code = (String) map.get("code");
        Cookie[] cookies = request.getCookies();
        String code1="";
        for(Cookie cookie:cookies){
            if("code".equals(cookie.getName())){
                code1=cookie.getValue();
            }
        }
        if(code1.equalsIgnoreCase(code)){
            if(userService.login(userName,userPwd)!=null){
                return new ResponseEntity<>(userService.login(userName,userPwd),HttpStatus.OK);
            }
            else return new ResponseEntity<>(false,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(false,HttpStatus.OK);
        }
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/code",method = RequestMethod.GET)
    public void getCode(HttpServletResponse response,HttpServletRequest request) throws IOException {
        VerifyCode vc = new VerifyCode();
        BufferedImage bi = vc.getImage(); // 生成图片
        //把text存在cookie里面
        Cookie cookie = new Cookie("code",vc.getText());
        response.addCookie(cookie);
        ServletOutputStream outputStream = response.getOutputStream();
        VerifyCode.output(bi, outputStream);
    }



    @ApiOperation("退出登录")
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        return null;
    }



}
