package Com.selenium.project.Generic.DataBase_Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;

public class Data_Base_Utility 
{
	Connection con ;
public void getDbConnection(String url,String username , String password) throws SQLException
{
	try 
	{
	Driver driver = new Driver();
	DriverManager .registerDriver(driver);
	con=DriverManager.getConnection(url, username , password);
	}
	catch (Exception e) 
	{
		
	}
}

public void getDbConnection() throws SQLException
{
try 
{
Driver driver = new Driver();
DriverManager .registerDriver(driver);
con=DriverManager.getConnection("jdbc:mysql://49.249.28.218:3307/projects","root","root@%");
}
catch (Exception e) 
{
	
}
}

public void closeDbConnection() throws SQLException
	{
	try
	{
	con.close();	
	}
	catch(Exception e)
	{
		
	}
	}
	public ResultSet ExecuteSelectQuerry(String query) throws SQLException
	{
		ResultSet result=null;
		try 
		{
			Statement stat=con.createStatement();
			 result= stat.executeQuery(query);
			return result;
		}
		catch (Exception e) 
		{
			
		}
		return result;
	}
	public int ExecuteNonSelectQuerry(String query)
	{
		int result=0;
		try 
		{
			Statement stat=con.createStatement();
			 result =stat.executeUpdate(query);
			return result;
		}
		catch (Exception e) 
		{
			
		}
		return result;
	}
	}

