package org.com.tianzmp.vo;

import java.util.ArrayList;
import java.util.List;

import org.com.tianzmp.entity.ZhumpCategory;

/**
* @Title: ZhumpCategoryVO.java
* @Package dell
* @Description:
* @author zhump  
* @date 2019/7/31 14:58
* @version V1.0  
*/
public class ZhumpCategoryVO extends ZhumpCategory {
	
	
	private List<ZhumpCategoryVO> childer = new ArrayList<ZhumpCategoryVO>();

	public List<ZhumpCategoryVO> getChilder() {
		return childer;
	}

	public void setChilder(List<ZhumpCategoryVO> childer) {
		this.childer = childer;
	}

	
	
	

}
