package com.swjt.cattle.controller;

import com.swjt.cattle.pojo.User;
import com.swjt.cattle.service.UserService;
import com.swjt.cattle.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(value = "智能系统",tags = "用户接口")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "",notes="测试")
    @RequestMapping(value = "/addUser",method = RequestMethod.GET)
    public String addUser(User u){
        userService.add(u);
        return "true";
    }

    @ApiOperation(value = "",notes="")
    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    public List<User> findUser() {
        return userService.queryAll();
    }

    @ApiOperation(value = "登录",notes="登录验证")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public Boolean login(String userName,String userPwd) {
        UserVo userVo = new UserVo();
        userVo.setUserName(userName);
        userVo.setUserPwd(userPwd);
        return userService.login(userVo);
    }



//    @ApiOperation("获取验证码")
//    @RequestMapping(value = "/code")
//    public ResponseEntity<Object> getCode(HttpServletResponse response) throws IOException {
//        VerifyCode vc = new VerifyCode();
//        BufferedImage bi = vc.getImage(); // 生成图片
//        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
//            put("img", VerifyCode.output(bi, response.getOutputStream()));
//        }};
//        return ResponseEntity.ok(imgResult);
//    }

    @ApiOperation("退出登录")
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        return null;
    }



}
