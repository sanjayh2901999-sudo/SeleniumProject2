package Com.selenium.project.Generic.Webdriver_Utility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class JavaUtility
{
public int getRandomNumber()
{
Random random	=new Random();
int ranDomNumber = random.nextInt(5000);
return ranDomNumber;
}
//public String getSystemDateYYYYDDMM()
//{
//	Date dateobj= new Date(0);
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	String date=sdf.format(dateobj);
//	return date;
//}
////public String getRequiredDateYYYYDDMM(int days)
//{
//	/*
//	// System.out.println(dateObj.toString());
//	// how to capture current system date 
//	SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
//	// how to capture date for next 30 days
//	Calendar cal = sim.getCalendar();
//	// cal.add(Calendar.DAY_OF_MONTH,-30);
//	cal.add(Calendar.DAY_OF_MONTH,days);
//	String reqDate =sim.format(cal.getTime());
//	return reqDate;*/
//	
//	
public String getSystemDateYYYYDDMM() 
{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    return sdf.format(cal.getTime());
}

public String getRequiredDateYYYYDDMM(int days) 
{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, days);
    return sdf.format(cal.getTime());
}	
	
	
}

