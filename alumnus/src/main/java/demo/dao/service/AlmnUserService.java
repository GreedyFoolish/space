package demo.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import demo.bean.ExcelUserImport;
import demo.bean.LoginBean;
import demo.bean.MsgException;
import demo.dao.model.AlmnUser;

@Service
public interface AlmnUserService {
	/**
	 * 注册用户
	 * @param userId
	 * @param password
	 * @return
	 * @throws MsgException
	 */
	int registerUser(String userId, String password) throws MsgException ;

	List<AlmnUser> getAllUser() throws MsgException ;

	AlmnUser checkUserLogin(LoginBean loginBean) throws MsgException ;

	AlmnUser UpdateUser(AlmnUser user) throws MsgException ;

	int getCoinNumByUid(String userId) throws MsgException ;

	void updateCoinNum(String userId, int doCnt, String dailyTag1, String dailyTag2) throws MsgException ;

	void DelUser(String userId) throws MsgException ;

	/**
	 * 还原用户
	 * @param userId
	 * @throws MsgException
	 */
	void restoreDelUser(String userId) throws MsgException ;
	
	/**
	 * 得到删除用户数据
	 * @return
	 * @throws MsgException
	 */
	List<AlmnUser> getRestoreUser() throws MsgException ;
	
	int addUserByExcel(ExcelUserImport data) throws MsgException ;	
	
	int updateUserIcon(AlmnUser user) throws MsgException ;

	void updateUserEffect();

	List<AlmnUser> gethotUserList();

	void addEnjoyTag(String userId, String dailyTag1, String dailyTag2);

	List<AlmnUser> getUserInfo(String userId);
	
}
