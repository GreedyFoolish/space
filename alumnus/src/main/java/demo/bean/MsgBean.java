package demo.bean;

/**
 * 统一的JSON返回对象
 */
public class MsgBean {
	private int code;
	private String title;
	private String msg;
	private Object extras;
	
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
	public Object getExtras() {
		return extras;
	}
	public void setExtras(Object extras) {
		this.extras = extras;
	}
	
	@Override
	public String toString() {
		return "MsgBean [code=" + code + ", title=" + title + ", msg=" + msg + ", extras=" + extras + "]";
	}
	
}
