package org.com.tianzmp.common.result;


public class Result extends ResoutlBase{

	public Result(ResultStatus status,Object data) {
		super(status.getCode(), status.getMessage(),data);
	}

}
