package com.swjt.cattle.service.impl;

import com.swjt.cattle.common.SimplePage;
import com.swjt.cattle.exceptions.SysRuntimeException;
import com.swjt.cattle.pojo.User;
import com.swjt.cattle.repository.UserRepository;
import com.swjt.cattle.service.UserService;
import com.swjt.cattle.utils.BeanUtils;
import com.swjt.cattle.vo.ID;
import com.swjt.cattle.vo.UserCo;
import com.swjt.cattle.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    //事务注解，一般只加在需要更改数据的方法上，出现问题会回滚，保证数据安全
    @Transactional
    public ID<?> addUser(UserVo userVo) {
        User user = new User();
        BeanUtils.copyPropertiesIgnoreNullValue(userVo,user);
        /**
         * 生成一个随机id
         */
        user.setId(UUID.randomUUID().toString());
        userRepository.save(user);
        return new ID<>(user.getId());
    }

    @Override
    @Transactional
    public ID<?> updateUser(String id, UserVo userVo) {
        User user = userRepository.findById(id);
        if(user==null)
        {
            throw new SysRuntimeException("Err_Normal_01", HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyPropertiesIgnoreNullValue(userVo,user,"id","userPermission");
        return new ID<>(id);
    }

    @Override
    public List<UserVo> queryAllUser() {
        List<User> allUser = userRepository.findAll();
        List<UserVo> userVos = new ArrayList<UserVo>();
        UserVo userVo = new UserVo();
        for(User user:allUser)
        {
            BeanUtils.copyPropertiesIgnoreNullValue(user,userVo);
            userVos.add(userVo);
        }
        return userVos;
    }

    @Override
    public SimplePage<UserVo> findUserByPage(Pageable pageable, UserCo userCo) {
        userRepository.findAll(pageable);
        return null;
    }

    @Override
    public User login(String userName,String userPwd) {
        return userRepository.findByUserNameAndUserPwd(userName,userPwd);
    }
}
