package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelReadAndWrite extends base{
	
	static Workbook workbook;
	static FileInputStream fis;
	
	public static void readExcel(String filePath) {
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+filePath);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.getMessage();
			File file = new File(System.getProperty("user.dir")+filePath);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static String getDataFromEcxel(String filePath,int row, int coloumn) {
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+filePath);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		workbook.getSheetAt(0).getRow(row).getCell(coloumn).setCellType(CellType.STRING);
		return workbook.getSheetAt(0).getRow(row).getCell(coloumn).getStringCellValue();

	}
	
	public static String[] getDataFromEcxel(String filePath,int[] row, int[] coloumn) {
		String [] values = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+filePath);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i<row.length; i++) {
			workbook.getSheetAt(0).getRow(row[i]).getCell(coloumn[i]).setCellType(CellType.STRING);
			values[i] = workbook.getSheetAt(0).getRow(row[i]).getCell(coloumn[i]).getStringCellValue();
		}
		
		return values;
	}

	public static void setDataToExcel(String filePath, int row, int coloumn, String value) {
		
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+filePath);
			workbook = new XSSFWorkbook(fis);
			workbook.getSheetAt(0).getRow(row).createCell(coloumn).setCellValue(value);
			FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")+filePath);
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int lastRow() {
		return workbook.getSheetAt(0).getLastRowNum();
	}
}
