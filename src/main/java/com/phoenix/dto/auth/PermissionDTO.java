package com.phoenix.dto.auth;

import lombok.Data;

import java.util.List;

/**
 * Created by hjx
 * 2018/1/23 0023.
 */
@Data
public class PermissionDTO {

    /** 权限id. */
    private String permissionId;

    /** 权限名称. */
    private String permissionName;

    /** 资源类型. */
    private Integer resourceType;

    /** 资源路径. */
    private String url;

    /** 权限字符串. */
    private String permiss;

    /** 父权限id. */
    private String parentId;

    /** 排序策略. */
    private Integer sortRank;

    private List<PermissionDTO> permissionList;
}
