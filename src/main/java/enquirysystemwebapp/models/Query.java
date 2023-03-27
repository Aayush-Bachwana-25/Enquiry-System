package enquirysystemwebapp.models;

import org.springframework.stereotype.Component;

@Component
public class Query {
	private int id;
	private String parentName;
	private String candidateName;
	private String email;
	private String mobile;
	private String query;
	private String enquiry_date;
	private String follow_date;
	
	
	

	@Override
	public String toString() {
		return "Query [id=" + id + ", parentName=" + parentName + ", candidateName=" + candidateName + ", email="
				+ email + ", mobile=" + mobile + ", query=" + query + ", enquiry_date=" + enquiry_date
				+ ", follow_date=" + follow_date + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnquiry_date() {
		return enquiry_date;
	}

	public void setEnquiry_date(String enquiry_date) {
		this.enquiry_date = enquiry_date;
	}

	public String getFollow_date() {
		return follow_date;
	}

	public void setFollow_date(String follow_date) {
		this.follow_date = follow_date;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	
	
	
}
