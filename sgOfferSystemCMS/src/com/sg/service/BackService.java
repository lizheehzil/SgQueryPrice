package com.sg.service;

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

public interface BackService {
	
	public String searchItem(String pno);
	public Object getItem(String classname,String pno);
	public int saveBoard(Mother_board mother_board);
	public int saveCasee(Casee casee);
	public int saveDisk(Disk disk);
	public int saveMonitor(Monitor casee);
	public int saveMemories_bar(Memories_bar casee);
	public int savePower(Power casee);
	public int saveNotpads(Notpads u);
	public int saveGraphics_card(Graphics_card u);
	public int saveCPU(CPU u);
	public int saveKey_mouse(Key_mouse u);
	public int saveRadiator(Radiator u);
	public int saveU_disk(U_disk u);
	public void deleteBoard(String pno);
	public void deleteCasee(String pno);
	public void deleteDisk(String pno);
	public void deleteMonitor(String pno);
	public void deleteMemories_bar(String pno);
	public void deletePower(String pno);
	public void deleteNotpads(String pno);
	public void deleteCPU(String pno);
	public void deleteGraphics_card(String pno);
	public void deleteKey_mouse(String pno);
	public void deleteRadiator(String pno);
	public void deleteU_disk(String pno);
}
