/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.student.entity.TStudent;
import com.thinkgem.jeesite.modules.student.service.TStudentService;

/**
 * 单表生成Controller
 * @author Hey
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/student/tStudent")
public class TStudentController extends BaseController {

	@Autowired
	private TStudentService tStudentService;

	@ModelAttribute
	public TStudent get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return tStudentService.get(id);
		}else{
			return new TStudent();
		}
	}

	@RequiresPermissions("student:tStudent:view")
	@RequestMapping(value = {"list", ""})
	public String list(TStudent tStudent, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			tStudent.setCreateBy(user);
		}
        Page<TStudent> page = tStudentService.find(new Page<TStudent>(request, response), tStudent);
        model.addAttribute("page", page);
		return "modules/" + "student/tStudentList";
	}

	@RequiresPermissions("student:tStudent:view")
	@RequestMapping(value = "form")
	public String form(TStudent tStudent, Model model) {
		model.addAttribute("tStudent", tStudent);
		return "modules/" + "student/tStudentForm";
	}

	@RequiresPermissions("student:tStudent:edit")
	@RequestMapping(value = "save")
	public String save(TStudent tStudent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tStudent)){
			return form(tStudent, model);
		}
		tStudentService.save(tStudent);
		addMessage(redirectAttributes, "保存单表生成'" + tStudent.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/student/tStudent/?repage";
	}

	@RequiresPermissions("student:tStudent:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		tStudentService.delete(id);
		addMessage(redirectAttributes, "删除单表生成成功");
		return "redirect:"+Global.getAdminPath()+"/student/tStudent/?repage";
	}

}