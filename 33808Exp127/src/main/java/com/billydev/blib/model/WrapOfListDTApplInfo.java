package com.billydev.blib.model;

import com.billydev.blib.entity.DTAppInfo;

import java.util.ArrayList;
import java.util.List;

public class WrapOfListDTApplInfo {
	

	List<DTAppInfo>  data = new ArrayList<>();

	public List<DTAppInfo> getDtApplList() {
		return data;
	}
	public void setDtApplList(List<DTAppInfo> data) {
		this.data = data;
	} 
	

}
