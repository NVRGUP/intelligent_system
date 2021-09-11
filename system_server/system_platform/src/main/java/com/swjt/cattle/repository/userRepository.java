package com.swjt.cattle.repository;

import com.swjt.cattle.pojo.user;
import org.springframework.data.jpa.repository.JpaRepository;

//userJPA
public interface userRepository extends JpaRepository<user,Long> {
}
