package com.phoenix.dataobject.auth.mapper;

import com.phoenix.dataobject.auth.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by hjx
 * 2018/1/22 0022.
 */

public interface UserMapper {

    /**
     * 主键查询用户
     */
    User selectByUserId(String userId);

    /**
     * 用户名密码(密码可为空)查询用户
     */
    User selectByUserName(@Param("userName")String userName,
                          @Param("password") String password);


}
