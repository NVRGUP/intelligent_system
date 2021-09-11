package com.swjt.cattle.service.impl;

import com.swjt.cattle.pojo.user;
import com.swjt.cattle.repository.userRepository;
import com.swjt.cattle.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {


    @Autowired
    private  userRepository userJ;

    @Override
    public boolean add(user u) {
//      增添用户
        userJ.save(u);
        return false;
    }
}
