package com.phoenix.service.auth;

import com.github.pagehelper.Page;
import com.phoenix.dataobject.auth.Role;

import java.util.List;

/**
 * Created by hjx
 * 2018/1/24 0024.
 */
public interface RoleService {

    List<Role> findByUserId(String userId);

    Page<Role> findRolePage(Integer page,
                            Integer size);

    void modifyRoleMenu(String roleId,
                        String roleMenuStr);

}
