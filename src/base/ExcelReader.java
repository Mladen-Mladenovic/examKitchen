package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	/*
	 * Ovde sam izvukao samo nekoliko funkcija Apache POI-a, da bih olakšao sebi rad sa ekselom.
	 */
	
	File file;
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell; // column

	public ExcelReader(String filePath) throws IOException {
		this.file = new File(filePath);
		this.fis = new FileInputStream(file);
		this.wb = new XSSFWorkbook(fis);
	}
	
	public XSSFSheet getSheet() {
		return sheet;
	}
	
	public String getStringData(String sheetName, int rowNum, int colNum) {
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		return cell.getStringCellValue();
	}
	
	public double getDoubleData(String sheetName, int rowNum, int colNum) {
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		return cell.getNumericCellValue();
	}
	
	public long getIntData(String sheetName, int rowNum, int colNum) {
		return (long) getDoubleData(sheetName, rowNum, colNum);
		
	}
	
	

}
