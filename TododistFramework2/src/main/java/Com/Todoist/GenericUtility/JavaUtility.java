package Com.Todoist.GenericUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public class JavaUtility 
{
	
/**
 * These are the methods used to generate random number & capture System date & time
 * @author SANJAY
 * @param int days
 * @return
 */
public int getRandomNumber()
{
	Random random = new Random();
	int random_number = random.nextInt(1000);
	return random_number;
	
}
public String getSystemDateYYYYDDMM()
{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Calendar cal = Calendar.getInstance();
	String time = sdf.format(cal.getTime());
	return time;
}
public String getRequiredDateYYYYDDMM(int days)
{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DAY_OF_MONTH,days);
	String time = sdf.format(cal.getTime());
	return time;
	
}
}
