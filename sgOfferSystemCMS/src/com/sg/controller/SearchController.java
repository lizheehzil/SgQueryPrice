package com.sg.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.common.page.Pagination;

import com.sg.dao.CPUDao;
import com.sg.dao.CaseeDao;
import com.sg.dao.DiskDao;
import com.sg.dao.Graphics_cardDao;
import com.sg.dao.Key_mouseDao;
import com.sg.dao.Memories_barDao;
import com.sg.dao.MonitorDao;
import com.sg.dao.Mother_boardDao;
import com.sg.dao.NotpadsDao;
import com.sg.dao.PowerDao;
import com.sg.dao.RadiatorDao;
import com.sg.dao.U_diskDao;
import com.sg.dao.UtilDao;
import com.sg.model.Brand;
import com.sg.mybatisdao.*;

@Controller
public class SearchController {
	
	@Autowired
	private NotpadsDao notpadsDao;
	@Resource(name = "cpuDaoImpl")
	private CPUDao cpuDao;
	@Autowired
	private UtilDao utilDao;
	@Autowired
	private CaseeDao caseeDao;
	@Autowired
	private DiskDao diskDao;
	@Autowired
	private Graphics_cardDao graphics_cardDao;
	@Autowired
	private Key_mouseDao key_mouseDao;
	@Autowired
	private Memories_barDao memories_barDao;
	@Autowired
	private MonitorDao monitorDao;
	@Autowired
	private Mother_boardDao mother_boardDao;
	@Autowired
	private PowerDao powerDao;
	@Autowired
	private RadiatorDao radiatorDao;
	@Autowired
	private U_diskDao u_diskDao;
	
