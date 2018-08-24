package com.yiaban.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.yiban.model.LoveLink;
import com.yiban.model.User;

public class TestReflect {
	public static void showProperty(Object o){
		Field[] fs = o.getClass().getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			String name =fs[i].getName();
			String type=fs[i].getType().getName();
			//System.out.println(type+"---");
			if(type.equals("int")){
				System.out.println( "int set"+name.substring(0,1).toUpperCase()+name.substring(1));
			}else if(type.equals("java.lang.String")){
				System.out.println("String set"+name.substring(0,1).toUpperCase()+name.substring(1));
			}
		}
	}
	public static void show(Object o){
		try {
			Method[] m =  o.getClass().getDeclaredMethods();
			for (int i = 0; i < m.length; i++) {

				Class[] cs = m[i].getParameterTypes();
				if(m[i].getName().startsWith("set")){
					String type =cs[0].getName();
					String name=m[i].getName();
					if(type.equals("int")){
						System.out.println( "int "+name.substring(3).substring(0,1).toLowerCase()+name.substring(4));
					}else if(type.equals("java.lang.String")){
						System.out.println("String "+name.substring(3));
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	public static List<Object> showClass(Object o){
		List<Object> list = new ArrayList<Object>(); 
		try {
			Class<? extends Object> c =  o.getClass();
			
			for(int i=0;i<2;i++){
				list.add(c.newInstance());
			}
			
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		//show(new LoveLink());
		//showProperty(new LoveLink());
		List<Object> list = showClass(new User());
		System.out.println(list.get(0)==list.get(1));
	}
	
}
