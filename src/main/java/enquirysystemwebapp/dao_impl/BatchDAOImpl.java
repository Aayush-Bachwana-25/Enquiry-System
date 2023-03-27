package enquirysystemwebapp.dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import enquirysystemwebapp.dao.BatchDAO;
import enquirysystemwebapp.helper.ConnectionProvider;
import enquirysystemwebapp.models.Batch;
import enquirysystemwebapp.models.Course;

public class BatchDAOImpl implements BatchDAO {
	
	private static Connection con;
	static
	{
		con=ConnectionProvider.getConnection();
	}
	

	public boolean addBatch(Batch batch) {
		// TODO Auto-generated method stub
		String query="insert into batches(batch_name,timings,course_id) values(?,?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, batch.getName());
			pst.setString(2, batch.getTimings());
			pst.setInt(3, batch.getCourseId());
			int affectedRows=pst.executeUpdate();
			
			if (affectedRows == 0) {
		        return false;
		    }	
			System.out.println("Batch inserted");
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		

	}

	public void updateBatch(Batch batch) {
		// TODO Auto-generated method stub

	}

	public boolean deleteBatch(int batchId) {
		// TODO Auto-generated method stub
		try {
			String query="DELETE from batches where batch_id=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, batchId);
			pst.execute();
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	public Batch getBatchById(int batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Batch> getBatchesByCourseId(int courseId) {
		// TODO Auto-generated method stub
		List<Batch> list=new ArrayList<Batch>();
		try {
			String query="select * from batches where course_id=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, courseId);
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				Batch batch=new Batch(courseId);
				batch.setId(rs.getInt(1));
				batch.setName(rs.getString(3));
				batch.setTimings(rs.getString(4));
				
				
				list.add(batch);	
			}
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
		return list;
	}

}
