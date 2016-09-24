package com.sg.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sg.service.FileService;

/**
 * 文件上传
 * 
 * @author Administrator
 *
 */
@Controller
public class FileController {

	@Autowired
	private FileService fileService;

	@RequestMapping("upload.do")
	public String testupload(@RequestParam(value = "desc") String desc,
			@RequestParam(value = "file") MultipartFile file)
			throws IOException {
		// System.out.println(file.getOriginalFilename());

		if (desc.equals("笔记本"))
			fileService.upLoadNotPads(file);
		if (desc.equals("装机配件")) {
			InputStream is = file.getInputStream();
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(is);

			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				XSSFSheet arg = workbook.getSheetAt(i);
				if (arg.getSheetName().equals("CPU")
						|| arg.getSheetName().equals("cpu")
						|| arg.getSheetName().equals("处理器")) {
					fileService.upLoadCpu(arg);
				}
				if (arg.getSheetName().equals("显卡")) {
					fileService.upLoadGraphics_card(arg);
				}
				if (arg.getSheetName().equals("主板")) {
					fileService.upLoadMother_board(arg);
				}
				if (arg.getSheetName().equals("硬盘")) {
					fileService.upLoadDisk(arg);
				}
				if (arg.getSheetName().equals("电源")) {
					fileService.upLoadPower(arg);
				}
				if (arg.getSheetName().equals("散热器")
						|| arg.getSheetName().equals("散热")) {
					fileService.upLoadRadiator(arg);
				}
				if (arg.getSheetName().equals("显示器")
						|| arg.getSheetName().equals("屏幕")) {
					fileService.upLoadMonitor(arg);
				}
				if (arg.getSheetName().equals("内存")
						|| arg.getSheetName().equals("内存条")) {
					fileService.upLoadMemories_bar(arg);
				}
				if (arg.getSheetName().equals("机箱")) {
					fileService.upLoadCasee(arg);
				}
				if (arg.getSheetName().equals("键鼠")
						|| arg.getSheetName().equals("键盘")
						|| arg.getSheetName().equals("鼠标")) {
					fileService.upLoadKey_mouse(arg);
				}
			}
		}
		if (desc.equals("配件")) {
			InputStream is = file.getInputStream();
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				XSSFSheet arg = workbook.getSheetAt(i);
				if (arg.getSheetName().equals("内存")
						|| arg.getSheetName().equals("内存条")) {
					fileService.upLoadMemories_bar(arg);
				}
				if (arg.getSheetName().equals("硬盘")) {
					fileService.upLoadDisk(arg);
				}
				if (arg.getSheetName().equals("u盘")
						|| arg.getSheetName().equals("U盘")) {
					fileService.upLoadU_disk(arg);
				}
			}
		}
		return "index";
	}
}
