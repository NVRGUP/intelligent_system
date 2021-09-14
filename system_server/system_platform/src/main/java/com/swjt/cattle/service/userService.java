package com.swjt.cattle.service;

import com.swjt.cattle.pojo.user;

import java.util.List;

public interface userService {
    boolean add(user u);
    List<user> queryAll();
}
