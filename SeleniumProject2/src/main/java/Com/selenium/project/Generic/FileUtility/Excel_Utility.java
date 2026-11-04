package Com.selenium.project.Generic.FileUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_Utility
{
public String getDataFromExcel(String sheetnName, int rowNumber , int cellNumber) throws Exception
{
	FileInputStream fis = new FileInputStream("./Test_Data/Test_Script_Data_For_Vtiger.xlsx");
	Workbook wb = WorkbookFactory.create(fis);
	String data = wb.getSheet(sheetnName).getRow(rowNumber).getCell(cellNumber).getStringCellValue();
	wb.close();
    return data;
}
public int getRowCount(String sheetName) throws Exception, IOException
{
	FileInputStream fis = new FileInputStream("./Test_Data/Test_Script_Data_For_Vtiger.xlsx");
	Workbook wb = WorkbookFactory.create(fis);
	int rowCount = wb.getSheet(sheetName).getLastRowNum();
	wb.close();
    return rowCount;
}
public void setDataIntoExcel(String sheetName , int rowNum, int cellNumber,String data) throws Throwable
{
	FileInputStream fis = new FileInputStream("./Test_Data/Test_Script_Data_For_Vtiger.xlsx");
	Workbook wb = WorkbookFactory.create(fis);
	wb.getSheet(sheetName).getRow(rowNum).createCell(cellNumber);
	
	FileOutputStream fos= new FileOutputStream("./Test_Data/Test_Script_Data_For_Vtiger.xlsx");
	wb.write(fos);
	wb.close();
	
}

}
