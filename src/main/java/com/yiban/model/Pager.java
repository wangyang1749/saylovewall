package com.yiban.model;

import java.util.List;

public class Pager<YBUser> {

	private int pageCount;
	private List<YBUser> list;
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<YBUser> getList() {
		return list;
	}
	public void setList(List<YBUser> list) {
		this.list = list;
	}
 	
}
