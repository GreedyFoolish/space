package demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;

import demo.bean.ExcelUserImport;
import demo.bean.LoginBean;
import demo.bean.MsgBean;
import demo.bean.MsgException;
import demo.bean.UserImportExcelReadListener;
import demo.dao.model.AlmnCoinLog;
import demo.dao.model.AlmnComment;
import demo.dao.model.AlmnDaily;
import demo.dao.model.AlmnEffectSet;
import demo.dao.model.AlmnFavorLog;
import demo.dao.model.AlmnNav;
import demo.dao.model.AlmnRole;
import demo.dao.model.AlmnUser;
import demo.dao.model.AlmnUserExample;

@Controller
@RequestMapping(value = "", method = { RequestMethod.POST })
public class DataPostController extends BaseController {
	/**
	 * 	登录处理
	 */
	@CrossOrigin
	@RequestMapping(value = "loginDeal")
	@ResponseBody // 限定返回类型
	public MsgBean loginDeal(@RequestBody LoginBean loginBean, HttpSession session) throws MsgException {
		// 优先进行token的验证
		String stoken = (String) session.getAttribute("login_token");
//		if(loginBean.getToken() == null || !loginBean.getToken().equals(stoken)) {
//			throw new MsgException("请不要重复提交");
//		}
		// token验证成功，先清空login_token
		// 再次验证valicode
//		String valicode = (String) session.getAttribute("login_valicode");
//		if(loginBean.getValicode() == null || !loginBean.getValicode().equalsIgnoreCase(valicode)) {
//			throw new MsgException("验证码不正确");
//		}
		// valicode验证成功，先清空login_valicode
		session.removeAttribute("login_valicode");
		session.removeAttribute("login_token");
		AlmnUser user = userService.checkUserLogin(loginBean);
		user.setUserPasswd("***");
		session.setAttribute("loginUser", user);
		MsgBean bean = new MsgBean();
		bean.setCode(0);
		bean.setExtras(user);
		return bean;
	}

	@CrossOrigin
	@RequestMapping(value = "registerDeal")
	@ResponseBody // 限定返回类型
	public MsgBean registerDeal(@RequestBody LoginBean loginBean, HttpSession session) throws MsgException {
		// 优先进行token的验证
		String stoken = (String) session.getAttribute("login_token");
		if(loginBean.getToken() == null || !loginBean.getToken().equals(stoken)) {
			throw new MsgException("请不要重复提交");
		}
		// token验证成功，先清空login_token
		session.removeAttribute("login_token");
		// 再次验证valicode
//		String valicode = (String) session.getAttribute("login_valicode");
//		if(loginBean.getValicode() == null || !loginBean.getValicode().equalsIgnoreCase(valicode)) {
//			throw new MsgException("验证码不正确");
//		}
		// valicode验证成功，先清空login_valicode
		session.removeAttribute("login_valicode");
		int flag = userService.registerUser(loginBean.getUserId(),loginBean.getPassword());
		
		MsgBean bean = new MsgBean();
		bean.setCode(0);
		bean.setExtras("");
		return bean;
	}
	/**
	 * 	用户导入
	 */
	@CrossOrigin
	@RequestMapping(value="excelUploader")
	@ResponseBody
	public MsgBean excelUploader(@RequestParam("file") MultipartFile[] file) throws MsgException, IllegalStateException, IOException {
		// 尝试做文件的上传
		if(file == null || file.length < 1) {
			MsgBean msg = new MsgBean();
			msg.setMsg("上传文件不能为空，必须选择一个上传文件");
			return msg;
		}
		MultipartFile mf = file[0];
		File saveFile = new File("E:\\Java\\workspace\\alumnus\\src\\main\\webapp\\static\\userImport\\" + mf.getOriginalFilename());
        String filename = saveFile.getName();
        String name = filename.substring(0,filename.indexOf("."));
        String suffix = filename.substring(filename.lastIndexOf("."));
        int i = 1;
        //若文件存在重命名
        while(saveFile.exists() && saveFile.length()>1) {
            String newFilename = name+"("+i+")"+suffix;
            String parentPath = saveFile.getParent();
            saveFile = new File(parentPath + File.separator + newFilename);
            i++;
        }
		mf.transferTo(saveFile);
		UserImportExcelReadListener listener = new UserImportExcelReadListener(userService);
		// 从Excel中去读数据，将读取到的数据写入到数据库中
		List<ExcelUserImport> list = EasyExcel.read(saveFile,ExcelUserImport.class,listener).sheet().doReadSync();
		MsgBean bean = new MsgBean();
		bean.setCode(0);
		bean.setExtras(listener.getErrorList());
		return bean;
	}

