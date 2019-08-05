package org.com.tianzmp.vo;

import java.util.ArrayList;
import java.util.List;

import org.com.tianzmp.entity.TianCategory;
/**
* @Title: TianCategoryVO.java
* @Package dell
* @Description:
* @author zhump  
* @date 2019/7/31 14:58
* @version V1.0  
*/
public class TianCategoryVO extends TianCategory{
	
	
	private List<TianCategoryVO> childer = new ArrayList<TianCategoryVO>();

	public List<TianCategoryVO> getChilder() {
		return childer;
	}

	public void setChilder(List<TianCategoryVO> childer) {
		this.childer = childer;
	}

	
	
	

}
