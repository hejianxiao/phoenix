package com.phoenix.dataobject.auth;

import lombok.Data;

/**
 * Created by hjx
 * 2018/1/22 0022.
 */
@Data
public class UserRole {

    /** 用户角色id. */
    private String userRoleId;

    /** 用户id. */
    private String userId;

    /** 角色id. */
    private String roleId;

}
