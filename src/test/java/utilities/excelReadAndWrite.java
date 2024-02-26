package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelReadAndWrite {
	
	static Workbook workbook;
	static FileInputStream fis;
	
	public static void readExcel(String filePath) {
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+filePath);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getDataFromEcxel(String filePath,int row, int coloumn) {
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+filePath);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook.getSheetAt(0).getRow(row).getCell(coloumn).getStringCellValue();

	}

	public static void setDataToExcel(String filePath, int row, int coloumn, String value) {
		
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+filePath);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		workbook.getSheetAt(0).getRow(row).createCell(coloumn).setCellValue(value);
		FileOutputStream out;
		try {
			out = new FileOutputStream(System.getProperty("user.dir")+filePath);
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int lastRow() {
		return workbook.getSheetAt(0).getLastRowNum();
	}
}
