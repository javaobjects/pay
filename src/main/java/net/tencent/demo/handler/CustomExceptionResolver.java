package net.tencent.demo.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import net.tencent.demo.exception.CustomException;
/**
 * 这个不是异常类型， 是全系统统一进行异常处理的异常处理器
* <p>Title: CustomExceptionResolver</p>  
* <p>Description: </p>  
* @author xianxian 
* @date 2019年6月28日
 */
//@ControllerAdvice
public class CustomExceptionResolver implements HandlerExceptionResolver {
	//@ExceptionHandler
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		/**
		 * 对要处理的异常对象ex进行判断，看是哪种类型，针对类型做出不同的处理
		 * ProductAlreayDeletedException
		 */
		Exception e=null;
		if(ex instanceof CustomException)
		{
			e=(CustomException)ex;
		}else
		{
			e=new CustomException("未知错误");
		}
		ModelAndView mav=new ModelAndView();
		mav.setViewName("error");
		mav.addObject("message", e.getMessage());
		return mav;
	}

}
