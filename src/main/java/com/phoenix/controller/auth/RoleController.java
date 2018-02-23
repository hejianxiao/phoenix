package com.phoenix.controller.auth;

import com.phoenix.service.auth.PermissionService;
import com.phoenix.service.auth.RoleService;
import com.phoenix.util.ResultVOUtil;
import com.phoenix.util.TableVOUtil;
import com.phoenix.vo.ResultVO;
import com.phoenix.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 角色管理
 * Created by hjx
 * 2018/2/8 0008.
 */
@Controller
@RequestMapping("/role")
public class RoleController{

    private RoleService roleService;
    private PermissionService permissionService;

    @Autowired
    public RoleController(RoleService roleService,
                          PermissionService permissionService) {
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    /**
     * 列表页
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "auth/role/list";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    @ResponseBody
    public TableVO roleData(@RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "5") Integer size) {
        return TableVOUtil.pageConvertToTable(roleService.findRolePage(page,size));
    }

    @RequestMapping(value = "/roleMenuList", method = RequestMethod.GET)
    @ResponseBody
    public TableVO menuData(@RequestParam("pid") String parentId,
                            @RequestParam("roleId") String roleId) {
        return TableVOUtil.listConvertToTable(permissionService.findPermissionByRoleId(roleId, parentId));
    }

    @RequestMapping( value = "/modifyRoleMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO modifyRoleMenu(@RequestParam("roleId") String roleId,
                                   @RequestParam("jsonData")String roleMenuStr){
        roleService.modifyRoleMenu(roleId,roleMenuStr);
        return ResultVOUtil.success();
    }

}
