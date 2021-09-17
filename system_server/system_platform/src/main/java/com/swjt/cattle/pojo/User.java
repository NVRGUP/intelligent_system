package com.swjt.cattle.pojo;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户表
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {


    /**
     * 主键，用uuid实现唯一性
     */
    @Id@Column(name = "id")
    private String id;

    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @Column(name = "user_pwd")
    private String userPwd;

    /**
     * 用户权限：{0：管理员，1：普通用户，2：...}
     */
    @Column(name = "user_permission")
    private Integer userPermission;
}
