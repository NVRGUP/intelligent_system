package com.swjt.cattle.service.impl;

import com.swjt.cattle.pojo.User;
import com.swjt.cattle.repository.UserRepository;
import com.swjt.cattle.service.UserService;
import com.swjt.cattle.utils.BeanUtils;
import com.swjt.cattle.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userJ;

    @Override
    public boolean add(User u) {
//      增添用户
        userJ.save(u);
        return false;
    }

    @Override
    public boolean login(UserVo userVo) {
        User user = new User();
        boolean flag=false;
        BeanUtils.copyPropertiesIgnoreNullValue(userVo,user);
        List<User> users = userJ.findByUserName(userVo.getUserName());
        for(User u:users)
        {
            if((userVo.getUserPwd().equals(u.getUserPwd()))){
                flag=true;
                break;
            }
        }
        return flag;
    }

    @Override
    public List<User> queryAll() {
        List<User> all = userJ.findAll();
        return all;
    }
}
