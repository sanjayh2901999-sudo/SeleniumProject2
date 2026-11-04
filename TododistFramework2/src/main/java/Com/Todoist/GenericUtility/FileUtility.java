package Com.Todoist.GenericUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility 
{
	/**
	 * This method is used to read data from the property file 
	 * @author SANJAY
	 * @param key
	 * @return
	 * @throws Exception
	 */
public String readDataFromPropertyFile(String key) throws Exception
{
	FileInputStream fis = new FileInputStream("./Environment_Configuration_Data/Todosit_CommonData.properties");
	Properties pobj = new Properties();
	pobj.load(fis);
	String value = pobj.getProperty(key);
	return value;
	
}
}
