package com.yiban.dao;

public class MysqlDaoFactory implements IDaoFactory{
	
	private  MysqlDaoFactory() {
	}
	private static MysqlDaoFactory df = new MysqlDaoFactory();
	public static  IDaoFactory getInstance(){
		return df;
	}
	
	@Override
	public ILoveLinkDao getLoveLinkDao() {
		return new LoveLinkDao();
	}
	
	
	
}
