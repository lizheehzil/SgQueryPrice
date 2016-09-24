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

import com.sg.dao.Graphics_cardDao;
import com.sg.model.Brand;
import com.sg.model.Graphics_card;

@Controller
public class GraphicsController {
	@Autowired
	private Graphics_cardDao graphics_cardDao;
	
	
	@RequestMapping("getAllGraphics.shtml")
	public String querynotpads(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term,
			@RequestParam(value = "term2", required = false) String term2,
			@RequestParam(value = "term3", required = false) String term3) {
		if (pageNo == null) pageNo = 1;
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t2="&term2="+term2;
		String t3="&term3="+term3;
		
		
		if(term==null||term.equals("")){ term="";t1="";}
		if(term2==null||term2.equals("")){ term2="";t2="";}
		if(term3==null||term3.equals("")){ term3="";t3="";}
		
		model.addAttribute("term",t1);
		model.addAttribute("term2",t2);
		model.addAttribute("term3",t3);
List<Brand> graphicsbrand=graphics_cardDao.getBrand();
		
		model.addAttribute("graphicsbrand", graphicsbrand);
		Pagination pagination = graphics_cardDao.getAll(pageNo,term,term2, term3);
		String url = "getAllGraphics.shtml";
		terms.append(t1).append(t2).append(t3);
		pagination.pageView(url, terms.toString());
		model.addAttribute("pagination", pagination);
		return "graphics_list";
	}
	@RequestMapping("detail_graphics.shtml")
	public String todetail(String brand,String category, Integer id ,Model model){
		Graphics_card arg=graphics_cardDao.getgraphics(id);
		model.addAttribute("brand",brand);
		model.addAttribute("category", category);
		model.addAttribute("arg",arg);
		return "detail/detail_graphics";
	}
}
