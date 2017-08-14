/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.terminal.entity.ShareCarseatInfo;
import com.thinkgem.jeesite.modules.terminal.service.ShareCarseatInfoService;

/**
 * 共享车位信息Controller
 * @author Hey
 * @version 2017-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/sharecarseatinfo/shareCarseatInfo")
public class ShareCarseatInfoController extends BaseController {

	@Autowired
	private ShareCarseatInfoService shareCarseatInfoService;

	@ModelAttribute
	public ShareCarseatInfo get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return shareCarseatInfoService.get(id);
		}else{
			return new ShareCarseatInfo();
		}
	}

	//@RequiresPermissions("sharecarseatinfo:shareCarseatInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShareCarseatInfo shareCarseatInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			shareCarseatInfo.setCreateBy(user);
		}
        Page<ShareCarseatInfo> page = shareCarseatInfoService.find(new Page<ShareCarseatInfo>(request, response), shareCarseatInfo);
        model.addAttribute("page", page);
		return "modules/" + "sharecarseatinfo/shareCarseatInfoList";
	}

	//@RequiresPermissions("sharecarseatinfo:shareCarseatInfo:view")
	@RequestMapping(value = "form")
	public String form(ShareCarseatInfo shareCarseatInfo, Model model) {
		model.addAttribute("shareCarseatInfo", shareCarseatInfo);
		return "modules/" + "sharecarseatinfo/shareCarseatInfoForm";
	}

	//@RequiresPermissions("sharecarseatinfo:shareCarseatInfo:edit")
	@RequestMapping(value = "save")
	public String save(ShareCarseatInfo shareCarseatInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shareCarseatInfo)){
			return form(shareCarseatInfo, model);
		}
		shareCarseatInfoService.save(shareCarseatInfo);
		//addMessage(redirectAttributes, "保存共享车位信息'" + shareCarseatInfo.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/sharecarseatinfo/shareCarseatInfo/?repage";
	}

	//@RequiresPermissions("sharecarseatinfo:shareCarseatInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		shareCarseatInfoService.delete(id);
		addMessage(redirectAttributes, "删除共享车位信息成功");
		return "redirect:"+Global.getAdminPath()+"/sharecarseatinfo/shareCarseatInfo/?repage";
	}

}