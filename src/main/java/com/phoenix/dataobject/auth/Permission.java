package com.phoenix.dataobject.auth;

import lombok.Data;

import java.util.Date;


@Data
public class Permission {
    private String permissionId;

    private String permissionName;

    private Integer resourceType;

    private String url;

    private String permiss;

    private String parentId;

    private Integer sortRank;

    private Date createTime;

    private Date updateTime;

}