	@RequestMapping("searchcpu.shtml")
	public String searchcpu(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term,
			@RequestParam(value = "term4", required = false) String term4){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = cpuDao.searchcpu(pageNo, term ,term4);
		String url = "searchcpu.shtml";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		model.addAttribute("pagination", pagination);
		return "cpu_list";
	}
	@RequestMapping("searchcasee.shtml")
	public String searchcasee(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = caseeDao.searchcasee(pageNo, term ,term4);
		String url = "searchcasee.shtml";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> caseebrand=caseeDao.getBrand();
		model.addAttribute("caseebrand", caseebrand);
		model.addAttribute("pagination", pagination);
		return "case_list";
	}
	@RequestMapping("searchdisk.shtml")
	public String searchdisk(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4){
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = diskDao.searchdisk(pageNo, term ,term4);
		String url = "searchdisk.shtml";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> diskbrand=diskDao.getBrand();
		model.addAttribute("diskbrand", diskbrand);
		model.addAttribute("pagination", pagination);
		return "disk_list";
	}
	@RequestMapping("searchgraphics.shtml")
	public String searchgraphics(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = graphics_cardDao.searchgraphics(pageNo, term ,term4);
		String url = "searchgraphics.shtml";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> graphicsbrand=graphics_cardDao.getBrand();
		model.addAttribute("graphicsbrand", graphicsbrand);
		model.addAttribute("pagination", pagination);
		return "graphics_list";
	}
	@RequestMapping("searchkeymouse.shtml")
	public String searchkeymouse(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = key_mouseDao.searchkeymouse(pageNo, term ,term4);
		String url = "searchkeymouse.shtml";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> keymousebrand=key_mouseDao.getBrand();
		model.addAttribute("keymousebrand", keymousebrand);
		model.addAttribute("pagination", pagination);
		return "keymouse";
	}
	@RequestMapping("searchmemories.shtml")
	public String searchmemories(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = memories_barDao.searchmemories(pageNo, term ,term4);
		String url = "searchmemories.shtml";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> memoriesbrand=memories_barDao.getBrand();
		model.addAttribute("memoriesbrand", memoriesbrand);
		model.addAttribute("pagination", pagination);
		return "memories";
	}
	@RequestMapping("searchmonitor.shtml")
	public String searchmonitor(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = monitorDao.searchmonitor(pageNo, term ,term4);
		String url = "searchmonitor.shtml";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> monitorbrand=monitorDao.getBrand();
		model.addAttribute("monitorbrand", monitorbrand);
		model.addAttribute("pagination", pagination);
		return "monitor_list";
	}
	@RequestMapping("searchnotpads.shtml")
	public String searchnotpads(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term,
			@RequestParam(value = "term4", required = false) String term4){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = notpadsDao.searchnotpads(pageNo, term ,term4);
		String url = "searchnotpads.shtml";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> notpadsbrand=notpadsDao.getBrand();
		model.addAttribute("notpadsbrand",notpadsbrand);
		model.addAttribute("pagination", pagination);
		return "index";
	}
	@RequestMapping("searchpower.shtml")
	public String searchpower(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		Pagination pagination = powerDao.searchpower(pageNo, term ,term4);
		String url = "searchpower.shtml";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> powerbrand=powerDao.getBrand();
		model.addAttribute("powerbrand", powerbrand);
		model.addAttribute("pagination", pagination);
		return "power_list";
	}
	@RequestMapping("searchradiator.shtml")
	public String searchradiator(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4){
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = radiatorDao.searchradiator(pageNo, term ,term4);
		String url = "searchradiator.shtml";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> radiatorbrand=radiatorDao.getBrand();
		model.addAttribute("radiatorbrand", radiatorbrand);
		model.addAttribute("pagination", pagination);
		return "radiator_list";
	}
	@RequestMapping("searchudisk.shtml")
	public String searchudisk(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = u_diskDao.searchudisk(pageNo, term ,term4);
		String url = "searchudisk.shtml";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> udiskbrand=u_diskDao.getBrand();
		model.addAttribute("udiskbrand", udiskbrand);
		model.addAttribute("pagination", pagination);
		return "udisk";
	}
	@RequestMapping("searchboard.shtml")
	public String searchboard(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = mother_boardDao.searchboard(pageNo, term ,term4);
		String url = "searchboard.shtml";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> boardbrand=mother_boardDao.getBrand();
		model.addAttribute("boardbrand", boardbrand);
		model.addAttribute("pagination", pagination);
		return "board_list";
	}
	@RequestMapping("searchnotpads.do")
	public String notpads(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term,
			@RequestParam(value = "term4", required = false) String term4,
			@RequestParam(value = "type", required = false) String type){
		
		if (pageNo == null) pageNo = 1;
		StringBuffer terms=new StringBuffer();
		String t1="term="+term;
		String t4="term4="+term4;
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		model.addAttribute("term",t1);
		Pagination pagination = notpadsDao.searchnotpads(pageNo,term,term4);
		String url = "searchnotpads.do";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> notpadsbrand=notpadsDao.getBrand();
		model.addAttribute("notpadsbrand",notpadsbrand);
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", type);
		return "listAll";
	}
	@RequestMapping("searchcpu.do")
	public String searchcpu(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term,
			@RequestParam(value = "term4", required = false) String term4,
			@RequestParam(value = "type", required = false) String type){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = cpuDao.searchcpu(pageNo, term ,term4);
		String url = "searchcpu.do";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", type);
		return "listAll";
	}
	@RequestMapping("searchcasee.do")
	public String searchcasee(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4,
			
			@RequestParam(value = "type", required = false) String type){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = caseeDao.searchcasee(pageNo, term ,term4);
		String url = "searchcasee.do";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> caseebrand=caseeDao.getBrand();
		model.addAttribute("caseebrand", caseebrand);
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", type);
		return "listAll";
	}
	@RequestMapping("searchdisk.do")
	public String searchdisk(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4,
			@RequestParam(value = "type", required = false) String type){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = diskDao.searchdisk(pageNo, term ,term4);
		String url = "searchdisk.do";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> diskbrand=diskDao.getBrand();
		model.addAttribute("diskbrand", diskbrand);
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", type);
		return "listAll";
	}
	@RequestMapping("searchgraphics_card.do")
	public String searchgraphics(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4,
			@RequestParam(value = "type", required = false) String type){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = graphics_cardDao.searchgraphics(pageNo, term ,term4);
		String url = "searchgraphics_card.do";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> graphicsbrand=graphics_cardDao.getBrand();
		model.addAttribute("graphicsbrand", graphicsbrand);
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", type);
		return "listAll";
	}
	@RequestMapping("searchkey_mouse.do")
	public String searchkeymouse(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4,
			@RequestParam(value = "type", required = false) String type){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = key_mouseDao.searchkeymouse(pageNo, term ,term4);
		String url = "searchkey_mouse.do";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> keymousebrand=key_mouseDao.getBrand();
		model.addAttribute("keymousebrand", keymousebrand);
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", type);
		return "listAll";
	}
	@RequestMapping("searchmemories.do")
	public String searchmemories(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4,
			@RequestParam(value = "type", required = false) String type){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = memories_barDao.searchmemories(pageNo, term ,term4);
		String url = "searchmemories.do";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> memoriesbrand=memories_barDao.getBrand();
		model.addAttribute("memoriesbrand", memoriesbrand);
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", type);
		return "listAll";
	}
	@RequestMapping("searchmonitor.do")
	public String searchmonitor(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4,
			@RequestParam(value = "type", required = false) String type){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = monitorDao.searchmonitor(pageNo, term ,term4);
		String url = "searchmonitor.do";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> monitorbrand=monitorDao.getBrand();
		model.addAttribute("monitorbrand", monitorbrand);
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", type);
		return "listAll";
	}
	
