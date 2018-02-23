package com.phoenix.controller.auth;

import com.google.gson.Gson;
import com.phoenix.config.SystemConfig;
import com.phoenix.dataobject.auth.User;
import com.phoenix.dto.auth.PermissionDTO;
import com.phoenix.enums.ResultEnum;
import com.phoenix.service.auth.PermissionService;
import com.phoenix.util.ResultVOUtil;
import com.phoenix.vo.ResultVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限控制
 * Created by hjx
 * 2018/1/23 0023.
 */
@Controller
public class AuthController {

    private static final String _PID = "0";

    private SystemConfig systemConfig;
    private PermissionService permissionService;

    @Autowired
    public AuthController(PermissionService permissionService,
                          SystemConfig systemConfig) {
        this.permissionService = permissionService;
        this.systemConfig = systemConfig;
    }

    /**
     * 登录页
     */
    @RequestMapping(value = "/index")
    public String login() {
        return "login";
    }
    /**
     * 首页
     */
    @RequestMapping(value = "/home")
    public String home(HttpServletRequest request) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user != null) {
            List<PermissionDTO> permissionDTOList =
                    permissionService.findPermission(user.getUserId(), _PID);
            if (!CollectionUtils.isEmpty(permissionDTOList)) {
                HttpSession session =request.getSession();
                Gson gs = new Gson();
                String trees = gs.toJson(this.getTree(systemConfig.getBasePath(), permissionDTOList));
                session.setAttribute("permissionList",permissionDTOList);
                session.setAttribute("tree",trees);
            }
            return "home";
        } else {
            return "login";
        }
    }

    /**
     * 登录方法
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultVO login(HttpServletRequest request,
                          User user) {
        if (StringUtils.isEmpty(user.getUserName()) ||
            StringUtils.isEmpty(user.getPassword())) {
            return ResultVOUtil.error(ResultEnum.USER_EMPTY.getCode(),
                    ResultEnum.USER_ERROR.getMsg());
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                user.getUserName(), user.getPassword()
        );
        try {
            subject.login(token);
            request.getSession().setAttribute("user", user);
            return ResultVOUtil.success("home","登录成功");
        } catch (Exception e) {
            return ResultVOUtil.error(ResultEnum.USER_ERROR.getCode(),
                                      ResultEnum.USER_ERROR.getMsg());
        }
    }


    /**
     * 安全退出
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "login";
    }

    /**
     * 获取权限树结构
     * @param permissionDTOList 登录用户权限
     * @return 权限树
     */
    private List<Map<String,Object>> getTree(String path, List<PermissionDTO> permissionDTOList) {
        List<Map<String,Object>> trees = new ArrayList<>();
        for (int i = 0;i < permissionDTOList.size(); i++) {
            PermissionDTO tempPermissionDTO = permissionDTOList.get(i);
            Map<String,Object> tree = new HashMap<>();
            //节点id
            tree.put("nodeId",i+1);
            //节点名称
            tree.put("text",tempPermissionDTO.getPermissionName());
            if (!StringUtils.isEmpty(tempPermissionDTO.getUrl())) {
                //节点链接
                tree.put("href",path + tempPermissionDTO.getUrl());
            }

            List<PermissionDTO> childPermissionList = tempPermissionDTO.getPermissionList();
            if (!CollectionUtils.isEmpty(childPermissionList)) {
                //子节点
                tree.put("nodes",getTree(path, childPermissionList));
            }
            trees.add(tree);
        }
        return trees;
    }


}
