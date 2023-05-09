package enquirysystemwebapp.dao_impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import enquirysystemwebapp.dao.EnquiryDAO;
import enquirysystemwebapp.helper.ConnectionProvider;
import enquirysystemwebapp.models.Feedback;
import enquirysystemwebapp.models.Query;





public class EnquiryDAOImpl implements EnquiryDAO{
	@Autowired
	private Query query;
	private static Connection con;
	
	
	
	static
	{
		con=ConnectionProvider.getConnection();
	}
	
	
	
	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}
	
	

	

	public boolean addQuery(Query query) {
		try {
			PreparedStatement pst=con.prepareStatement("insert into queries (name,parent_name,email,mobile,query) values (?,?,?,?,?)");
			pst.setString(1, query.getCandidateName());
			pst.setString(2, query.getParentName());
			pst.setString(3, query.getEmail());
			pst.setString(4, query.getMobile());
			pst.setString(5, query.getQuery());
			int affectedRows=pst.executeUpdate();
			if(affectedRows>0) {
				return true;
			}
		}
		catch(Exception ee) {
			ee.printStackTrace();
			
		}
		return false;
	}

	public boolean deleteQuery(int id) {
		// TODO Auto-generated method stub
		try {
			String query="DELETE from queries where id=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}

	public Query getQueryById(int id) {
		// TODO Auto-generated method stub
		try {
			String run_query="select * from queries where id=?";
			PreparedStatement pst=con.prepareStatement(run_query);
			pst.setInt(1, id);
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				Query query=new Query();
				query.setCandidateName(rs.getString(1));
				query.setParentName(rs.getString(2));
				query.setEmail(rs.getString(3));
				query.setMobile(rs.getString(4));
				query.setQuery(rs.getString(5));
				query.setId(rs.getInt(6));
				query.setEnquiry_date(rs.getString(7));
				query.setFollow_date(rs.getString(8));
				return query;
			}
		}
		catch(Exception ee) {
			ee.printStackTrace();
			return null;
		}
		return null;
	}

	public List<Query> getAllQueries() {
		// TODO Auto-generated method stub
		List<Query> queries=new ArrayList<Query>();
		try {
			String run_query="select * from queries";
			PreparedStatement pst=con.prepareStatement(run_query);
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				Query query=new Query();
				query.setCandidateName(rs.getString(1));
				query.setParentName(rs.getString(2));
				query.setEmail(rs.getString(3));
				query.setMobile(rs.getString(4));
				query.setQuery(rs.getString(5));
				query.setId(rs.getInt(6));
				query.setEnquiry_date(rs.getString(7));
				query.setFollow_date(rs.getString(8));
				queries.add(query);	
			}
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
		return queries;
	}

	public List<Query> getAllQueriesForToday() {
		// TODO Auto-generated method stub
		List<Query> queries=new ArrayList<Query>();
		try {
			String run_query="select * from queries where follow_date=curdate()";
			PreparedStatement pst=con.prepareStatement(run_query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				Query query=new Query();
				query.setCandidateName(rs.getString(1));
				query.setParentName(rs.getString(2));
				query.setEmail(rs.getString(3));
				query.setMobile(rs.getString(4));
				query.setQuery(rs.getString(5));
				query.setId(rs.getInt(6));
				query.setEnquiry_date(rs.getString(7));
				query.setFollow_date(rs.getString(8));
				queries.add(query);		
			}
			
			if(queries.size()==0) {
				return null;
			}
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
		return queries;
	}

	public boolean updateFollowDate(int id, String newDate) {
		// TODO Auto-generated method stub
		try {
			String query="update queries set follow_date=? where id=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, newDate);
			pst.setInt(2, id);
			pst.execute();
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	
	public List<Feedback> getFeedbackHistoryForQuery(int id) {
		// TODO Auto-generated method stub
		List<Feedback> feedbackHistory=new ArrayList<Feedback>();
		try {
			String query="select feedback,timestamp from feedback_summary where query_id=? order by id desc";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, id);
			
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Feedback feedback=new Feedback();
				feedback.setFeedback(rs.getString(1));
				feedback.setTimestamp(rs.getString(2));
				feedbackHistory.add(feedback);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return feedbackHistory;
	}
	
	public boolean addFeedbackForQuery(Feedback feedback) {
		// TODO Auto-generated method stub
		try {
			String query="insert into feedback_summary(query_id,feedback) values(?,?)";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1,feedback.getEnquiryId());
			pst.setString(2, feedback.getFeedback());
			int affectedRows=pst.executeUpdate();
			
			if(affectedRows>0) {
				return true;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return false;
		
	}

}
