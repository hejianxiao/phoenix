package com.phoenix.dataobject.auth.mapper;

import com.github.pagehelper.Page;
import com.phoenix.dataobject.auth.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByUserId(String userId);

    Page<Role> selectPage();
}