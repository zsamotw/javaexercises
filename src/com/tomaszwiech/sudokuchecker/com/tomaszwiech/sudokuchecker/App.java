package com.tomaszwiech.sudokuchecker;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class App {
	Workbook wb = new XSSFWorkbook();
	FileOutputStream fileOut;

	void fromWWW() {
		try {
			fileOut = new FileOutputStream("wokkbook.xlsx");
			wb.write(fileOut);
			fileOut.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	void firstWorkBook() {
		try {
			fileOut = new FileOutputStream("Pierwszy.xlsx");
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(1);
			for(int i = 0; i <= 10; i++) {
				row.createCell(i).setCellValue(i);
			}
			
			wb.write(fileOut);
			fileOut.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.firstWorkBook();
	}
}
