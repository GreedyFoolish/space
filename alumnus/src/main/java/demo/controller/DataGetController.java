package demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.annotation.AuthValidate;
import demo.bean.MsgException;
import demo.dao.model.AlmnCommentAnls;
import demo.dao.model.AlmnDaily;
import demo.dao.model.AlmnEffectSet;
import demo.dao.model.AlmnInfoCheck;
import demo.dao.model.AlmnNav;
import demo.dao.model.AlmnRole;
import demo.dao.model.AlmnUser;
import demo.handler.AuthCode;
import demo.utils.Mutils;

@Controller
@RequestMapping(value = "", method = { RequestMethod.GET })
public class DataGetController extends BaseController {

	@AuthValidate(AuthCode.MANAGER) // 表示需要登录权限
	@RequestMapping(value = { "", "index" })
	public String index() throws MsgException {
		return "index";
	}

	@RequestMapping(value = "login")
	public String login(HttpSession session, HttpServletResponse response) {
		// 添加token，避免重复提交
		String token = Mutils.md5(Mutils.randomString(10));
		// 写入验证码
		String valicode = Mutils.randomString(4);
		// 保存数据进入session，便于二次验证
		session.setAttribute("login_token", token);
		session.setAttribute("login_valicode", valicode);
		// 将数据写入到页面上，会自动拼接为/WEB-INF/WEB-VIEWS/login.jsp
		Cookie cookieA = new Cookie("login_token", token);
		Cookie cookieB = new Cookie("login_valicode", valicode);
		response.addCookie(cookieA);
		response.addCookie(cookieB);
		return "login";
	}

	@CrossOrigin
	@RequestMapping(value = "getAllImportUser")
	@ResponseBody
	public List<AlmnInfoCheck> getAllImportUser() throws MsgException {
		List<AlmnInfoCheck> list = infoCheckService.getAllUserInfo();
		return list;
	}
	/**
	 * 	获取所有用户信息
	 */
	@CrossOrigin
	@RequestMapping(value = "getUserInfo")
	@ResponseBody
	public List<AlmnUser> getUserInfo() throws MsgException {
		List<AlmnUser> list = userService.getAllUser();
		for(AlmnUser user : list) {
			user.setUserPasswd("***");
		}
		return list;
	}
	/**
	 * 	获取所有角色信息
	 */
	@CrossOrigin
	@RequestMapping(value = "getRoleInfo")
	@ResponseBody
	public List<AlmnRole> getRoleInfo() throws MsgException {
		return roleService.getAllRole();
	}
	/**
	 * 	得到回收用户
	 */
	@CrossOrigin
	@RequestMapping(value = "getDelUserInfo")
	@ResponseBody
	public List<AlmnUser> getDelUserInfo() throws MsgException {
		List<AlmnUser> list = userService.getRestoreUser();
		for(AlmnUser user : list) {
			user.setUserPasswd("***");
		}
		return list;
	}
	
	@CrossOrigin
	@RequestMapping(value = "getHotUserList")
	@ResponseBody
	public List<AlmnUser> getHotUserList() throws MsgException {
		List<AlmnUser> userList = userService.gethotUserList();
		if(userList.size() > 5) {
			List<AlmnUser> userList2 = new ArrayList<AlmnUser>();
			userList2.add(userList.get(0));
			userList2.add(userList.get(1));
			userList2.add(userList.get(2));
			userList2.add(userList.get(3));
			userList2.add(userList.get(4));
			return userList2;
		}
		return userList;
	}

	@CrossOrigin
	@RequestMapping(value = "getHotDailyList")
	@ResponseBody
	public List<AlmnDaily> getHotDailyList() throws MsgException {
		List<AlmnDaily> dailyList = dailyService.gethotDailyList();
		if(dailyList.size() > 5) {
			List<AlmnDaily> udailyList = new ArrayList<AlmnDaily>();
			udailyList.add(dailyList.get(0));
			udailyList.add(dailyList.get(1));
			udailyList.add(dailyList.get(2));
			udailyList.add(dailyList.get(3));
			udailyList.add(dailyList.get(4));
			return udailyList;
		}
		return dailyList;
	}
	
	@CrossOrigin
	@RequestMapping(value = "getAllCommentSet")
	@ResponseBody
	public List<AlmnCommentAnls> getAllCommentSet() throws MsgException {
		List<AlmnCommentAnls> list = commentAnlsService.getCommentAnls();
		return list;
	}
	/**
	 * 	获取所有热度设置信息
	 */
	@CrossOrigin
	@RequestMapping(value = "getEffectSetList")
	@ResponseBody
	public List<AlmnEffectSet> getEffectSetList() throws MsgException {
		return effectSetService.getAllEffectSet();
	}
	/**
	 * 	获取所有导航
	 */
	@CrossOrigin
	@RequestMapping(value = "getAllNav")
	@ResponseBody
	public List<AlmnNav> getAllNav() throws MsgException {
		return navService.selectNavList();
	}
	
}
