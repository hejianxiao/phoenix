package com.phoenix.dataobject.auth;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private String roleId;

    private String roleName;

    private Date createTime;

    private Date updateTime;

}