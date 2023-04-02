package enquirysystemwebapp.models;

public class Transaction {
	private int transactionId;
	private int studentId;
	private int courseId;
	private String chequeNo;
	private String bankName;
	private String transactionDate;
	
	
	public Transaction() {
		super();
	}

	public Transaction(int studentId, int courseId) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
	}
	
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	@Override
	public String toString() {
		return "Transaction [studentId=" + studentId + ", courseId=" + courseId + ", chequeNo=" + chequeNo
				+ ", bankName=" + bankName + ", transactionDate=" + transactionDate + "]";
	}
	
	
}
