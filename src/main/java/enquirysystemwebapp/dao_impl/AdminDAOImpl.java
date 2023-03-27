package enquirysystemwebapp.dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import enquirysystemwebapp.dao.AdminDAO;
import enquirysystemwebapp.helper.ConnectionProvider;
import enquirysystemwebapp.models.Admin;


public class AdminDAOImpl implements AdminDAO{
	
	@Autowired
	private Admin admin;
	private static Connection con;
	static
	{
		con=ConnectionProvider.getConnection();
	}
	
	public AdminDAOImpl() {}
	public AdminDAOImpl(Admin admin) {
		super();
		this.admin = admin;
	}

	public boolean login(){
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst=con.prepareStatement("select * from admin where username=? and password=?");
			pst.setString(1, admin.getUsername());
			pst.setString(2, admin.getPassword());
			ResultSet rs=pst.executeQuery();
			
			if(rs.next()==false) {
				return false;
			}
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
		return true;
		
	}

}
