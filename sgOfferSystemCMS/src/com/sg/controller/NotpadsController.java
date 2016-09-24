package com.sg.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.common.page.Pagination;

import com.sg.dao.NotpadsDao;
import com.sg.model.Brand;
import com.sg.model.Notpads;



@Controller
public class NotpadsController {
	@Autowired
	private NotpadsDao notpadsDao;
	
	
	@RequestMapping("getNotpads.shtml")
	public String querynotpads(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term,
			@RequestParam(value = "term2", required = false) String term2,
			@RequestParam(value = "term3", required = false) String term3,
			@RequestParam(value = "term4", required = false) String term4
			) {
		if (pageNo == null) pageNo = 1;
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t2="&term2="+term2;
		String t3="&term3="+term3;
		String t4="&term4="+term4;
		
		if(term==null||term.equals("")){ term="";t1="";}
		if(term2==null||term2.equals("")){ term2="";t2="";}
		if(term3==null||term3.equals("")){ term3="";t3="";}
		if(term4==null||term4.equals("")){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		model.addAttribute("term2",t2);
		model.addAttribute("term3",t3);
		model.addAttribute("term4",t4);
		
		List<Brand> notpadsbrand=notpadsDao.getBrand();
		
		model.addAttribute("notpadsbrand", notpadsbrand);
		Pagination pagination = notpadsDao.getAll(pageNo,term,term2,term3,term4);
		String url = "getNotpads.shtml";
		terms.append(t1).append(t2).append(t3).append(t4);
		pagination.pageView(url, terms.toString());
		model.addAttribute("pagination", pagination);
		return "index";
	}
	@RequestMapping("detail_notpads.shtml")
	public String todetail(String brand,String category,Integer id, Model model){
		Notpads arg=notpadsDao.getnotpads(id);
		model.addAttribute("arg",arg);
		model.addAttribute("brand",brand);
		model.addAttribute("category", category);
		return "detail/detail_notpads";
	}
}