	@CrossOrigin
	@RequestMapping(value = "userIconUploader")
	@ResponseBody
	public AlmnUser userIconUploader(@RequestParam(value = "img") MultipartFile[] file, @RequestParam String userId) throws MsgException {
		String path = "E:/Java/workspace/alumnus/src/main/webapp/static/Image/UserIcon/";
		long timec = System.currentTimeMillis();
		String filename = file[0].getOriginalFilename();
		int sub = filename.lastIndexOf(".");
		String suffix = filename.substring(sub);
		path = path + timec + suffix;
		File savefile = new File(path);
		try {
			file[0].transferTo(savefile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		String imgName = timec + suffix;
		AlmnUser user = new AlmnUser();
		user.setUserId(userId);
		user.setUserIcon(imgName);
		userService.updateUserIcon(user);
		AlmnUserExample example = new AlmnUserExample();
		AlmnUserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<AlmnUser> list = userMapper.selectByExample(example);
		return list.get(0);
	}
	/**
	 * 获得登录用户信息，用于更新cookie
	 */
	@CrossOrigin
	@RequestMapping(value = "getMyUserInfo")
	@ResponseBody
	public AlmnUser getMyUserInfo(@RequestParam String userId) throws MsgException {
		AlmnUser user = userService.getUserInfo(userId).get(0);
		user.setUserPasswd("***");
		return user;
	}
	
	@CrossOrigin
	@RequestMapping(value = "updateRoleInfo")
	@ResponseBody
	public List<AlmnRole> updateRoleInfo(@RequestParam Integer id, @RequestParam String roleName) throws MsgException {
		roleService.updateRoleInfo(id, roleName);
		return roleService.getAllRole();
	}
	/**
	 * 	编辑用户
	 */
	@CrossOrigin
	@RequestMapping(value = "editUser")
	@ResponseBody
	public AlmnUser editUser(@RequestParam String userId, @RequestParam Integer roleId, @RequestParam String userRealname, @RequestParam String userIdcard, @RequestParam String userPhone, @RequestParam String userMail, @RequestParam String personalHonor) throws MsgException {
		AlmnUser user = new AlmnUser();
		user.setUserId(userId);
		user.setRoleId(roleId);
		user.setUserRealname(userRealname);
		user.setUserIdcard(userIdcard);
		user.setUserPhone(userPhone);
		user.setUserMail(userMail);
		user.setPersonalHonor(personalHonor);
		user = userService.UpdateUser(user);
		user.setUserPasswd("***");
		return user;
	}
	/**
	 *	删除用户
	 */
	@CrossOrigin
	@RequestMapping(value = "delUser")
	@ResponseBody
	public MsgException delUser(@RequestParam String userId) throws MsgException {
		userService.DelUser(userId);
		MsgException msg = new MsgException();
		msg.setCode(200);
		msg.setMsg("删除用户成功");
		return msg;
	}
	/**
	 * 	还原用户
	 */
	@CrossOrigin
	@RequestMapping(value = "restoreDelUser")
	@ResponseBody
	public MsgException restoreDelUser(@RequestParam String userId) throws MsgException {
		userService.restoreDelUser(userId);
		MsgException msg = new MsgException();
		msg.setCode(200);
		msg.setMsg("还原成功");
		return msg;
	}
	/**
	 * 	发布动态
	 */
	@CrossOrigin
	@RequestMapping(value = "editDailyList")
	@ResponseBody
	public MsgBean editDailyList(@RequestParam String userId,@RequestParam String dailyContent, @RequestParam ArrayList<String> imgList) throws MsgException {
		String dailyId = userId + "@" + System.currentTimeMillis();
		AlmnDaily daily = new AlmnDaily();
		daily.setUserId(userId);
		daily.setDailyId(dailyId);
		daily.setDailyContent(dailyContent);
		String imgs= imgList.get(0);
		for (int i=1; i<imgList.size(); i++) {
			imgs = imgs + "@" + imgList.get(i);;
		}
		daily.setDailyImgList(imgs);
		dailyService.insertOrUpdateDaily(daily);
		//"static/Image/DailyImage" + "/" +
		MsgBean bean = new MsgBean();
		bean.setCode(0);
		bean.setExtras("push daily succ");
		return bean;
	}
	/**
	 * 	提交图片
	 */
	@CrossOrigin
	@RequestMapping(value = "dailyImgUploader")
	@ResponseBody
	public ArrayList<String> dailyImgUploader(@RequestParam(value = "imgs") MultipartFile[] file) throws MsgException {
		ArrayList<String> msg = new ArrayList<String>();
		for (int i = 0; i < file.length; ++i) {
			String path;
			long imagename = System.currentTimeMillis();
			String filename = file[i].getOriginalFilename();
			int sub = filename.lastIndexOf(".");
			String suffix = filename.substring(sub);
//			System.out.println("suffix----------->"+suffix);
			path = "E:/Java/workspace/alumnus/src/main/webapp/static/Image/DailyImage/";
			path = path + imagename + "-0" + (i + 1) + suffix;
			File savefile = new File(path);
			try {
				file[i].transferTo(savefile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			msg.add(imagename + "-0" + (i + 1) + suffix);
		}
		return msg;
	}
	/**
	 * 	获取动态列表
	 */
	@CrossOrigin
	@RequestMapping(value = "getDailyList")
	@ResponseBody
	public List<AlmnDaily> getDailyList(@RequestParam String userId, @RequestParam Integer flag) throws MsgException {
		List<AlmnDaily> dailyList = dailyService.selectAllDaily(userId, flag);
		return dailyList;
	}
	/**
	 * 	获取点赞记录
	 */
	@CrossOrigin
	@RequestMapping(value = "getFavorLog")
	@ResponseBody
	public List<AlmnFavorLog> getFavorLog(@RequestParam String userId) throws MsgException {
		return favorLogService.getFavorLog(userId);
	}
	/**
	 * 	获取动态评论信息
	 */
	@CrossOrigin
	@RequestMapping(value = "getComment")
	@ResponseBody
	public List<AlmnComment> getComment(@RequestParam String dailyId) throws MsgException {
		return commentService.selectCommentByDailyId(dailyId);
	}

	@CrossOrigin
	@RequestMapping(value = "getEnjoyDaily")
	@ResponseBody
	public List<AlmnDaily> getEnjoyDaily(@RequestParam String userId, @RequestParam String enjoyUser, @RequestParam String enjoyTag) throws MsgException {
		List<AlmnDaily> dailyList = dailyService.getEnjoyDaily(userId, enjoyUser, enjoyTag);
		return dailyList;
	}
	
	@CrossOrigin
	@RequestMapping(value = "getEnjoyUser")
	@ResponseBody
	public List<AlmnDaily> getEnjoyUser(@RequestParam String userId, @RequestParam Integer flag) throws MsgException {
		List<AlmnDaily> dailyList = dailyService.selectAllDaily(userId, flag);
		return dailyList;
	}
	@CrossOrigin
	@RequestMapping(value = "updateDailyTag")
	@ResponseBody
	public int updateDailyTag(@RequestParam String dailyId, @RequestParam String tagInput) throws MsgException {
		return dailyService.updateDailyTag(dailyId, tagInput);
	}
	/**
	 * 	点赞事件
	 */
	@CrossOrigin
	@RequestMapping(value = "addFavorLog")
	@ResponseBody
	public String addFavorLog(@RequestParam String userId, @RequestParam String dailyId, @RequestParam String dailyTag1, @RequestParam String dailyTag2) throws MsgException {
		userService.addEnjoyTag(userId ,dailyTag1, dailyTag2);
		// 添加点赞记录
		AlmnFavorLog favorLog = new AlmnFavorLog();
		favorLog.setDailysId(dailyId);
		String[] str = dailyId.split("@");
		String user1 = str[0];
		favorLog.setUser1Id(user1);
		favorLog.setUser2Id(userId);
		int flag = favorLogService.countFavorLog(favorLog,"add");
		if(flag == 1) {
			favorLogService.insert(favorLog);
			return "succ";
		} else if(flag == 2) {
			favorLogService.updateAdd(favorLog);
			return "succ";
		}
		return "already";
	}
	/**
	 * 	取消点赞事件
	 */
	@CrossOrigin
	@RequestMapping(value = "cancelFavorLog")
	@ResponseBody
	public String cancelFavorLog(@RequestParam String userId, @RequestParam String dailyId, @RequestParam String dailyTag1, @RequestParam String dailyTag2) throws MsgException {
		AlmnFavorLog favorLog = new AlmnFavorLog();
		favorLog.setDailysId(dailyId);
		String[] str = dailyId.split("@");
		String user1 = str[0];
		favorLog.setUser1Id(user1);
		favorLog.setUser2Id(userId);
		int flag = favorLogService.countFavorLog(favorLog,"cancel");
		if(flag == 2) {
			favorLogService.cancelFavorLog(favorLog);
			return "cancel";
		}
		return "can't cancel";
	}
	
	@CrossOrigin
	@RequestMapping(value = "getCoinLog")
	@ResponseBody
	public List<AlmnCoinLog> getCoinLog(@RequestParam String userId) throws MsgException {
		return coinLogService.getCoinLog(userId);
	}
	/**
	 * 	添加投币记录
	 */
	@CrossOrigin
	@RequestMapping(value = "addCoinLog")
	@ResponseBody
	public String addCoinLog(@RequestParam String userId, @RequestParam String dailyId, @RequestParam int coinCnt, @RequestParam String dailyTag1, @RequestParam String dailyTag2) throws MsgException {
		AlmnCoinLog coinLog = new AlmnCoinLog();
		coinLog.setDailysId(dailyId);
		coinLog.setUser1Id(dailyId.split("@")[0]);
		coinLog.setUser2Id(userId);
		coinLog.setCoinCnt(coinCnt);
		coinLogService.insert(coinLog);
		userService.updateCoinNum(userId, coinCnt, dailyTag1, dailyTag2);
		return "succ";
//		int cnt = coinLogService.selectCoinCnt(coinLog);
//		int userCnt = userService.getCoinNumByUid(userId);
//		// 历史可投币数 ==> 最终执行的投币数
//		int doCnt = 3 - cnt;
//		// 用户剩余币数与历史可投币数作比较
//		if(userCnt < doCnt) {
//			// 用户剩余币数比历史可投币数少，则doCnt替换，得到可执行投币数
//			doCnt = userCnt;
//		}
//		// 用户操作投币数与可执行投币数比较
//		if(coinCnt < doCnt) {
//			// 若用户操作投币数比可执行投币数少，则doCnt替换，得到最终执行的投币数
//			doCnt = coinCnt;
//		}
//		System.out.println(" --------- > userid "+userId+" ucnt: "+ userCnt +" cnt "+ cnt+" docnt "+ doCnt);
//		if(doCnt > 0) {
//			coinLog.setCoinCnt(doCnt);
//			coinLogService.insert(coinLog);
//			userService.updateCoinNum(userId, doCnt);
//			return "投币成功";
//		}
	}
	/**
	 * 	评论事件
	 * @param commt1Id 被评论用户
	 * @param commt2Id 评论用户
	 */
	@CrossOrigin
	@RequestMapping(value = "addComment")
	@ResponseBody
	public String addComment(@RequestParam String commt1Id, @RequestParam String commt2Id, @RequestParam String dailyId, @RequestParam String comment) throws MsgException {
		AlmnComment commt = new AlmnComment();
//		long nowTime = System.currentTimeMillis();
		String cmt1 = commt1Id.split("@")[0];
		// 第一次评论
//		if(commt1Id.indexOf("@") == -1) {
//			cmt1 = cmt1 + "@" + nowTime;
//		} else {
//			cmt1 = commt1Id;
//		}
		String cmt2 = commt2Id.split("@")[0];// + "@" + nowTime;
		commt.setDailysId(dailyId);
		// 被评论用户
		commt.setComment1Id(cmt1);
		// 评论用户
		commt.setComment2Id(cmt2);
		commt.setCommentContent(comment);
		commentService.insert(commt);
		return "suss";
	}

	@CrossOrigin
	@RequestMapping(value = "updateCommentSet")
	@ResponseBody
	public void updateCommentSet(@RequestParam String list, @RequestParam Integer flag) throws MsgException {
		String[] arr = list.split("/");
		commentAnlsService.delCommentAnls(flag);
		for(int i=0; i< arr.length; i++) {
			commentAnlsService.insert(arr[i], flag);
		}
		return ;
	}
	/**
	 * 	设置热度值
	 */
	@CrossOrigin
	@RequestMapping(value = "updateEffectSet")
	@ResponseBody
	public List<AlmnEffectSet> updateEffectSet(@RequestParam String key, @RequestParam Integer val) throws MsgException {
		effectSetService.setEffectSet(key, val);
		userService.updateUserEffect();
		dailyService.updateDailyEffect();
		return effectSetService.getAllEffectSet();
	}
	/**
	 * 	添加导航
	 */
	@CrossOrigin
	@RequestMapping(value = "addNav")
	@ResponseBody
	public List<AlmnNav> addNav(@RequestParam Integer sort, @RequestParam String name, @RequestParam String url, @RequestParam String icon) throws MsgException {
		AlmnNav nav = new AlmnNav();
		nav.setNavName(name);
		nav.setNavUrl(url);
		nav.setNavIcon(icon);
		navService.addNav(nav);
		return navService.selectNavList();
	}
	/**
	 * 	更新导航
	 */
	@CrossOrigin
	@RequestMapping(value = "updateNav")
	@ResponseBody
	public List<AlmnNav> updateNav(@RequestParam Integer id, @RequestParam String name, @RequestParam String url, @RequestParam String icon) throws MsgException {
		AlmnNav nav = new AlmnNav();
		nav.setId(id);
		nav.setNavName(name);
		nav.setNavUrl(url);
		nav.setNavIcon(icon);
		navService.updateNav(nav);
		return navService.selectNavList();
	}
	
}
