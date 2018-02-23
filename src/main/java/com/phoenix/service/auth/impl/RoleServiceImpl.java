package com.phoenix.service.auth.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phoenix.dataobject.auth.Role;
import com.phoenix.dataobject.auth.RolePermission;
import com.phoenix.dataobject.auth.mapper.RoleMapper;
import com.phoenix.dataobject.auth.mapper.RolePermissionMapper;
import com.phoenix.service.auth.RoleService;
import com.phoenix.util.serializer.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by hjx
 * 2018/1/24 0024.
 */
@Service
public class RoleServiceImpl implements RoleService{

    private RoleMapper roleMapper;
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper,
                           RolePermissionMapper rolePermissionMapper) {
        this.roleMapper = roleMapper;
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public List<Role> findByUserId(String userId) {
        return  roleMapper.selectByUserId(userId);
    }

    @Override
    public Page<Role> findRolePage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return roleMapper.selectPage();
    }

    @Override
    public void modifyRoleMenu(String roleId,
                               String roleMenuStr) {
        List<Map<String,Object>> listMap = new Gson().fromJson(roleMenuStr,
                new TypeToken<List<Map<String,Object>>>(){}.getType());
        for (Map<String,Object> map : listMap) {
            RolePermission permissionTemp = rolePermissionMapper.selectByParams(roleId,
                                                map.get("permissionId").toString());
            RolePermission rolePermissionParentTemp = rolePermissionMapper.selectByParams(
                    roleId,map.get("pid").toString());
            if (map.get("0").equals(true) && permissionTemp == null) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(roleId);
                rolePermission.setPermissionId(map.get("permissionId").toString());
                rolePermission.setRolePermissionId(KeyUtil.genUniqueKey());
                rolePermissionMapper.insert(rolePermission);

                if (rolePermissionParentTemp == null) {
                    RolePermission rolePermissionParent = new RolePermission();
                    rolePermissionParent.setRoleId(roleId);
                    rolePermissionParent.setPermissionId(map.get("pid").toString());
                    rolePermissionParent.setRolePermissionId(KeyUtil.genUniqueKey());
                    rolePermissionMapper.insert(rolePermissionParent);
                }
            }

            if (map.get("0").equals(false) && permissionTemp != null) {
                rolePermissionMapper.deleteByPrimaryKey(permissionTemp.getRolePermissionId());

                List<RolePermission> rolePermissionList = rolePermissionMapper.selectByPermissionId(
                        map.get("pid").toString());
                if (CollectionUtils.isEmpty(rolePermissionList)) {
                    rolePermissionMapper.deleteByPrimaryKey(rolePermissionParentTemp.getRolePermissionId());
                }
            }
        }
    }


}
