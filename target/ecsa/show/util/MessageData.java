package main.webapp.show.util;

public class MessageData {

	public String message;
	public String code;
	public String status;
	
	public MessageData(String code, String message, String status ){
		this.code = code;
		this.message = message;
		this.status = status;
	}
	
	public MessageData() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
