package org.com.tianzmp.common.result;

public enum ResultStatus {
	/************系统状态码**************/
	/**操作成功*/
	SUCCESS("000001","操作成功"),
	/**操作失败*/
	FALI("000002","操作失败"),
	/**系统异常*/
	ERROR("000003","系统异常"),
	
	
	
	/****************业务状态码********************/
	/**参数模块异常码*/
	PARMSERROR("000400","参数错误")

	;
	
	private ResultStatus(String code, String message) {
		this.code = code;
		this.message = message;
	}

	private String code;
	
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
	

}
