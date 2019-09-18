package com.ky.gps.controller.manage;

import com.ky.gps.annotation.PermissionName;
import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbRoleAuthority;
import com.ky.gps.entity.SysAuthorityExtractAttribute;
import com.ky.gps.service.SbRoleAuthorityService;
import com.ky.gps.service.SysRoleService;
import com.ky.gps.util.IntegerUtil;
import com.ky.gps.util.ResultWrapperUtil;
import com.ky.gps.util.SbRoleAuthorityUtil;
import javafx.beans.binding.ObjectBinding;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 角色权限管理处理器
 *
 * @author Daye
 */
@RequestMapping("/m/roleAuthority")
@Controller
public class SbRoleAuthorityManageHandler {

    private SysRoleService sysRoleService;
    private SbRoleAuthorityService sbRoleAuthorityService;

    /**
     * 添加角色的操作权限
     *
     * @return 返回json格式数据
     */
    @PermissionName(displayName = "角色权限添加", group = "角色权限管理")
    @RequiresPermissions("roleAuthority:add")
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultWrapper addRecording(@RequestBody Map<String, Object> params,
                                      HttpServletResponse response,
                                      HttpServletRequest request) {
        ResultWrapper resultWrapper;
        //提取角色id
        Integer id = (Integer) params.get("id");
        //id不为空且存在
        if (IntegerUtil.isValid(id) && sysRoleService.findById(id).getData() != null) {
            //提取所有权限信息
            List<Map<String, Object>> authorityMap = (List<Map<String, Object>>) params.get("authority");
            //查询角色id的所有权限id
            List<Integer> authorityIdList = (List<Integer>) sbRoleAuthorityService.findAuthorityIdByRoleId(id).getData();
            //提取提交的权限信息中checked=true的权限id
            List<Integer> idList = SbRoleAuthorityUtil.extractIdFromParam(authorityMap, authorityIdList);
            resultWrapper = sbRoleAuthorityService.batchSaveRoleIdAndAuthorityId(id, idList);
        } else {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, "角色id不存在或权限参数为空", response);
        }
        return resultWrapper;
    }

    @Autowired
    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @Autowired
    public void setSbRoleAuthorityService(SbRoleAuthorityService sbRoleAuthorityService) {
        this.sbRoleAuthorityService = sbRoleAuthorityService;
    }
}
