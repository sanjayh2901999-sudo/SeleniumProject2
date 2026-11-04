package Com.selenium.project.Generic.FileUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class File_Utility 
{
public String getDataFromPropertiesFile(String Key) throws Exception
{
	
	FileInputStream fis = new FileInputStream("./Config_App_Data/commondata.properties");
	Properties pobj = new Properties();
	pobj.load(fis);
	String data =pobj.getProperty(Key);
	
	
	return data;
	
}
}
