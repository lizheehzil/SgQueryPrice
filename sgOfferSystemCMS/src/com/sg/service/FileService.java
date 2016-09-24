package com.sg.service;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {
	
	public int upLoadNotPads(MultipartFile file) throws IOException;
	public int upLoadCpu(XSSFSheet sheet) throws IOException;
	public int upLoadCasee(XSSFSheet sheet) throws IOException;
	public int upLoadDisk(XSSFSheet sheet) throws IOException;
	public int upLoadGraphics_card(XSSFSheet sheet) throws IOException;
	public int upLoadKey_mouse(XSSFSheet sheet) throws IOException;
	public int upLoadMemories_bar(XSSFSheet sheet) throws IOException;
	public int upLoadMonitor(XSSFSheet sheet) throws IOException;
	public int upLoadMother_board(XSSFSheet sheet) throws IOException;
	public int upLoadPower(XSSFSheet sheet) throws IOException;
	public int upLoadRadiator(XSSFSheet sheet) throws IOException;
	public int upLoadU_disk(XSSFSheet sheet) throws IOException;
}
