package com.yiaban.test;

public class TestAnyParam {
	public static void show(Object...param){
		int sum=0;
		for(int i =0;i<param.length;i++){
			System.out.println(param[i] instanceof String);
			//System.out.println(sum+=(int)param[i]);
		}
	}
	public static void main(String[] args) {
		show(1,2,3,"");
	}
}
