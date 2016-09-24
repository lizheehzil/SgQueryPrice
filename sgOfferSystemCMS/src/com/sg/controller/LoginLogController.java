/**
 * @package com.sg.controller
 * @description TODO
 * @author lizhe
 * @modify 2016年9月23日 下午1:06:26 
 */
package com.sg.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;

import com.sg.model.LoginLog;
import com.sg.model.LoginLogExample;
import com.sg.service.LoginLogService;

/**
 * @（#) LoginLogController
 * @description 登录日志
 * @author lizhe
 * @version
 * @modify 2016年9月23日
 */
@Controller
public class LoginLogController {
	@Autowired
	private LoginLogService loginLogService;
	
	/**
	 * 
	 * @description 跳转到日志列表 
	 * @param pageNo
	 * @return
	 * String
	 * @throws 
	 * @author lizhe
	 */
	@RequestMapping("/toLoginLogList.do")
	public String toLoginLogList(Integer pageNo, ModelMap model){
		//初始化当前页
		pageNo = Pagination.cpn(pageNo);
		Integer pageSize = 5;//每页大小
		StringBuilder params = new StringBuilder();
		
		LoginLogExample logExample = new LoginLogExample();//查询条件
		
		try {
			Pagination pagination = new Pagination(pageNo,pageSize,loginLogService.countByExample(logExample));
			List<LoginLog> loginLogs = loginLogService.selectByExample(logExample);
			pagination.setList(loginLogs);//放入list集合
			
			pagination.pageView("toLoginLogList.shtml", params.toString());//分页信息
			model.addAttribute("pagination", pagination);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "employeelog";
	}
	
	/**
	 * 
	 * @description 删除登录日志信息 
	 * @param ids
	 * @return
	 * String
	 * @throws 
	 * @author lizhe
	 */
	@RequestMapping("/deleteLoginLog.shtml")
	public String deleteLoginLog(String[] ids) {
		if(ids!=null&& ids.length>0)
		{
			try {
				List<String> delIds = Arrays.asList(ids);
				LoginLogExample logExample = new LoginLogExample();
				logExample.createCriteria().andIdIn(delIds);
				loginLogService.deleteByExample(logExample);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "redirect:toLoginLogList.do";
	}
}
