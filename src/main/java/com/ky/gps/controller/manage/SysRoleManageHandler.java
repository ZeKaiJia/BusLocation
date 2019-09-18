package com.ky.gps.controller.manage;

import com.ky.gps.annotation.PermissionName;
import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SysRole;
import com.ky.gps.service.SysRoleService;
import com.ky.gps.util.IntegerUtil;
import com.ky.gps.util.ResultWrapperUtil;
import com.ky.gps.util.SysRoleUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 角色管理
 *
 * @author Daye
 */
@Controller
@RequestMapping("/m/role")
public class SysRoleManageHandler {

    private SysRoleService sysRoleService;

    /**
     * 更新角色记录
     * @param sysRole 待更新的角色
     * @param request 请求域
     * @param response 响应域
     * @return 返回json格式数据
     */
    @RequiresPermissions("role:update")
    @PermissionName(displayName = "角色更新", group = "角色管理")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultWrapper updateRole(@RequestBody SysRole sysRole,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        ResultWrapper resultWrapper;
        if(IntegerUtil.isValid(sysRole.getId())
                && sysRoleService.findById(sysRole.getId()).getData() != null){
            resultWrapper = sysRoleService.updateById(sysRole);
        } else{
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, "更新的角色不存在", response);
        }
        return resultWrapper;
    }

    /**
     * 添加角色记录
     *
     * @param sysRole  待添加的角色对象
     * @param request  请求域
     * @param response 响应域
     * @return 返回json格式数据
     */
    @PermissionName(displayName = "角色添加", group = "角色管理")
    @RequiresPermissions("role:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper addRole(@RequestBody SysRole sysRole,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        ResultWrapper resultWrapper;
        if (!SysRoleUtil.checkEffectiveBeforeInsert(sysRole)) {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, "含有空属性", response);
        } else {
            resultWrapper = sysRoleService.saveRole(sysRole);
        }
        return resultWrapper;
    }

    /**
     * 根据id恢复角色
     *
     * @param params 存放json提交的参数map，包含
     *               id 角色id
     * @return 返回json数据
     */
    @RequiresPermissions("role:rollback")
    @PermissionName(displayName = "角色恢复", group = "角色管理")
    @RequestMapping(value = "/rollback", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper rollbackRoleById(@RequestBody Map<String, Object> params,
                                          HttpServletResponse response) {
        Integer id = (Integer) params.get("id");
        ResultWrapper resultWrapper;
        if (IntegerUtil.isValid(id)) {
            resultWrapper = sysRoleService.rollbackById(id);
        } else {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, response);
        }
        return resultWrapper;
    }

    /**
     * 根据id删除角色
     *
     * @param params 存放json提交的参数map，包含
     *               id 角色id
     * @return 返回json数据
     */
    @RequiresPermissions("role:delete")
    @PermissionName(displayName = "角色删除", group = "角色管理")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper deleteRoleById(@RequestBody Map<String, Object> params,
                                        HttpServletResponse response) {
        Integer id = (Integer) params.get("id");
        ResultWrapper resultWrapper;
        if (IntegerUtil.isValid(id)) {
            resultWrapper = sysRoleService.deleteById(id);
        } else {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, response);
        }
        return resultWrapper;
    }

    /**
     * 查询所有角色信息
     *
     * @return 返回json格式数据
     */
    @RequiresPermissions("role:query")
    @PermissionName(group = "角色管理", displayName = "角色查询")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultWrapper findAllRole() {
        ResultWrapper resultWrapper = sysRoleService.findAllRole();
        return resultWrapper;
    }

    @Autowired
    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }
}