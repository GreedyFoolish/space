package demo.bean;

import com.alibaba.excel.annotation.ExcelProperty;

public class ExcelUserImport {

	@ExcelProperty("学号")
	private String userId;
	
	@ExcelProperty("真实姓名")
	private String userRealname;
	
	@ExcelProperty("身份证号")
	private String userIdCard;
	
	@ExcelProperty("联系电话")
	private String userPhone;
	
	@ExcelProperty("联系邮箱")
	private String userMail;
	
	@ExcelProperty("错误信息")
	private String errorInfo;

	public String getUserId() {
		return userId == null? null: userId.trim();
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserRealname() {
		return userRealname == null? null: userRealname.trim();
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}

	public String getUserIdCard() {
		return userIdCard == null? null: userIdCard.trim();
	}

	public void setUserIdCard(String userIdCard) {
		this.userIdCard = userIdCard;
	}

	public String getUserPhone() {
		return userPhone == null? null: userPhone.trim();
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserMail() {
		return userMail == null? null: userMail.trim();
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getErrorInfo() {
		return errorInfo == null? null: errorInfo.trim();
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	@Override
	public String toString() {
		return "ExcelUserImport [userId=" + userId + ", userRealname=" + userRealname + ", userIdCard=" + userIdCard
				+ ", userPhone=" + userPhone + ", userMail=" + userMail + ", errorInfo=" + errorInfo + "]";
	}
	
}
