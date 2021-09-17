package com.swjt.cattle.service;

import com.swjt.cattle.common.SimplePage;
import com.swjt.cattle.pojo.User;
import com.swjt.cattle.vo.ID;
import com.swjt.cattle.vo.UserCo;
import com.swjt.cattle.vo.UserVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    /**
     *添加用户
     * @param userVo
     * @return 返回主键id
     */
    ID<?> addUser(UserVo userVo);

    /**
     * 更新用户信息
     * @param id
     * @param userVo
     * @return
     */
    ID<?> updateUser(String id,UserVo userVo);

    /**
     * 查询所有用户
     * @return
     */
    List<UserVo> queryAllUser();

    /**
     *
     * @param pageable
     * @param userCo
     * @return 分页查询
     */
    SimplePage<UserVo> findUserByPage(Pageable pageable, UserCo userCo);
    /**
     *
     * @param userName
     * @param userPwd
     * @return
     */
    User login(String userName , String userPwd);
}
