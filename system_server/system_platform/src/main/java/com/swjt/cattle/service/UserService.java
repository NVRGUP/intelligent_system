package com.swjt.cattle.service;

import com.swjt.cattle.pojo.User;
import com.swjt.cattle.vo.UserVo;

import java.util.List;

public interface UserService {
    boolean add(User u);
    List<User> queryAll();
    boolean login(UserVo userVo);
}
