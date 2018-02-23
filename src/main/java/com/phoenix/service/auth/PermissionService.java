package com.phoenix.service.auth;

import com.github.pagehelper.Page;
import com.phoenix.dataobject.auth.Permission;
import com.phoenix.dto.auth.PermissionDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by hjx
 * 2018/1/24 0024.
 */
public interface PermissionService {
    List<PermissionDTO> findPermission(String userId, String parentId);

    List<Permission> findPermissionAll(Integer resourceType, String parentId);

    Page<Permission> findPermissionPage(Integer resourceType,
                                        String parentId,
                                        Integer page,
                                        Integer size);


    Permission savePermission(Permission permission);

    Permission findOne(String permissionId);

    void del(String permissionId);

    List<Map<String,Object>> findPermissionByRoleId(String roleId,
                                                    String parentId);
}
