package com.sg.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.common.page.Pagination;

import com.sg.dao.CPUDao;
import com.sg.model.CPU;

@Controller
public class CPUController {
	@Resource(name = "cpuDaoImpl")
	private CPUDao cpuDao;
	
	
	@RequestMapping("getAllCPU.shtml")
	public String querynotpads(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term,
			@RequestParam(value = "term2", required = false) String term2) {
		if (pageNo == null) pageNo = 1;
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t2="&term2="+term2;
		
		
		if(term==null||term.equals("")){ term="";t1="";}
		if(term2==null||term2.equals("")){ term2="";t2="";}
		
		model.addAttribute("term",t1);
		model.addAttribute("term2",t2);
		
		Pagination pagination = cpuDao.getAll(pageNo,term,term2);
		String url = "getAllCPU.shtml";
		terms.append(t1).append(t2);
		pagination.pageView(url, terms.toString());
		model.addAttribute("pagination", pagination);
		return "cpu_list";
	}
	@RequestMapping("detail_cpu.shtml")
	public String todetail(String brand,String category, Integer id, Model model){
		CPU arg=cpuDao.getcpu(id);
		model.addAttribute("arg",arg);
		model.addAttribute("brand",brand);
		model.addAttribute("category", category);
		return "detail/detail_cpu";
	}
}