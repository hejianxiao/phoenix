package com.phoenix.dataobject.auth.mapper;

import com.phoenix.dataobject.auth.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(String rolePermissionId);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(String rolePermissionId);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    RolePermission selectByParams(@Param("roleId")String roleId,
                                  @Param("permissionId")String permissionId);

    List<RolePermission> selectByPermissionId(String permissionId);
}