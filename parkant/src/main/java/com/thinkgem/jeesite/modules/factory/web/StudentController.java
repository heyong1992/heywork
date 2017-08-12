/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.factory.web;

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
import com.thinkgem.jeesite.modules.factory.entity.Student;
import com.thinkgem.jeesite.modules.factory.service.StudentService;

/**
 * 产品Controller
 * @author ThinkGem
 * @version 2017-06-29
 */
@Controller
@RequestMapping(value = "${adminPath}/factory/student")
public class StudentController extends BaseController {

	@Autowired
	private StudentService studentService;
	
	@ModelAttribute("student")
	public Student get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return studentService.get(id);
		}else{
			return new Student();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			student.setCreateBy(user);
		}
        Page<Student> page = studentService.find(new Page<Student>(request, response), student); 
        model.addAttribute("page", page);
		return "modules/" + "factory/studentList";
	}

	@RequestMapping(value = "form")
	public String form(Student student, Model model) {
		model.addAttribute("student", student);
		return "modules/" + "factory/studentForm";
	}

	@RequiresPermissions("factory:student:edit")
	@RequestMapping(value = "save")
	public String save(Student student, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, student)){
			return form(student, model);
		}
		studentService.save(student);
		addMessage(redirectAttributes, "保存产品'" + student.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/factory/student/?repage";
	}
	
	@RequiresPermissions("factory:student:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		studentService.delete(id);
		addMessage(redirectAttributes, "删除产品成功");
		return "redirect:"+Global.getAdminPath()+"/factory/student/?repage";
	}

}
