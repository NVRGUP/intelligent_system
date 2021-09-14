package com.swjt.cattle.controller;

import com.swjt.cattle.pojo.user;
import com.swjt.cattle.service.userService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "医院智能",tags = "用户接口")
@RequestMapping("/user")
public class userController {

    @Autowired
    private userService userService;


    @ApiOperation(value = "",notes="测试")
    @RequestMapping(value = "/addUser",method = RequestMethod.GET)
    public String addUser(user u){
        userService.add(u);
        return "true";
    }

    @ApiOperation(value = "",notes="")
    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    public List<user> findUser() {

        return userService.queryAll();

    }



}
