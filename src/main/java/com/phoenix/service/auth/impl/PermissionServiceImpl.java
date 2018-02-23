package com.phoenix.service.auth.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.phoenix.dataobject.auth.Permission;
import com.phoenix.dataobject.auth.enums.ResourceTypeEnum;
import com.phoenix.dataobject.auth.mapper.PermissionMapper;
import com.phoenix.dto.auth.PermissionDTO;
import com.phoenix.service.auth.PermissionService;
import com.phoenix.util.serializer.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by hjx
 * 2018/1/24 0024.
 */
@Service
public class PermissionServiceImpl implements PermissionService{

    private static final String _AUTHC = "authc";
    private static final String _PID = "0";

    private PermissionMapper permissionMapper;

    @Autowired
    public PermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<PermissionDTO> findPermission(String userId, String parentId) {
        List<Permission> permissionList = permissionMapper.selectByUserId(userId, parentId);
        if (!CollectionUtils.isEmpty(permissionList)) {
            List<PermissionDTO> permissionDTOList = new ArrayList<>();
            for (Permission permission : permissionList) {
                PermissionDTO permissionDTO = convert(permission);
                permissionDTO.setPermissionList( permissionMapper.selectByUserId(userId,permissionDTO.getPermissionId()).stream().map(
                        this::convert
                ).collect(Collectors.toList()));
                permissionDTOList.add(permissionDTO);

            }
            return permissionDTOList;
        }
        return null;
    }

    @Override
    public List<Permission> findPermissionAll(Integer resourceType, String parentId) {
        return permissionMapper.selectAll(resourceType, parentId);
    }

    @Override
    public Page<Permission> findPermissionPage(Integer resourceType, String parentId, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return permissionMapper.selectPage(resourceType, parentId);
    }

    @Override
    public Permission savePermission(Permission permission) {
        if (StringUtils.isEmpty(permission.getPermissionId())) {
            permission.setPermissionId(KeyUtil.genUniqueKey());
            permission.setResourceType(ResourceTypeEnum.URL.getCode());
            permission.setPermiss(_AUTHC);
            if (StringUtils.isEmpty(permission.getParentId())) {
                permission.setParentId(_PID);
            }
            permissionMapper.insert(permission);
            return permission;
        } else {
            Permission tempPermission = permissionMapper.selectByPrimaryKey(permission.getPermissionId());
            tempPermission.setPermissionName(permission.getPermissionName());
            tempPermission.setUrl(permission.getUrl());
            tempPermission.setSortRank(permission.getSortRank());
            permissionMapper.updateByPrimaryKey(tempPermission);
            return tempPermission;
        }
    }

    @Override
    public Permission findOne(String permissionId) {
        return permissionMapper.selectByPrimaryKey(permissionId);
    }

    @Override
    public void del(String permissionId) {
        permissionMapper.deleteByPrimaryKey(permissionId);
    }

    @Override
    public List<Map<String,Object>> findPermissionByRoleId(String roleId, String parentId) {
        return permissionMapper.selectByRoleId(roleId, parentId);
    }


    private PermissionDTO convert(Permission permission) {
        PermissionDTO permissionDTO = new PermissionDTO();
        BeanUtils.copyProperties(permission, permissionDTO);
        return permissionDTO;
    }
}
