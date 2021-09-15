package com.swjt.cattle.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "user")
@Data
public class UserVo implements Serializable {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "userName")
    private String userName;

    @ApiModelProperty(value = "userPwd")
    private String userPwd;
}
