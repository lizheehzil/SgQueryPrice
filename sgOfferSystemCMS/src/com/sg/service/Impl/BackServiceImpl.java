package com.sg.service.Impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.sg.model.CPU;
import com.sg.model.Casee;
import com.sg.model.Disk;
import com.sg.model.Graphics_card;
import com.sg.model.Key_mouse;
import com.sg.model.Memories_bar;
import com.sg.model.Monitor;
import com.sg.model.Mother_board;
import com.sg.model.Notpads;
import com.sg.model.Power;
import com.sg.model.Radiator;
import com.sg.model.U_disk;
import com.sg.mybatisdao.*;
import com.sg.service.BackService;

@Service("backService")
public class BackServiceImpl implements BackService{

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
	
	@Override
	public String searchItem(String pno) {
		String className=utilDao.searchItem(pno);
		//System.out.println(className);
		return className;
	}

	@Override
	public Object getItem(String className,String pno) {
		if(className.equals("Notpads"))
			return notpadsDao.getItem(pno);
		if(className.equals("Casee"))
			return caseeDao.getItem(pno);
		if(className.equals("Disk"))
			return diskDao.getItem(pno);
		if(className.equals("CPU"))
			return cpuDao.getItem(pno);
		if(className.equals("Graphics_card"))
			return graphics_cardDao.getItem(pno);
		if(className.equals("Memories_bar"))
			return memories_barDao.getItem(pno);
		if(className.equals("Monitor"))
			return monitorDao.getItem(pno);
		if(className.equals("Mother_board"))
			return mother_boardDao.getItem(pno);
		if(className.equals("Key_mouse"))
			return key_mouseDao.getItem(pno);
		if(className.equals("U_disk"))
			return u_diskDao.getItem(pno);
		if(className.equals("Power"))
			return powerDao.getItem(pno);
		if(className.equals("Radiator"))
			return radiatorDao.getItem(pno);
		return null;
	}
	
	@Override
	public int saveBoard(Mother_board u){
		return mother_boardDao.saveBoard(u);
	}

	@Override
	public int saveCasee(Casee u) {
		return caseeDao.saveCasee(u);
	}

	@Override
	public int saveDisk(Disk u) {
		return diskDao.saveDisk(u);
	}

	@Override
	public int saveMonitor(Monitor u) {
		return monitorDao.saveMonitor(u);
	}

	@Override
	public int saveMemories_bar(Memories_bar u) {
		return memories_barDao.saveMemories(u);
	}

	@Override
	public int savePower(Power u) {
		return powerDao.savePower(u);
	}

	@Override
	public int saveNotpads(Notpads u) {
		return notpadsDao.saveNotpads(u);
	}

	@Override
	public int saveGraphics_card(Graphics_card u) {
		return graphics_cardDao.saveGraphics(u);
	}

	@Override
	public int saveCPU(CPU u) {
		return cpuDao.saveCPU(u);
	}

	@Override
	public int saveKey_mouse(Key_mouse u) {
		return key_mouseDao.saveKeymouse(u);
	}

	@Override
	public int saveRadiator(Radiator u) {
		return radiatorDao.saveRadiator(u);
	}

	@Override
	public int saveU_disk(U_disk u) {
		return u_diskDao.saveUdisk(u);
	}

	@Override
	public void deleteBoard(String pno) {
		mother_boardDao.deleteBoard(pno);
	}

	@Override
	public void deleteCasee(String pno) {
		caseeDao.deleteCasee(pno);
	}

	@Override
	public void deleteDisk(String pno) {
		diskDao.deleteDisk(pno);
	}

	@Override
	public void deleteMonitor(String pno) {
		monitorDao.deleteMonitor(pno);
		
	}

	@Override
	public void deleteMemories_bar(String pno) {
		memories_barDao.deleteMemories_bar(pno);
	}

	@Override
	public void deletePower(String pno) {
		powerDao.deletePower(pno);
	}

	@Override
	public void deleteNotpads(String pno) {
		notpadsDao.deleteNotpads(pno);
	}

	@Override
	public void deleteCPU(String pno) {
		cpuDao.deleteCPU(pno);
	}

	@Override
	public void deleteGraphics_card(String pno) {
		graphics_cardDao.deleteGraphics_card(pno);
	}

	@Override
	public void deleteKey_mouse(String pno) {
		key_mouseDao.deleteKey_mouse(pno);
	}

	@Override
	public void deleteRadiator(String pno) {
		radiatorDao.deleteRadiator(pno);
	}

	@Override
	public void deleteU_disk(String pno) {
		u_diskDao.deleteU_disk(pno);
	}

}
