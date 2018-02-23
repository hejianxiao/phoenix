package com.phoenix.dataobject.auth;

import lombok.Data;

/**
 * Created by hjx
 * 2018/1/22 0022.
 */
@Data
public class User {

    /** 主键. */
    private String userId;

    /** 真实姓名. */
    private String name;

    /** 用户名. */
    private String userName;

    /** 密码. */
    private String password;

    /** 盐. */
    private String salt;

    /** 用户状态. */
    private Integer status;

}
