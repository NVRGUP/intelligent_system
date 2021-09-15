package com.swjt.cattle.repository;

import com.swjt.cattle.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//userJPA
public interface UserRepository extends JpaRepository<User,Integer> {
    public List<User> findByUserName(String userName);
}
