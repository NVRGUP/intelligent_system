package com.swjt.cattle.repository;

import com.swjt.cattle.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {
    /**
     * 根据id查询某个用户
     * @param id
     * @return
     */
    User findById(String id);
    /**
     * login登录
     * @param userName
     * @param userPwd
     * @return User
     */
    public User findByUserNameAndUserPwd(String userName,String userPwd);

}
