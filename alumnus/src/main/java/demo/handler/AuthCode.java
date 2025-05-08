package demo.handler;

/**
 *	权限分类
 */
public enum AuthCode {
	MANAGER(1000,"login");
	private int id;
	private String url;

	private AuthCode(int id, String url) {
		this.id = id;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
