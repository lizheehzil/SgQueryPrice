package com.sg.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.common.page.Pagination;

import com.sg.dao.BackmangerDao;

@Controller
public class ToListController {

	@Autowired
	private BackmangerDao backmangerDao;
	
	@RequestMapping("listAllNotpads.do")
	public String listAll(@RequestParam(value = "pageNo", required = false)
		Integer pageNo,ModelMap model) {
		if (pageNo == null)
			pageNo = 1;
		Pagination pagination = backmangerDao.getAll(pageNo);
		String url = "listAllNotpads.do";
		pagination.pageView(url,"");
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", "notpads");
		return "listAll";
	}
	@RequestMapping("listAllCPU.do")
	public String listAllcpu(@RequestParam(value = "pageNo", required = false)
		Integer pageNo,ModelMap model) {
		if (pageNo == null)
			pageNo = 1;
		Pagination pagination = backmangerDao.getAllcpu(pageNo);
		String url = "listAllCPU.do";
		pagination.pageView(url,"");
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", "cpu");
		return "listAll";
	}
	@RequestMapping("listAllDisk.do")
	public String listAlldisk(@RequestParam(value = "pageNo", required = false)
		Integer pageNo,ModelMap model) {
		if (pageNo == null)
			pageNo = 1;
		Pagination pagination = backmangerDao.getAlldisk(pageNo);
		String url = "listAllDisk.do";
		pagination.pageView(url,"");
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", "disk");
		return "listAll";
	}
	@RequestMapping("listAllMemories_bar.do")
	public String listAllmemories(@RequestParam(value = "pageNo", required = false)
		Integer pageNo,ModelMap model) {
		if (pageNo == null)
			pageNo = 1;
		Pagination pagination = backmangerDao.getAllmemories(pageNo);
		String url = "listAllMemories_bar.do";
		pagination.pageView(url,"");
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", "memories");
		return "listAll";
	}
	@RequestMapping("listAllMother_board.do")
	public String listAllboard(@RequestParam(value = "pageNo", required = false)
		Integer pageNo,ModelMap model) {
		if (pageNo == null)
			pageNo = 1;
		Pagination pagination = backmangerDao.getAllboard(pageNo);
		String url = "listAllMother_board.do";
		pagination.pageView(url,"");
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", "mother_board");
		return "listAll";
	}
	@RequestMapping("listAllCasee.do")
	public String listAllcasee(@RequestParam(value = "pageNo", required = false)
		Integer pageNo,ModelMap model) {
		if (pageNo == null)
			pageNo = 1;
		Pagination pagination = backmangerDao.getAllcasee(pageNo);
		String url = "listAllCasee.do";
		pagination.pageView(url,"");
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", "casee");
		return "listAll";
	}
	@RequestMapping("listAllGraphics_card.do")
	public String listAllgraphics(@RequestParam(value = "pageNo", required = false)
		Integer pageNo,ModelMap model) {
		if (pageNo == null)
			pageNo = 1;
		Pagination pagination = backmangerDao.getAllgraphics(pageNo);
		String url = "listAllGraphics_card.do";
		pagination.pageView(url,"");
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", "graphics_card");
		return "listAll";
	}
	@RequestMapping("listAllKey_mouse.do")
	public String listAllkeymouse(@RequestParam(value = "pageNo", required = false)
		Integer pageNo,ModelMap model) {
		if (pageNo == null)
			pageNo = 1;
		Pagination pagination = backmangerDao.getAllkeymouse(pageNo);
		String url = "listAllKey_mouse.do";
		pagination.pageView(url,"");
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", "key_mouse");
		return "listAll";
	}
	@RequestMapping("listAllMonitor.do")
	public String listAllmonitor(@RequestParam(value = "pageNo", required = false)
		Integer pageNo,ModelMap model) {
		if (pageNo == null)
			pageNo = 1;
		Pagination pagination = backmangerDao.getAllmonitor(pageNo);
		String url = "listAllMonitor.do";
		pagination.pageView(url,"");
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", "monitor");
		return "listAll";
	}
	@RequestMapping("listAllPower.do")
	public String listAllpower(@RequestParam(value = "pageNo", required = false)
		Integer pageNo,ModelMap model) {
		if (pageNo == null)
			pageNo = 1;
		Pagination pagination = backmangerDao.getAllpower(pageNo);
		String url = "listAllPower.do";
		pagination.pageView(url,"");
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", "power");
		return "listAll";
	}
	@RequestMapping("listAllRadiator.do")
	public String listAllradiator(@RequestParam(value = "pageNo", required = false)
		Integer pageNo,ModelMap model) {
		if (pageNo == null)
			pageNo = 1;
		Pagination pagination = backmangerDao.getAllradiator(pageNo);
		String url = "listAllRadiator.do";
		pagination.pageView(url,"");
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", "radiator");
		return "listAll";
	}
	@RequestMapping("listAllU_disk.do")
	public String listAlludisk(@RequestParam(value = "pageNo", required = false)
		Integer pageNo,ModelMap model) {
		if (pageNo == null)
			pageNo = 1;
		Pagination pagination = backmangerDao.getAlludisk(pageNo);
		String url = "listAllU_disk.do";
		pagination.pageView(url,"");
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", "u_disk");
		return "listAll";
	}
	
}
