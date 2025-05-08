package demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import demo.dao.mapper.AlmnCoinLogMapper;
import demo.dao.mapper.AlmnCommentAnlsMapper;
import demo.dao.mapper.AlmnCommentMapper;
import demo.dao.mapper.AlmnDailyMapper;
import demo.dao.mapper.AlmnEffectSetMapper;
import demo.dao.mapper.AlmnFavorLogMapper;
import demo.dao.mapper.AlmnInfoCheckMapper;
import demo.dao.mapper.AlmnNavMapper;
import demo.dao.mapper.AlmnRoleMapper;
import demo.dao.mapper.AlmnUserMapper;
import demo.dao.model.AlmnUser;
import demo.dao.service.AlmnCoinLogService;
import demo.dao.service.AlmnCommentAnlsService;
import demo.dao.service.AlmnCommentService;
import demo.dao.service.AlmnDailyService;
import demo.dao.service.AlmnEffectSetService;
import demo.dao.service.AlmnFavorLogService;
import demo.dao.service.AlmnInfoCheckService;
import demo.dao.service.AlmnNavService;
import demo.dao.service.AlmnRoleService;
import demo.dao.service.AlmnUserService;

public class BaseController {
	@Autowired
	protected HttpServletRequest httpRequest;
	@Autowired
	protected AlmnUserMapper userMapper;
	@Autowired
	protected AlmnUserService userService;
	@Autowired
	protected AlmnRoleMapper roleMapper;
	@Autowired
	protected AlmnRoleService roleService;
	@Autowired
	protected AlmnEffectSetMapper effectSetMapper;
	@Autowired
	protected AlmnEffectSetService effectSetService;
	@Autowired
	protected AlmnNavMapper navMapper;
	@Autowired
	protected AlmnNavService navService;
	@Autowired
	protected AlmnDailyMapper dailyMapper;
	@Autowired
	protected AlmnDailyService dailyService;
//	@Autowired
//	protected AlmnDailyMapper dailyMapper;
//	@Autowired
//	protected AlmnDailyService dailyService;
	@Autowired
	protected AlmnFavorLogMapper favorLogMapper;
	@Autowired
	protected AlmnFavorLogService favorLogService;
	@Autowired
	protected AlmnCoinLogMapper coinLogMapper;
	@Autowired
	protected AlmnCoinLogService coinLogService;
	@Autowired
	protected AlmnCommentMapper commentMapper;
	@Autowired
	protected AlmnCommentService commentService;
	@Autowired
	protected AlmnInfoCheckMapper infoCheckMapper;
	@Autowired
	protected AlmnInfoCheckService infoCheckService;
	@Autowired
	AlmnCommentAnlsMapper commentAnlsMapper;
	@Autowired
	AlmnCommentAnlsService commentAnlsService;

	// 获取用户对象
	protected AlmnUser getUser() {
		return (AlmnUser) httpRequest.getSession().getAttribute("loginUser");
	}

}
