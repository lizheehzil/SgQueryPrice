package com.sg.service.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
import com.sg.model.*;
import com.sg.mybatisdao.*;
import com.sg.service.FileService;

@Service("fileService")
public class FileServiceImpl implements FileService {

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
	public int upLoadNotPads(MultipartFile file) throws IOException {
		InputStream is = file.getInputStream();
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int category_id=utilDao.getCategoryId(sheet.getSheetName());
		Notpads notpads = null;
		List<Notpads> list = new ArrayList<Notpads>();
		String cellStr = null;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			notpads = new Notpads();
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					cellStr = "";
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					cellStr = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					cellStr = cell.getNumericCellValue() + "";
				} else {
					cellStr = cell.getStringCellValue();
				}

				if (j == 0) {
					utilDao.checkBrand(cellStr, category_id);
					notpads.setBrandId(utilDao.getBrandId(cellStr,category_id));
				} else if (j == 1) {
					notpads.setPno(cellStr);
				} else if (j == 2) {
					notpads.setModel(cellStr);
				} else if (j == 3) {
					notpads.setConfiguration_code(cellStr);
				} else if (j == 4) {
					notpads.setCPU(cellStr);
				} else if (j == 5) {
					notpads.setGraphics_card(cellStr);
				} else if (j == 6) {
					notpads.setMemories(cellStr);
				} else if (j == 7) {
					notpads.setScreen_size(cellStr);
				} else if (j == 8) {
					notpads.setDisk(cellStr);
				} else if (j == 9) {
					notpads.setKeypad_backlight(cellStr);
				} else if (j == 10) {
					notpads.setCd_drive(cellStr);
				} else if (j == 11) {
					try {
						notpads.setM_2(new Double(cellStr).intValue());
					} catch (NumberFormatException e) {
						notpads.setM_2(0);
					}
				} else if (j == 12) {
					try {
						notpads.setMSATA(new Double(cellStr).intValue());
					} catch (NumberFormatException e) {
						notpads.setMSATA(0);
					}
				} else if (j == 13) {
					notpads.setStandard_price(utilDao.getDouble(cellStr));
				} else if (j == 14) {
					notpads.setUnstandard_price(utilDao.getDouble(cellStr));
				} else if (j == 15) {
					notpads.setMini_price(utilDao.getDouble(cellStr));
				} else if (j == 16) {
					notpads.setGuide_price(utilDao.getDouble(cellStr));
				} else if (j == 17) {
					notpads.setJd_price(utilDao.getDouble(cellStr));
				} else if (j == 18) {
					notpads.setRemark(cellStr);
				}
			}
			notpads.setCategory_id(category_id);
			notpads.setCreate_time(utilDao.getCreatTime());
			notpads.setUpdate_time(utilDao.getCreatTime());
			notpads.setIs_display(1);
			notpads.setId(utilDao.setRandomId());
			list.add(notpads);
		}
		int a = notpadsDao.importNotspads(list);
		return a;
	}

	@Override
	public int upLoadCpu(XSSFSheet sheet) throws IOException {
		CPU cpu = null;
		List<CPU> list = new ArrayList<CPU>();
		int category_id=utilDao.getCategoryId(sheet.getSheetName());
		String cellStr = null;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			cpu = new CPU();
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					cellStr = "";
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					cellStr = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					cellStr = cell.getNumericCellValue() + "";
				} else {
					cellStr = cell.getStringCellValue();
				}

				if (j == 0) {
					cpu.setPno(cellStr);
				} else if (j == 1) {
					utilDao.checkBrand(cellStr, category_id);
					cpu.setBrandId(utilDao.getBrandId(cellStr,category_id));
				} else if (j == 2) {
					cpu.setModel(cellStr);
				} else if (j == 3) {
					cpu.setMatch_board(cellStr);
				} else if (j == 4) {
					cpu.setCost_price(utilDao.getDouble(cellStr));
				} else if (j == 5) {
					cpu.setMini_price(utilDao.getDouble(cellStr));
				} else if (j == 6) {
					cpu.setInner_price(utilDao.getDouble(cellStr));
				} else if (j == 7) {
					cpu.setMedia_price(utilDao.getDouble(cellStr));
				} else if (j == 8) {
					cpu.setJd_price(utilDao.getDouble(cellStr));
				} else if (j == 9) {
					cpu.setRemark(cellStr);
				}
			}
			cpu.setCategory_id(category_id);
			cpu.setCreate_time(utilDao.getCreatTime());
			cpu.setUpdate_time(utilDao.getCreatTime());
			cpu.setIs_display(1);
			cpu.setId(utilDao.setRandomId());
			list.add(cpu);
		}

		int a = cpuDao.importCPU(list);
		return a;
	}

	@Override
	public int upLoadCasee(XSSFSheet sheet) throws IOException {
		Casee casee = null;
		int category_id=utilDao.getCategoryId(sheet.getSheetName());
		List<Casee> list = new ArrayList<Casee>();
		String cellStr = null;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			casee = new Casee();
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					cellStr = "";
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					cellStr = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					cellStr = cell.getNumericCellValue() + "";
				} else {
					cellStr = cell.getStringCellValue();
				}

				if (j == 0) {
					casee.setPno(cellStr);
				} else if (j == 1) {
					utilDao.checkBrand(cellStr, category_id);
					casee.setBrandId(utilDao.getBrandId(cellStr,category_id));
				}else if (j == 2) {
					casee.setModel(cellStr);
				} else if (j == 3) {
					casee.setCost_price(utilDao.getDouble(cellStr));
				} else if (j == 4) {
					casee.setMini_price(utilDao.getDouble(cellStr));
				} else if (j == 5) {
					casee.setInner_price(utilDao.getDouble(cellStr));
				} else if (j == 6) {
					casee.setMedia_price(utilDao.getDouble(cellStr));
				} else if (j == 7) {
					casee.setJd_price(utilDao.getDouble(cellStr));
				} else if (j == 8) {
					casee.setRemark(cellStr);
				}
			}
			casee.setCategory_id(category_id);
			casee.setCreate_time(utilDao.getCreatTime());
			casee.setUpdate_time(utilDao.getCreatTime());
			casee.setIs_display(1);
			casee.setId(utilDao.setRandomId());
			list.add(casee);
		}

		int a = caseeDao.importCasee(list);
		return a;
	}

	@Override
	public int upLoadDisk(XSSFSheet sheet) throws IOException {
		Disk disk = null;
		List<Disk> list = new ArrayList<Disk>();
		int category_id=utilDao.getCategoryId(sheet.getSheetName());
		String cellStr = null;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			disk = new Disk();
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					cellStr = "";
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					cellStr = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					cellStr = cell.getNumericCellValue() + "";
				} else {
					cellStr = cell.getStringCellValue();
				}

				if (j == 0) {
					disk.setPno(cellStr);
				} else if (j == 1) {
					utilDao.checkBrand(cellStr, category_id);
					disk.setBrandId(utilDao.getBrandId(cellStr,category_id));
				} else if (j == 2) {
					disk.setModel(cellStr);
				} else if (j == 3) {
					disk.setCapacity(utilDao.getDouble(cellStr));
				} else if (j == 4) {
					disk.setInterface_type_id(cellStr);
				} else if (j == 5) {
					disk.setCost_price(utilDao.getDouble(cellStr));
				} else if (j == 6) {
					disk.setMini_price(utilDao.getDouble(cellStr));
				} else if (j == 7) {
					disk.setInner_price(utilDao.getDouble(cellStr));
				} else if (j == 8) {
					disk.setMedia_price(utilDao.getDouble(cellStr));
				} else if (j == 9) {
					disk.setJd_price(utilDao.getDouble(cellStr));
				} else if (j == 10) {
					disk.setRemark(cellStr);
				}
			}
			disk.setCategory_id(category_id);
			disk.setCreate_time(utilDao.getCreatTime());
			disk.setUpdate_time(utilDao.getCreatTime());
			disk.setIs_display(1);
			disk.setId(utilDao.setRandomId());
			list.add(disk);
		}

		int a = diskDao.importDisk(list);
		return a;
	}

	@Override
	public int upLoadGraphics_card(XSSFSheet sheet) throws IOException {
		Graphics_card graphics_card = null;
		List<Graphics_card> list = new ArrayList<Graphics_card>();
		String cellStr = null;
		int category_id=utilDao.getCategoryId(sheet.getSheetName());
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			graphics_card = new Graphics_card();
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					cellStr = "";
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					cellStr = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					cellStr = cell.getNumericCellValue() + "";
				} else {
					cellStr = cell.getStringCellValue();
				}

				if (j == 0) {
					graphics_card.setPno(cellStr);
				} else if (j == 1) {
					utilDao.checkBrand(cellStr, category_id);
					graphics_card.setBrandId(utilDao.getBrandId(cellStr,category_id));
				} else if (j == 2) {
					graphics_card.setModel(cellStr);
				} else if (j == 3) {
					graphics_card.setCapacity(utilDao.getDouble(cellStr));
				} else if (j == 4) {
					graphics_card.setDisplay_core(cellStr);
				} else if (j == 5) {
					graphics_card.setCost_price(utilDao.getDouble(cellStr));
				} else if (j == 6) {
					graphics_card.setMini_price(utilDao.getDouble(cellStr));
				} else if (j == 7) {
					graphics_card.setInner_price(utilDao.getDouble(cellStr));
				} else if (j == 8) {
					graphics_card.setMedia_price(utilDao.getDouble(cellStr));
				} else if (j == 9) {
					graphics_card.setJd_price(utilDao.getDouble(cellStr));
				} else if (j == 10) {
					graphics_card.setOther_spec(cellStr);
				}
			}
			graphics_card.setCategory_id(category_id);
			
			graphics_card.setCreate_time(utilDao.getCreatTime());
			graphics_card.setUpdate_time(utilDao.getCreatTime());
			graphics_card.setIs_display(1);
			graphics_card.setId(utilDao.setRandomId());
			list.add(graphics_card);
		}

		int a = graphics_cardDao.importGraphics_card(list);
		return a;
	}

	@Override
	public int upLoadKey_mouse(XSSFSheet sheet) throws IOException {
		Key_mouse key_mouse = null;
		List<Key_mouse> list = new ArrayList<Key_mouse>();
		String cellStr = null;
		int category_id=utilDao.getCategoryId(sheet.getSheetName());

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			key_mouse = new Key_mouse();
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					cellStr = "";
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					cellStr = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					cellStr = cell.getNumericCellValue() + "";
				} else {
					cellStr = cell.getStringCellValue();
				}

				if (j == 0) {
					key_mouse.setPno(cellStr);
				} else if (j == 1) {
					utilDao.checkBrand(cellStr, category_id);
					key_mouse.setBrandId(utilDao.getBrandId(cellStr,category_id));
				} else if (j == 2) {
					key_mouse.setTypeId(utilDao.getCategoryId(cellStr));
				} else if (j == 3) {
					key_mouse.setModel(cellStr);
				} else if (j == 4) {
					key_mouse.setCost_price(utilDao.getDouble(cellStr));
				} else if (j == 5) {
					key_mouse.setMini_price(utilDao.getDouble(cellStr));
				} else if (j == 6) {
					key_mouse.setInner_price(utilDao.getDouble(cellStr));
				} else if (j == 7) {
					key_mouse.setMedia_price(utilDao.getDouble(cellStr));
				} else if (j == 8) {
					key_mouse.setJd_price(utilDao.getDouble(cellStr));
				} else if (j == 9) {
					key_mouse.setRemark(cellStr);
				}
			}
			key_mouse.setCategory_id(category_id);
			key_mouse.setCreate_time(utilDao.getCreatTime());
			key_mouse.setUpdate_time(utilDao.getCreatTime());
			key_mouse.setIs_display(1);
			key_mouse.setId(utilDao.setRandomId());
			list.add(key_mouse);
		}

		int a = key_mouseDao.importKey_mouse(list);
		return a;
	}

	@Override
	public int upLoadMemories_bar(XSSFSheet sheet) throws IOException {
		Memories_bar memories_bar = null;
		int category_id=utilDao.getCategoryId(sheet.getSheetName());
		List<Memories_bar> list = new ArrayList<Memories_bar>();
		String cellStr = null;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			memories_bar = new Memories_bar();
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					cellStr = "";
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					cellStr = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					cellStr = cell.getNumericCellValue() + "";
				} else {
					cellStr = cell.getStringCellValue();
				}

				if (j == 0) {
					memories_bar.setPno(cellStr);
				} else if (j == 1) {
					utilDao.checkBrand(cellStr, category_id);
					memories_bar.setBrandId(utilDao.getBrandId(cellStr,category_id));
				} else if (j == 2) {
					memories_bar.setModel(cellStr);
				} else if (j == 3) {
					memories_bar.setCapacity(utilDao.getDouble(cellStr));
				} else if (j == 4) {
					memories_bar.setCost_price(utilDao.getDouble(cellStr));
				} else if (j == 5) {
					memories_bar.setMini_price(utilDao.getDouble(cellStr));
				} else if (j == 6) {
					memories_bar.setInner_price(utilDao.getDouble(cellStr));
				} else if (j == 7) {
					memories_bar.setMedia_price(utilDao.getDouble(cellStr));
				} else if (j == 8) {
					memories_bar.setJd_price(utilDao.getDouble(cellStr));
				} else if (j == 9) {
					memories_bar.setRemark(cellStr);
				}
			}
			memories_bar.setCategory_id(category_id);
			memories_bar.setCreate_time(utilDao.getCreatTime());
			memories_bar.setUpdate_time(utilDao.getCreatTime());
			memories_bar.setIs_display(1);
			memories_bar.setId(utilDao.setRandomId());
			list.add(memories_bar);
		}

		int a = memories_barDao.importMemories_bar(list);
		return a;
	}

	@Override
	public int upLoadMonitor(XSSFSheet sheet) throws IOException {
		Monitor monitor = null;
		int category_id=utilDao.getCategoryId(sheet.getSheetName());
		List<Monitor> list = new ArrayList<Monitor>();
		String cellStr = null;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			monitor = new Monitor();
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					cellStr = "";
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					cellStr = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					cellStr = cell.getNumericCellValue() + "";
				} else {
					cellStr = cell.getStringCellValue();
				}

				if (j == 0) {
					monitor.setPno(cellStr);
				} else if (j == 1) {
					utilDao.checkBrand(cellStr, category_id);
					monitor.setBrandId(utilDao.getBrandId(cellStr,category_id));
				} else if (j == 2) {
					monitor.setModel(cellStr);
				} else if (j == 3) {
					monitor.setSize(cellStr);
				} else if (j == 4) {
					monitor.setPanel(cellStr);
				} else if (j == 5) {
					monitor.setCost_price(utilDao.getDouble(cellStr));
				} else if (j == 6) {
					monitor.setMini_price(utilDao.getDouble(cellStr));
				} else if (j == 7) {
					monitor.setInner_price(utilDao.getDouble(cellStr));
				} else if (j == 8) {
					monitor.setMedia_price(utilDao.getDouble(cellStr));
				} else if (j == 9) {
					monitor.setJd_price(utilDao.getDouble(cellStr));
				} else if (j == 10) {
					monitor.setRemark(cellStr);
				}
			}
			monitor.setCategory_id(category_id);
			monitor.setCreate_time(utilDao.getCreatTime());
			monitor.setUpdate_time(utilDao.getCreatTime());
			monitor.setIs_display(1);
			monitor.setId(utilDao.setRandomId());
			list.add(monitor);
		}

		int a = monitorDao.importMonitor(list);
		return a;
	}

	@Override
	public int upLoadMother_board(XSSFSheet sheet) throws IOException {
		Mother_board mother_board = null;
		List<Mother_board> list = new ArrayList<Mother_board>();
		String cellStr = null;
		int category_id=utilDao.getCategoryId(sheet.getSheetName());
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			mother_board = new Mother_board();
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					cellStr = "";
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					cellStr = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					cellStr = cell.getNumericCellValue() + "";
				} else {
					cellStr = cell.getStringCellValue();
				}

				if (j == 0) {
					mother_board.setPno(cellStr);
				} else if (j == 1) {
					utilDao.checkBrand(cellStr, category_id);
					mother_board.setBrandId(utilDao.getBrandId(cellStr,category_id));
				} else if (j == 2) {
					mother_board.setModel(cellStr);
				} else if (j == 3) {
					mother_board.setHardware_spec(cellStr);
				} else if (j == 4) {
					mother_board.setCost_price(utilDao.getDouble(cellStr));
				} else if (j == 5) {
					mother_board.setMini_price(utilDao.getDouble(cellStr));
				} else if (j == 6) {
					mother_board.setInner_price(utilDao.getDouble(cellStr));
				} else if (j == 7) {
					mother_board.setMedia_price(utilDao.getDouble(cellStr));
				} else if (j == 8) {
					mother_board.setJd_price(utilDao.getDouble(cellStr));
				} else if (j == 9) {
					mother_board.setRemark(cellStr);
				}
			}
			mother_board.setCategory_id(category_id);
			mother_board.setCreate_time(utilDao.getCreatTime());
			mother_board.setUpdate_time(utilDao.getCreatTime());
			mother_board.setIs_display(1);
			mother_board.setId(utilDao.setRandomId());
			list.add(mother_board);
		}

		int a = mother_boardDao.importMother_board(list);
		return a;
	}

	@Override
	public int upLoadPower(XSSFSheet sheet) throws IOException {
		Power power = null;
		List<Power> list = new ArrayList<Power>();
		String cellStr = null;
		int category_id=utilDao.getCategoryId(sheet.getSheetName());
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			power = new Power();
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					cellStr = "";
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					cellStr = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					cellStr = cell.getNumericCellValue() + "";
				} else {
					cellStr = cell.getStringCellValue();
				}

				if (j == 0) {
					power.setPno(cellStr);
				} else if (j == 1) {
					utilDao.checkBrand(cellStr, category_id);
					power.setBrandId(utilDao.getBrandId(cellStr,category_id));
				} else if (j == 2) {
					power.setModel(cellStr);
				} else if (j == 3) {
					power.setPower(utilDao.getDouble(cellStr));
				} else if (j == 4) {
					power.setCost_price(utilDao.getDouble(cellStr));
				} else if (j == 5) {
					power.setMini_price(utilDao.getDouble(cellStr));
				} else if (j == 6) {
					power.setInner_price(utilDao.getDouble(cellStr));
				} else if (j == 7) {
					power.setMedia_price(utilDao.getDouble(cellStr));
				} else if (j == 8) {
					power.setJd_price(utilDao.getDouble(cellStr));
				} else if (j == 9) {
					power.setRemark(cellStr);
				}
			}
			power.setCategory_id(category_id);
			power.setCreate_time(utilDao.getCreatTime());
			power.setUpdate_time(utilDao.getCreatTime());
			power.setIs_display(1);
			power.setId(utilDao.setRandomId());
			list.add(power);
		}

		int a = powerDao.importPower(list);
		return a;
	}

	@Override
	public int upLoadRadiator(XSSFSheet sheet) throws IOException {
		Radiator radiator = null;
		List<Radiator> list = new ArrayList<Radiator>();
		String cellStr = null;
		int category_id=utilDao.getCategoryId(sheet.getSheetName());
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			radiator = new Radiator();
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					cellStr = "";
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					cellStr = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					cellStr = cell.getNumericCellValue() + "";
				} else {
					cellStr = cell.getStringCellValue();
				}

				if (j == 0) {
					radiator.setPno(cellStr);
				} else if (j == 1) {
					utilDao.checkBrand(cellStr, category_id);
					radiator.setBrandId(utilDao.getBrandId(cellStr,category_id));
				} else if (j == 2) {
					radiator.setModel(cellStr);
				} else if (j == 3) {
					radiator.setTemplate(cellStr);
				} else if (j == 4) {
					radiator.setCost_price(utilDao.getDouble(cellStr));
				} else if (j == 5) {
					radiator.setMini_price(utilDao.getDouble(cellStr));
				} else if (j == 6) {
					radiator.setInner_price(utilDao.getDouble(cellStr));
				} else if (j == 7) {
					radiator.setMedia_price(utilDao.getDouble(cellStr));
				} else if (j == 8) {
					radiator.setJd_price(utilDao.getDouble(cellStr));
				} else if (j == 9) {
					radiator.setRemark(cellStr);
				}
			}
			radiator.setCategory_id(category_id);
			radiator.setCreate_time(utilDao.getCreatTime());
			radiator.setUpdate_time(utilDao.getCreatTime());
			radiator.setIs_display(1);
			radiator.setId(utilDao.setRandomId());
			list.add(radiator);
		}

		int a = radiatorDao.importRadiator(list);
		return a;
	}

	@Override
	public int upLoadU_disk(XSSFSheet sheet) throws IOException {
		U_disk u_disk = null;
		int category_id=utilDao.getCategoryId(sheet.getSheetName());
		List<U_disk> list = new ArrayList<U_disk>();
		String cellStr = null;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			u_disk = new U_disk();
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					cellStr = "";
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
					cellStr = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					cellStr = cell.getNumericCellValue() + "";
				} else {
					cellStr = cell.getStringCellValue();
				}

				if (j == 0) {
					u_disk.setPno(cellStr);
				} else if (j == 1) {
					utilDao.checkBrand(cellStr, category_id);
					u_disk.setBrandId(utilDao.getBrandId(cellStr,category_id));
				} else if (j == 2) {
					u_disk.setModel(cellStr);
				} else if (j == 3) {
					u_disk.setCapacity(utilDao.getDouble(cellStr));
				} else if (j == 4) {
					u_disk.setCost_price(utilDao.getDouble(cellStr));
				} else if (j == 5) {
					u_disk.setMini_price(utilDao.getDouble(cellStr));
				} else if (j == 6) {
					u_disk.setInner_price(utilDao.getDouble(cellStr));
				} else if (j == 7) {
					u_disk.setMedia_price(utilDao.getDouble(cellStr));
				} else if (j == 8) {
					u_disk.setJd_price(utilDao.getDouble(cellStr));
				} else if (j == 9) {
					u_disk.setRemark(cellStr);
				}
			}
			u_disk.setCategory_id(category_id);
			u_disk.setCreate_time(utilDao.getCreatTime());
			u_disk.setUpdate_time(utilDao.getCreatTime());
			u_disk.setIs_display(1);
			u_disk.setId(utilDao.setRandomId());
			list.add(u_disk);
		}

		int a = u_diskDao.importU_disk(list);
		return a;
	}

}
