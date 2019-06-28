package net.tencent.demo.exception;
/**
 * 自定义异常
* <p>Title: CustomException</p>  
* <p>Description: </p>  
* @author xianxian 
* @date 2019年6月28日
 */
public class CustomException extends Exception{
	
	private String message;
	
	public CustomException(String message)
	{
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
