package demo.bean;

import java.util.HashMap;
import java.util.Map;

public class MsgException extends Exception {
	private static final long serialVersionUID = -1127192699180442871L;
	
	private int code;
	private String title;
	private String msg;
	private Map<String,Object> extras;
	
	public MsgException() {
		this("");
	}
	
	public MsgException(String msg) {
		super(msg);
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getExtras() {
		if(extras == null) {
			extras = new HashMap<>();
		}
		return extras;
	}
	public void setExtras(Map<String, Object> extras) {
		this.extras = extras;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
