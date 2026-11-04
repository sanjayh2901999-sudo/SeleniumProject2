package Com.selenium.project.Generic.FileUtility;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Json_Utility 
{
public String getDataFromJsonFile(String key) throws Exception
{
	FileReader fileR = new FileReader("./Config_App_Data/App_Common_Data.json");
	JSONParser parser = new JSONParser();
	Object obje = parser.parse(fileR);
	// step2:Convert java object into JSONObject using downcasting
	JSONObject map = (JSONObject) obje;// downcasting
	// Step3:get the value from json file using key
	System.out.println(map.get("url"));
	String data = (String)map.get(key);
	return data;
	
}
}
