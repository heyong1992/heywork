package com.thinkgem.jeesite.modules.terminal.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/12 0012.
 */
@Controller
@RequestMapping(value = "${adminPath}/terminal/user")
public class UserTerminalController extends BaseController {

    @Autowired
    private SystemService systemService;

    /**
     * 创建用户
     */
    @RequestMapping("create")
    @ResponseBody
    public Map<String,String> create(User user) {
        Map<String,String> map=new HashMap<String, String>();

        if (!"true".equals(checkLoginName(null, user.getLoginName()))) {
            map.put("success","false");
            map.put("reason","保存用户'" + user.getLoginName() + "'失败，登录名已存在");
            return map;
        }
        user.setCompany(new Office("1"));
        user.setOffice(new Office("1"));
        //加密
        user.setPassword(SystemService.entryptPassword(user.getPassword()));
        // 保存用户信息
        systemService.saveUser(user);
        map.put("success","true");
        return map;
    }

    /**
     * 用户登录
     */
    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(User user) {
        Map<String,Object> map=new HashMap<String, Object>();
        User u = UserUtils.getUser();
        map.put("success",u);

        return map;
    }

    @ResponseBody
    @RequestMapping("checkLoginName")
    public String checkLoginName(String oldLoginName, String loginName) {
        if (loginName != null && loginName.equals(oldLoginName)) {
            return "true";
        } else if (loginName != null && systemService.getUserByLoginName(loginName) == null) {
            return "true";
        }
        return "false";
    }
}
