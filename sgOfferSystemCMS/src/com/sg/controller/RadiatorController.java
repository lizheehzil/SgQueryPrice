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

import com.sg.dao.RadiatorDao;
import com.sg.model.Brand;
import com.sg.model.Radiator;

@Controller
public class RadiatorController {
	@Autowired
	private RadiatorDao radiatorDao;
	
	
	@RequestMapping("getAllRadiator.shtml")
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
		List<Brand> radiatorbrand=radiatorDao.getBrand();
		
		model.addAttribute("radiatorbrand", radiatorbrand);
		Pagination pagination = radiatorDao.getAll(pageNo,term,term2);
		String url = "getAllRadiator.shtml";
		terms.append(t1).append(t2);
		pagination.pageView(url, terms.toString());
		model.addAttribute("pagination", pagination);
		return "radiator_list";
	}
	@RequestMapping("detail_radiator.shtml")
	public String todetail(String brand,String category,Integer id, Model model){
		Radiator arg=radiatorDao.getradiator(id);
		model.addAttribute("arg",arg);
		model.addAttribute("brand",brand);
		model.addAttribute("category", category);
		return "detail/detail_radiator";
	}
}