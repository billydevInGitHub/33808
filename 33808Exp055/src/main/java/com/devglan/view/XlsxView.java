package com.devglan.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.devglan.model.UserDetails;

public class XlsxView extends AbstractXlsxView {

	@Override
	public void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"users.xlsx\"");

		@SuppressWarnings("unchecked")
		List<UserDetails> users = (List<UserDetails>) model.get("users");

		Sheet sheet = workbook.createSheet("Users Xlsx");

		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("First Name");
		header.createCell(1).setCellValue("Last Name");
		header.createCell(2).setCellValue("Email");

		int rowCount = 1;
		for (UserDetails user : users) {
			Row fruitRow = sheet.createRow(rowCount++);
			fruitRow.createCell(0).setCellValue(user.getFirstName());
			fruitRow.createCell(1).setCellValue(user.getLastName());
			fruitRow.createCell(2).setCellValue(user.getEmail());
		}

	}
}