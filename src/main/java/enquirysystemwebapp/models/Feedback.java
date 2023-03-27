package enquirysystemwebapp.models;

public class Feedback {
	int enquiryId;
	String feedback;
	String timestamp;
	
	
	
	
	public Feedback() {
		super();
	}
	public Feedback(int enquiryId, String feedback) {
		super();
		this.enquiryId = enquiryId;
		this.feedback = feedback;
	}
	public int getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Feedback [enquiryId=" + enquiryId + ", feedback=" + feedback + ", timestamp=" + timestamp + "]";
	}
	
	
	
	
}
