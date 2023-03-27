package enquirysystemwebapp.dao;

import java.util.List;

import enquirysystemwebapp.models.Feedback;
import enquirysystemwebapp.models.Query;

public interface EnquiryDAO {
	public 	boolean addQuery(Query query);
	public boolean deleteQuery(int id);
	public Query getQueryById(int id);
	public List<Query> getAllQueries();
	public List<Query> getAllQueriesForToday();
	public boolean updateFollowDate(int id, String newDate);
	public List<Feedback> getFeedbackHistoryForQuery(int id);
	public boolean addFeedbackForQuery(Feedback feedback);
}
