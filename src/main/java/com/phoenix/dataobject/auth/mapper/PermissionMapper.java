package com.phoenix.dataobject.auth.mapper;

import com.github.pagehelper.Page;
import com.phoenix.dataobject.auth.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PermissionMapper {
    int deleteByPrimaryKey(String permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String permissionId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectByUserId(@Param("userId") String userId,
                                    @Param("parentId") String parentId);


    List<Permission> selectAll(@Param("resourceType") Integer resourceType,
                               @Param("parentId") String parentId);

    Page<Permission> selectPage(@Param("resourceType") Integer resourceType,
                                @Param("parentId") String parentId);

    List<Map<String,Object>> selectByRoleId(@Param("roleId") String roleId,
                                            @Param("parentId") String parentId);

}