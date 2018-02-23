package com.phoenix.controller.auth;

import com.phoenix.dataobject.auth.Permission;
import com.phoenix.dataobject.auth.enums.ResourceTypeEnum;
import com.phoenix.enums.ResultEnum;
import com.phoenix.form.PermissionForm;
import com.phoenix.service.auth.PermissionService;
import com.phoenix.util.ResultVOUtil;
import com.phoenix.util.TableVOUtil;
import com.phoenix.vo.ResultVO;
import com.phoenix.vo.TableVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 菜单管理
 * Created by hjx
 * 2018/1/26 0026.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    private static final String _PID = "0";

    private PermissionService permissionService;

    @Autowired
    public MenuController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 列表页
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "8") Integer size,
                       @RequestParam(value = "parentId", defaultValue = "0") String parentId,
                       Model model) {
        model.addAttribute("list", permissionService.findPermissionPage(
                ResourceTypeEnum.URL.getCode(),parentId,page,size));
        if (!parentId.equals(_PID)) {
            model.addAttribute("parentId",parentId);
        }else {
            model.addAttribute("parentId",_PID);
        }
        return "auth/menu/list";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    @ResponseBody
    public TableVO data(@RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                        @RequestParam(value = "parentId", defaultValue = "0") String parentId,
                        Model model) {
        if (!parentId.equals(_PID)) {
            model.addAttribute("parentId",parentId);
        }else {
            model.addAttribute("parentId",_PID);
        }
        return TableVOUtil.pageConvertToTable(permissionService.findPermissionPage(ResourceTypeEnum.URL.getCode(),parentId,page,size));
    }


    /**
     * 维护页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(String id,
                        String parentId,
                        Model model) {
        if (!StringUtils.isEmpty(id)) {
            Permission permission = permissionService.findOne(id);
            model.addAttribute("permission", permission);
        }
        model.addAttribute("parentId", parentId);
        return "auth/menu/index";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO save(@Valid PermissionForm permissionForm,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.error(ResultEnum.FORM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionForm,permission);
        Permission result = permissionService.savePermission(permission);
        return ResultVOUtil.success("/menu/list?parentId="+result.getParentId(),null);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO del(@RequestParam("id")String id) {
        Permission permission = permissionService.findOne(id);
        if (permission == null) {
            return ResultVOUtil.error(ResultEnum.DELETE_EMPTY_ERROR.getCode(),
                    ResultEnum.DELETE_EMPTY_ERROR.getMsg());
        }
        String parentId = permission.getParentId();
        permissionService.del(id);
        return ResultVOUtil.success("/menu/list?parentId="+parentId,null);
    }

}
