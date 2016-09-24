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

import com.sg.dao.CaseeDao;
import com.sg.model.Brand;
import com.sg.model.Casee;

@Controller
public class CaseController {
	@Autowired
	private CaseeDao caseeDao;

	@RequestMapping("getAllCasee.shtml")
	public String querynotpads(
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,
			@RequestParam(value = "term", required = false) String term,
			@RequestParam(value = "term2", required = false) String term2) {
		if (pageNo == null)
			pageNo = 1;
		StringBuffer terms = new StringBuffer();

		String t1 = "term=" + term;
		String t2 = "&term2=" + term2;

		if (term == null || term.equals("")) {
			term = "";
			t1 = "";
		}
		if (term2 == null || term2.equals("")) {
			term2 = "";
			t2 = "";
		}

		model.addAttribute("term", t1);
		model.addAttribute("term2", t2);
		List<Brand> caseebrand=caseeDao.getBrand();
		
		model.addAttribute("caseebrand", caseebrand);

		Pagination pagination = caseeDao.getAll(pageNo, term, term2);
		String url = "getAllCasee.shtml";
		terms.append(t1).append(t2);
		pagination.pageView(url, terms.toString());
		model.addAttribute("pagination", pagination);
		return "case_list";
	}
	@RequestMapping("detail_casee.shtml")
	public String todetail(String brand,String category, Integer id, Model model){
		Casee arg=caseeDao.getcasee(id);
		model.addAttribute("arg",arg);
		model.addAttribute("brand",brand);
		model.addAttribute("category", category);
		return "detail/detail_casee";
	}
}
