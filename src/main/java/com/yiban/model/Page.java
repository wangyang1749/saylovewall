package com.yiban.model;

import java.util.List;
/**
 * 
 * @author 王阳
 *
 * @param totalRecord 一共有多少条记录,totalPage 一共有多少页,
	currentPage 当前是第几页,pageSize 一页有多少条数据
 * 		
 */
public class Page<P> {
	private int totalRecord;//一共有多少条记录
	private int totalPage;//一共有多少页
	private int currentPage;//当前是第几页
	private int pageSize;//一页有多少条数据
	private int currentRecord;
	private List<P> list;
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<P> getList() {
		return list;
	}
	public void setList(List<P> list) {
		this.list = list;
	}
	public int getCurrentRecord() {
		return currentRecord;
	}
	public void setCurrentRecord(int currentRecord) {
		this.currentRecord = currentRecord;
	}
	
}
