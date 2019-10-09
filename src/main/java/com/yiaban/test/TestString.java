package com.yiaban.test;

public class TestString {
	public static void cutString(){
//		String a ="helloword";
//		String b = "hello";
//		System.out.println(a.substring(b.length()));
		String sql ="INSERT INTO USER (id,username,PASSWORD,age) VALUES (2,'孙悟空','123','800')";
		String t =" how are you";
		System.out.println(t.indexOf("are"));
		String s =sql.replace(" ","");
		s= s.substring(s.indexOf("(")+1);
		String [] a =s.substring(0,s.indexOf(")")).split(",");
		for(String b:a){
			System.out.println(b);
		}	
	}
	
	public static void joinString(String... param){
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		boolean flag=true;
		sb2.append(") VALUES (");
		sb.append("INSERT INTO "+param[0]);
		sb.append("(");
		for(int i=1;i<param.length;i++){
			if(flag){
				sb.append(param[i]);
				sb2.append("?");
				flag=false;
			}else{
				sb.append(","+param[i]);
				sb2.append(",?");
			}
		}
		sb2.append(")");
		sb.append(sb2);
		System.out.println(sb.toString());
		
	}
	public static void main(String[] args) {
		joinString("user","name","age");
	}
}
