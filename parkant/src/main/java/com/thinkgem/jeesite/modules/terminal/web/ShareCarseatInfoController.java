/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.terminal.vo.ReturnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.terminal.entity.ShareCarseatInfo;
import com.thinkgem.jeesite.modules.terminal.service.ShareCarseatInfoService;

import java.util.List;

/**
 * 共享车位信息Controller
 * @author Hey
 * @version 2017-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/terminal/shareCarseat")
public class ShareCarseatInfoController extends BaseController {

	@Autowired
	private ShareCarseatInfoService shareCarseatInfoService;


	/**
	 * 获取共享车位
	 */
	@RequestMapping(value = "get")
	@ResponseBody
	public ShareCarseatInfo get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return shareCarseatInfoService.get(id);
		}else{
			return new ShareCarseatInfo();
		}
	}
	/**
	 * 发布车位共享
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public ReturnVo create(ShareCarseatInfo shareCarseatInfo, Model model, RedirectAttributes redirectAttributes) {
		ReturnVo returnVo=new ReturnVo();
		returnVo.setSuccess("true");
		returnVo.setReason("发布成功");
		shareCarseatInfoService.save(shareCarseatInfo);
		return returnVo;
	}

	/**
	 * 获取共享车位列表
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public List<ShareCarseatInfo> list(ShareCarseatInfo shareCarseatInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<ShareCarseatInfo> page = shareCarseatInfoService.find(new Page<ShareCarseatInfo>(request, response), shareCarseatInfo);
		return page.getList();
	}


}