	@RequestMapping("searchpower.do")
	public String searchpower(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4,
			@RequestParam(value = "type", required = false) String type){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = powerDao.searchpower(pageNo, term ,term4);
		String url = "searchpower.do";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> powerbrand=powerDao.getBrand();
		model.addAttribute("powerbrand", powerbrand);
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", type);
		return "listAll";
	}
	@RequestMapping("searchradiator.do")
	public String searchradiator(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4,
			@RequestParam(value = "type", required = false) String type){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = radiatorDao.searchradiator(pageNo, term ,term4);
		String url = "searchradiator.do";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> radiatorbrand=radiatorDao.getBrand();
		model.addAttribute("radiatorbrand", radiatorbrand);
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", type);
		return "listAll";
	}
	@RequestMapping("searchu_disk.do")
	public String searchudisk(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4,
			@RequestParam(value = "type", required = false) String type){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = u_diskDao.searchudisk(pageNo, term ,term4);
		String url = "searchu_disk.do";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> udiskbrand=u_diskDao.getBrand();
		model.addAttribute("udiskbrand", udiskbrand);
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", type);
		return "listAll";
	}
	@RequestMapping("searchmother_board.do")
	public String searchboard(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			ModelMap model,@RequestParam(value = "term", required = false) String term
			,@RequestParam(value = "term4", required = false) String term4,
			@RequestParam(value = "type", required = false) String type){
		
		if (pageNo == null) pageNo = 1;
		
		StringBuffer terms=new StringBuffer();
		
		String t1="term="+term;
		String t4="term4="+term4;
		
		if(term==null){ term="";t1="";}
		if(term4==null){ term4="";t4="";}
		
		model.addAttribute("term",t1);
		
		Pagination pagination = mother_boardDao.searchboard(pageNo, term ,term4);
		String url = "searchmother_board.do";
		terms.append(t1).append(t4);
		pagination.pageView(url, terms.toString());
		List<Brand> boardbrand=mother_boardDao.getBrand();
		model.addAttribute("boardbrand", boardbrand);
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", type);
		return "listAll";
	}
	}	
