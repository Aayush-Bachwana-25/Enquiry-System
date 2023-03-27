package enquirysystemwebapp.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class ConnectionProvider {
	static Connection con;
	static ResourceBundle rb;
	static
	{
		try {
			rb=ResourceBundle.getBundle("enquirysystemwebapp.helper.database");
			Class.forName(rb.getString("driver"));
			System.out.println("Driver Loaded");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection()
	{
		try
		{
			String url,user,password;
			
			url=rb.getString("url");
			user=rb.getString("user");
			password=rb.getString("password");
			con=DriverManager.getConnection(url,user,password);
			System.out.println("Connection eastablished");
		}
		catch(Exception ee)
		{
			System.out.println(ee);
		}
		return con;
	}
}
