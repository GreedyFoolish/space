package demo.dao.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BaseModel {
	
	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date addtime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updatetime;
	private Integer deltime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getDeltime() {
		return deltime;
	}
	public void setDeltime(Integer deltime) {
		this.deltime = deltime;
	}
	@Override
	public String toString() {
		return "BaseModel [id=" + id + ", addtime=" + addtime + ", updatetime=" + updatetime + ", deltime=" + deltime
				+ "]";
	}
	
}
