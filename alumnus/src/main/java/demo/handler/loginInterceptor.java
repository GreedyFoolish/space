package demo.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import demo.annotation.AuthValidate;
import demo.bean.MsgException;
import demo.dao.mapper.AlmnUserMapper;
import demo.dao.model.AlmnUser;
import demo.dao.service.AlmnUserService;
import demo.utils.Mutils;

/**
 * 自定义的登录拦截器
 */
public class loginInterceptor implements HandlerInterceptor {

	@Autowired
	private AlmnUserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 判定是否为空
		if(handler == null || !(handler instanceof HandlerMethod)) {
			// 表示访问的内容无需进行权限的判断，直接返回true可以直接访问下一步操作
			return true;
		}
		// 获取目标执行方法是否有Authvalidate的注解
		AuthValidate validate = ((HandlerMethod)handler).getMethodAnnotation(AuthValidate.class);
		if(validate == null) {
			return true;
		}
		// 按注解内容value来判断是否拥有登录权限
		if(validate.value() == AuthCode.MANAGER) {
			// 当前的登录必须要判断User是否拥有登录权限
			AlmnUser user = loginCheck(request);
			if(user == null) {
				// 此时需要调用重定向，到登录界面
				response.sendRedirect(AuthCode.MANAGER.getUrl());
				return false;
			}
			
		}
		// false表示没有权限，不再向后执行，true表示拥有权限继续执行下一个拦截器方法或controller方法
		return true;
	}
	
	@Autowired
	AlmnUserMapper userMapper;
	private AlmnUser loginCheck(HttpServletRequest request) throws MsgException {
		AlmnUser user = (AlmnUser) request.getSession().getAttribute("loginUser");
		if(user == null && Mutils.IsAppDebug() ) {
			user = userMapper.findUserByUid("root");
			request.getSession().setAttribute("loginUser",user);
			// 在session中记录用户的权限列表，因为在首页进行了列表的刷新，所以登录过程中不需要刷新session
//			request.getSession().setAttribute("navList", navService.selectNavByManager(manager));
		}
        return user;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
