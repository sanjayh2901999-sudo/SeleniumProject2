package Com.Todoist.GenericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	/**
	 * This method is used to read & write data from excel file
	 * @author SANJAY
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @return
	 * @throws Exception
	 */
	public String readDataFromExcel(String sheetName,int rowNum,int cellNum) throws Exception
	{
	    FileInputStream fis =
	    new FileInputStream("./Test_Script_Data/Todoist_Test_Script_Data_.xlsx");

	    Workbook wb = WorkbookFactory.create(fis);

	    Sheet sheet = wb.getSheet(sheetName);

	    if(sheet == null) 
	    {
	        throw new RuntimeException("❌ Sheet NOT found: " + sheetName);
	    }

	    if(sheet.getRow(rowNum) == null)
	    {
	        throw new RuntimeException("❌ Row NOT found: " + rowNum);
	    }

	    if(sheet.getRow(rowNum).getCell(cellNum) == null)
	    {
	        throw new RuntimeException("❌ Cell NOT found at column: " + cellNum);
	    }

	    return sheet.getRow(rowNum).getCell(cellNum).toString();
	}

	
public void writeDataIntoExcel(String sheetName,int rowNo,int cellNo,String data) throws Exception
{
	FileInputStream fis=new FileInputStream("./Test_Script_Data/Todoist_Test_Script_Data_.xlsx");
	Workbook wb = WorkbookFactory.create(fis);
	Sheet sh = wb.getSheet(sheetName);
	Row row = sh.createRow(rowNo);
	Cell cell = row.createCell(cellNo);
	cell.setCellValue(data);
	
	FileOutputStream fos = new FileOutputStream("./Test_Script_Data/Todoist_Test_Script_Data_.xlsx");
	wb.write(fos);
	wb.close();
}
public int getTotalRowCount(String sheetName) throws Exception
{
	FileInputStream fis = new FileInputStream("./Test_Script_Data/Todoist_Test_Script_Data_.xlsx");
	Workbook wb = WorkbookFactory.create(fis);
	Sheet sh = wb.getSheet(sheetName);
	int row_count = sh.getLastRowNum();
	return row_count;
	
}
}

