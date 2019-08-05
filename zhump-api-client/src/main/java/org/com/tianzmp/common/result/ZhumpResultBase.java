package org.com.tianzmp.common.result;

import org.com.tianzmp.common.ResultBase;

public class ZhumpResultBase extends ResultBase {

	public ZhumpResultBase(ZhumpResultStatus zhumpResultStatus, Object data) {
		super(zhumpResultStatus.getCode(), zhumpResultStatus.getMessage(), data);
	}
}
