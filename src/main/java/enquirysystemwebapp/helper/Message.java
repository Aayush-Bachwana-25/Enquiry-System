package enquirysystemwebapp.helper;

public class Message {
	private String messageContent;
	private String type;
	
	
	
	
	public Message(String messageContent, String type) {
		super();
		this.messageContent = messageContent;
		this.type = type;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Message [message=" + messageContent + ", type=" + type + "]";
	}
	
	
}
