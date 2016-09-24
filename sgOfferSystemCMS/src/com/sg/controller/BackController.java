package com.sg.controller;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sg.model.*;
import com.sg.service.BackService;

/**
 * 后台查看详情、编辑、删除操作
 * @author Administrator
 *
 */

@Controller
public class BackController {
	
	@Resource(name="backService")
	private BackService backService;
	
	
	@RequestMapping("toindex.do")
	public String index(){
		return "index";
	}
	@RequestMapping("detail.do")
	public String toDetail(String pno,String brand,ModelMap model){
		String className=backService.searchItem(pno);
		model.addAttribute("brand",brand);
		model.addAttribute("item", backService.getItem(className,pno));
		return "detail/"+className;
	}
		
	@RequestMapping("remove.do")
	public String remove(String pno){
		String className=backService.searchItem(pno);
		String method="delete"+className;
		try {
			backService.getClass().getDeclaredMethod(method, String.class).invoke(backService,pno);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:listAll"+className+".do";
	}
	
	@RequestMapping("editboard.do")
	public String edit(Mother_board board ,String brand,Model model){
		backService.saveBoard(board);
		model.addAttribute("pno",board.getPno());
		model.addAttribute("brand",brand);
		return "redirect:detail.do";
	}
	@RequestMapping("editcasee.do")
	public String edit(Casee u, String brand,Model model){
		backService.saveCasee(u);
		model.addAttribute("pno",u.getPno());
		model.addAttribute("brand",brand);
		
		return "redirect:detail.do";
	}
	@RequestMapping("editdisk.do")
	public String edit(Disk disk,String brand,Model model){
		backService.saveDisk(disk);
		model.addAttribute("pno",disk.getPno());
		model.addAttribute("brand",brand);
		return "redirect:detail.do";
	}
	@RequestMapping("editmonitor.do")
	public String edit(Monitor u,String brand,Model model){
		backService.saveMonitor(u);
		model.addAttribute("pno",u.getPno());
		model.addAttribute("brand",brand);
		return "redirect:detail.do";
	}
	@RequestMapping("editmemories.do")
	public String edit(Memories_bar u,String brand,Model model){
		backService.saveMemories_bar(u);
		model.addAttribute("pno",u.getPno());
		model.addAttribute("brand",brand);
		return "redirect:detail.do";
	}
	@RequestMapping("editpower.do")
	public String edit(Power u,String brand,Model model){
		backService.savePower(u);
		model.addAttribute("pno",u.getPno());
		model.addAttribute("brand",brand);
		return "redirect:detail.do";
	}
	@RequestMapping("editnotpads.do")
	public String edit(Notpads u,String brand,Model model){
		backService.saveNotpads(u);
		model.addAttribute("pno",u.getPno());
		model.addAttribute("brand",brand);
		return "redirect:detail.do";
	}
	@RequestMapping("editgraphics.do")
	public String edit(Graphics_card u,String brand,Model model){
		backService.saveGraphics_card(u);
		model.addAttribute("pno",u.getPno());
		model.addAttribute("brand",brand);
		return "redirect:detail.do";
	}
	@RequestMapping("editcpu.do")
	public String edit(CPU u,String brand,Model model){
		backService.saveCPU(u);
		model.addAttribute("pno",u.getPno());
		model.addAttribute("brand",brand);
		return "redirect:detail.do";
	}
	@RequestMapping("editkeymouse.do")
	public String edit(Key_mouse u,String brand,Model model){
		backService.saveKey_mouse(u);
		model.addAttribute("pno",u.getPno());
		model.addAttribute("brand",brand);
		return "redirect:detail.do";
	}
	@RequestMapping("editradiator.do")
	public String edit(Radiator u,String brand,Model model){
		backService.saveRadiator(u);
		model.addAttribute("pno",u.getPno());
		model.addAttribute("brand",brand);
		return "redirect:detail.do";
	}
	@RequestMapping("editudisk.do")
	public String edit(U_disk u,String brand,Model model){
		backService.saveU_disk(u);
		model.addAttribute("pno",u.getPno());
		model.addAttribute("brand",brand);
		return "redirect:detail.do";
	}
	
}
