package demo.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import demo.bean.MsgBean;
import demo.bean.MsgException;

@ControllerAdvice	// 定义为全局异常的Controller辅助类
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {
	
	@ExceptionHandler(MsgException.class)	// 限定统一处理的异常类型
	@ResponseBody
	public MsgBean handleMsgException(MsgException exception) {
		MsgBean bean = new MsgBean();
		bean.setCode(-1);
		bean.setMsg(exception.getMessage());
		return bean;
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		// 返回值改为true，否则beforeBodyWrite的方法不执行
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		// 转换request对象类型
		ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
		HttpServletRequest httpServletRequest = serverHttpRequest.getServletRequest();
		// 判定请求类型
		if("POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
			// 判断返回值类型
			if(body instanceof MsgBean) {
				httpServletRequest.setAttribute("response", body);
			}
		}
		return body;
	}

}
