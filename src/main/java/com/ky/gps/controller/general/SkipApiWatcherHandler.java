package com.ky.gps.controller.general;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Daye
 * 跳转到api文档接口
 */
@RequestMapping("/api")
@Controller
public class SkipApiWatcherHandler {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toApiIndex(){
        return "api/index_api";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toAdminApi(){
        return "api/general/login_api";
    }

    @RequestMapping(value = "/simulation", method = RequestMethod.GET)
    public String toSimulationApi(){
        return "api/general/simulation_posision_api";
    }

    @RequestMapping(value = "/realTimeQuery", method = RequestMethod.GET)
    public String toRealTimeQueryApi(){
        return "api/client/real_time_query_api";
    }

    @RequestMapping(value = "/simpleUser", method = RequestMethod.GET)
    public String toSimpleUserApi(){
        return "api/client/simple_user_api";
    }

    @RequestMapping(value = "/deptManage", method = RequestMethod.GET)
    public String toDeptManageApi(){
        return "api/backstage/dept_manage_api";
    }
    @RequestMapping(value = "/manageSelf", method = RequestMethod.GET)
    public String toManagerSelfApi(){
        return "api/backstage/manager_self_api";
    }
    @RequestMapping(value = "/userManage", method = RequestMethod.GET)
    public String toUserManageApi(){
        return "api/backstage/user_manage_api";
    }
}