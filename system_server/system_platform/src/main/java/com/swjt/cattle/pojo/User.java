package com.swjt.cattle.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    //id
    @Id
    private Integer id;

    //用户
    private String userName;

    //密码
    private String userPwd;

}
