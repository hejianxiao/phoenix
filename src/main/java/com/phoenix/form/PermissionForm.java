package com.phoenix.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by hjx
 * 2018/2/1 0001.
 */
@Data
public class PermissionForm {

    private String permissionId;

    @NotEmpty(message = "权限名称必填")
    private String permissionName;

    private Integer resourceType;

    private String url;

    private String permiss;

    private String parentId;

    @NotNull(message = "排序必填")
    private Integer sortRank;

    private Date createTime;

    private Date updateTime;
    
}
