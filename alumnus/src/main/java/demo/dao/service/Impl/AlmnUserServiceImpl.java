package demo.dao.service.Impl;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import demo.bean.ExcelUserImport;
import demo.bean.LoginBean;
import demo.bean.MsgException;
import demo.dao.mapper.AlmnInfoCheckMapper;
import demo.dao.mapper.AlmnUserMapper;
import demo.dao.model.AlmnInfoCheck;
import demo.dao.model.AlmnInfoCheckExample;
import demo.dao.model.AlmnUser;
import demo.dao.model.AlmnUserExample;
import demo.dao.service.AlmnInfoCheckService;
import demo.dao.service.AlmnUserService;

@Service // 告诉Spring框架这个类是一个Service，会由Spring框架协助初始化
public class AlmnUserServiceImpl implements AlmnUserService {
	@Autowired
	private AlmnUserMapper userMapper;

	@Override
	public int registerUser(String userId, String password) throws MsgException {
		AlmnUser user = new AlmnUser();
		user.setUserId(userId);
		user.setUserPasswd(password);
		return userMapper.insertSelective(user);
	}
	
	@Override
	public List<AlmnUser> getAllUser() throws MsgException {
		// 添加查询条件
		AlmnUserExample example = new AlmnUserExample();
		AlmnUserExample.Criteria criteria = example.createCriteria();
		return userMapper.selectByExample(example);
	}

	@Override
	public AlmnUser checkUserLogin(LoginBean loginBean) throws MsgException {
//		System.out.println("loginBean=============>"+loginBean.getUserId());
		AlmnUser user = userMapper.findUserByUid(loginBean.getUserId());
		if(user == null) {
			throw new MsgException("账号不存在"+loginBean.toString());
		}
		// 开始验证密码是否正确
		if(StringUtils.isEmpty(user.getUserPasswd())) {
			// 初始密码123456
			if("123456".equals(loginBean.getPassword())) {
				return user;
			}
			throw new MsgException("密码不匹配");
		} else {
			// 当前密码存在
			if(user.getUserPasswd().equals(loginBean.getPassword())) {
				return user;
			}
			throw new MsgException("密码不匹配");
		}
	}

	@Override
	public AlmnUser UpdateUser(AlmnUser user) throws MsgException {
		if(user == null) {
			throw new MsgException("请求参数错误");
		}
		AlmnUserExample example = new AlmnUserExample();
		AlmnUserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(user.getUserId());
		userMapper.updateByExampleSelective(user, example);
		return userMapper.findUserByUid(user.getUserId());
	}

	@Override
	public int getCoinNumByUid(String userId) throws MsgException {
		AlmnUserExample example = new AlmnUserExample();
		AlmnUserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<AlmnUser> list = userMapper.selectByExample(example);
		AlmnUser user = list.get(0);
		return user.getCoinNum();
	}

	@Override
	public void updateCoinNum(String userId, int num, String dailyTag1, String dailyTag2) throws MsgException {
		AlmnUserExample example = new AlmnUserExample();
		AlmnUserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<AlmnUser> list = userMapper.selectByExample(example);
		AlmnUser user = list.get(0);
		AlmnUser user2 = new AlmnUser();
		user2.setCoinNum(user.getCoinNum() - num);
		
		TreeSet<String> st = new TreeSet<String>();
		String tag = "";
		if(user.getEnjoyTag() != null) {
			String[] arr = user.getEnjoyTag().split("@");
			for(String s : arr) {
				st.add(s);
			}
		}
		if(!dailyTag1.equals("null")) {
			st.add(dailyTag1);
		}
		if(!dailyTag2.equals("null")) {
			st.add(dailyTag2);
		}
		Object[] obj = st.toArray();
		if(obj[0] != null || obj[0].equals("null")) {
			tag = (String) obj[0];
		}
		for(int i=1; i<obj.length; i++) {
			tag += "@" + obj[i];
		}
		if(tag.charAt(0) == '@') {
			tag = tag.substring(1);
//			System.out.println("s222=================>"+tag);
		}
		user2.setEnjoyTag(tag);
		userMapper.updateByExampleSelective(user2, example);
	}

	@Override
	public void DelUser(String userId) throws MsgException {
		AlmnUser user = new AlmnUser();
		int time = (int) System.currentTimeMillis();;
		user.setUserId(userId);
		user.setDeltime(time);
		userMapper.updateDelTimeByUid(user);
	}

	@Override
	public void restoreDelUser(String userId) throws MsgException {
		userMapper.updateDelTimeByUid2(userId);
	}
	
	@Override
	public List<AlmnUser> getRestoreUser() throws MsgException {
		return userMapper.selectDelUser();
	}

	@Autowired
	private AlmnInfoCheckService infoCheckService;
	@Override
	public int addUserByExcel(ExcelUserImport data) throws MsgException {
		AlmnInfoCheck user = new AlmnInfoCheck();
		if(StringUtils.isEmpty(data.getUserId())) {
			data.setErrorInfo("用户学号不能为空:"+data.toString());
			return 0;
		}
		user.setUserId(data.getUserId());
		user.setUserRealname(data.getUserRealname());
		user.setUserIdcard(data.getUserIdCard());
		user.setUserPhone(data.getUserPhone());
		user.setUserMail(data.getUserMail());
		return infoCheckService.countInsertUser(user);
	}

	@Override
	public int updateUserIcon(AlmnUser user) throws MsgException {
		return userMapper.updateUserIcon(user);
	}

	@Override
	public void updateUserEffect() {
		userMapper.updateUserEffect();
	}

	@Override
	public List<AlmnUser> gethotUserList() {
		return userMapper.gethotUserList();
	}

	@Override
	public void addEnjoyTag(String userId, String dailyTag1, String dailyTag2) {
		AlmnUserExample example = new AlmnUserExample();
		AlmnUserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<AlmnUser> list = userMapper.selectByExample(example);
		AlmnUser user = list.get(0);
		AlmnUser user2 = new AlmnUser();
		String[] arr;
		TreeSet<String> st = new TreeSet<String>();
		String tag = "";
		if(user.getEnjoyTag() != null) {
			arr = user.getEnjoyTag().split("@");
			for(String s : arr) {
				st.add(s);
			}
		}
		if(!dailyTag1.equals("null")) {
			st.add(dailyTag1);
		}
		if(!dailyTag2.equals("null")) {
			st.add(dailyTag2);
		}
		Object[] obj = st.toArray();
		if(obj[0] != null) {
			tag = (String) obj[0];
		}
		for(int i=1; i<obj.length; i++) {
			tag += "@" + obj[i];
		}
		if(tag.charAt(0) == '@') {
			tag = tag.substring(1);
//			System.out.println("s=================>"+tag);
		}
		user2.setEnjoyTag(tag);
//		System.out.println("addEnjoyTag------->"+userId);
		userMapper.updateByExampleSelective(user2, example);
	}

	@Override
	public List<AlmnUser> getUserInfo(String userId) {
		AlmnUserExample example = new AlmnUserExample();
		AlmnUserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return userMapper.selectByExample(example);
	}


}
