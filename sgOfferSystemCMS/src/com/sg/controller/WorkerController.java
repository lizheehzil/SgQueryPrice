package com.sg.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.common.page.Pagination;

import com.sg.dao.MessageDao;
import com.sg.dao.UtilDao;
import com.sg.model.Itemvo;
import com.sg.model.LoginLog;
import com.sg.model.Message;
import com.sg.model.Worker;
import com.sg.service.BackService;
import com.sg.service.LoginLogService;
import com.sg.service.WokerService;

@Controller
public class WorkerController {
	
	@Autowired
	private LoginLogService loginLogService;//登录日志service
	
	@Resource(name="workerService")
	private WokerService workerService;
	
	@Resource(name="backService")
	private BackService backService;
	
	@Autowired
	private UtilDao utilDao;
	@Autowired
	private MessageDao messageDao;
	
	@RequestMapping("front.login")
	public String tofrontLogin(){
		return "frontlogin";
	}
	
	@RequestMapping("back.login")
	public String tobackLogin(){
		return "backlogin";
	}
	
	
	@RequestMapping("feedback.do")
	public String feedback(@RequestParam(value = "msg")Integer msgid,
			@RequestParam(value = "pno")String pno ,String category,String brand,ModelMap map){
		map.addAttribute("category",category);
		map.addAttribute("brand",brand);
		map.addAttribute("msg",messageDao.getMessage(msgid));
		map.addAttribute("item", backService.getItem(utilDao.searchItem(pno), pno));
		return "feedback";
	}
	
	@RequestMapping("toNotice.do")
	public String tonotice(ModelMap map,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			HttpServletRequest req){
		Integer uid=(Integer)req.getSession().getAttribute("uid");
		if(pageNo==null){
			pageNo=1;
		}
		Integer pageSize=8;
		Integer count=messageDao.getTotolCount();
		Integer fromIndex= (pageNo - 1) * pageSize;
		
		messageDao.readAll(uid);
		List<Message> ms=messageDao.getAllNotice(uid,fromIndex,pageSize);

		List<Itemvo> list=new ArrayList<Itemvo>();
		for (Message message : ms) {
			String className;
			try {
				className = backService.searchItem(message.getTitle());
				Itemvo vo=messageDao.getItem(className,message.getTitle());
				vo.setFrom(message.getFrom().intValue());
				vo.setFromName(message.getFromName());
				vo.setId(message.getId());
				vo.setDate(message.getDate());
				vo.setRemark(message.getType().toString());
				vo.setText(message.getText());
				list.add(vo);
			} catch (Exception e) {
				
			}
			
		}
		map.addAttribute("items", list);
		Pagination pagination = new Pagination(pageNo, pageSize, count,list);
		String url = "toNotice.do";
		pagination.pageView(url,null);
		map.addAttribute("pagination", pagination);
		
		return "notice";
	}
	
	
	@RequestMapping("toNotice.shtml")
	public String toNotice(ModelMap map,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			HttpServletRequest req){
		Integer uid=(Integer)req.getSession().getAttribute("uid");
		if(pageNo==null){
			pageNo=1;
		}
		Integer pageSize=8;
		Integer count=messageDao.getCount(uid);
		Integer fromIndex= (pageNo - 1) * pageSize;
		
		messageDao.readAll(uid);
		List<Message> ms=messageDao.getMyNotice(uid,fromIndex,pageSize);

		List<Itemvo> list=new ArrayList<Itemvo>();
		for (Message message : ms) {
			String className;
			try {
				className = backService.searchItem(message.getTitle());
				Itemvo vo=messageDao.getItem(className,message.getTitle());
				vo.setId(message.getId());
				vo.setDate(message.getDate());
				vo.setRemark(message.getType().toString());
				vo.setText(message.getText());
				list.add(vo);
			} catch (Exception e) {
				
			}
			
		}
		map.addAttribute("items", list);
		Pagination pagination = new Pagination(pageNo, pageSize, count,list);
		String url = "toNotice.shtml";
		pagination.pageView(url,null);
		map.addAttribute("pagination", pagination);
		
		return "notice";
	}
	
	
	@RequestMapping("backlogin.login")
	public String dobackLogin(@RequestParam(value="username") String username,
			@RequestParam(value="pwd") String pwd,
			HttpServletRequest request){
		Worker worker = workerService.login(username, pwd,2);
		
		if(worker!=null){
			HttpSession session = request.getSession();
			session.setAttribute("allbrand", utilDao.getAllBrand());
			session.setAttribute("allcategory", utilDao.getAllCategory());
			session.setAttribute("uid", worker.getId());
			session.setAttribute("worker", worker);
			
			return "redirect:toindex.do";
		}
		return "redirect:back.login";
	}
	
	@RequestMapping("frontlogin.login")
	public String dofrontLogin(@RequestParam(value="username") String username,
			@RequestParam(value="pwd") String pwd,
			HttpServletRequest request){
		Worker worker = workerService.login(username, pwd,1);
		if(worker!=null){
			HttpSession session = request.getSession();
			session.setAttribute("allbrand", utilDao.getAllBrand());
			session.setAttribute("allcategory", utilDao.getAllCategory());
			session.setAttribute("worker", worker);
			session.setAttribute("uid", worker.getId());
			
			//登录成功插入登录日志表信息========
			//id生成
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String id = sdf.format(new Date());
			
			LoginLog log = new LoginLog(id,new Date(),worker.getId(),1,worker.getName());//dao中没有区分前后台登录用户字段,前端登录默认改为1
			loginLogService.insert(log);
			//===========================
			return "redirect:getNotpads.shtml";
		}
		return "redirect:front.login";
	}
	
	@RequestMapping("logout.do")
	public String logoutb(HttpSession session){
		session.invalidate();
		return "redirect:back.login";
	}
	@RequestMapping("logout.shtml")
	public String logoutf(HttpSession session){
		session.invalidate();
		return "redirect:front.login";
	}
}
