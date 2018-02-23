package com.phoenix.shiro;

import com.phoenix.dataobject.auth.Role;
import com.phoenix.dataobject.auth.User;
import com.phoenix.dto.auth.PermissionDTO;
import com.phoenix.service.auth.PermissionService;
import com.phoenix.service.auth.RoleService;
import com.phoenix.service.auth.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hjx
 * 2018/1/23 0023.
 */
public class CustomRealm extends AuthorizingRealm{

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user  = (User)principals.getPrimaryPrincipal();

        List<Role> roleList = roleService.findByUserId(user.getUserId());
        if (!CollectionUtils.isEmpty(roleList)) {
            List<String> roles = roleList.stream().map(
                    Role::getRoleName
            ).collect(Collectors.toList());
            authorizationInfo.addRoles(roles);
        }
        List<PermissionDTO> permissionDTOList = permissionService.findPermission(user.getUserId(), null);
        if (!CollectionUtils.isEmpty(permissionDTOList)) {
            List<String> permissions = permissionDTOList.stream().map(
                    PermissionDTO::getPermissionName
            ).collect(Collectors.toList());
            authorizationInfo.addStringPermissions(permissions);
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.findByUserName(username,null);
        if(user != null){
            return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        }
        return null;
    }
}
