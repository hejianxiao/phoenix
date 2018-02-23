package com.phoenix.service.auth;

import com.phoenix.dataobject.auth.User;
import com.phoenix.dto.auth.PermissionDTO;

import java.util.List;

/**
 * Created by hjx
 * 2018/1/23 0023.
 */
public interface UserService {

    User findByUserName(String userName, String password);

}
