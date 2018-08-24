package com.yiaban.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.yiban.model.User;

public class TestExtends {
	public static void showPer(Person p){
		try {
			Method mm = p.getClass().getMethod("school");
			mm.invoke(p);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public static void showobj(Object o){
		System.out.println("main");
		try {
			Method[] m = o.getClass().getDeclaredMethods();
			for (int i = 0; i < m.length; i++) {
				System.out.println(m[i].getName());
				
			}
			Method mm = o.getClass().getMethod("school");
			mm.invoke(o);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public static void showObject(){
		Object  o= new User();
		User u  =(User)o;
		u.getPassword();
	}

	public static void main(String[] args) {
		
		Person p1 = new Student();
		//p1.show();
		Student s =(Student)p1;
		//s.school();
		Object o = new Student();
		
		showobj(p1);
		System.out.println("-----");
		showPer(p1);
	}
	
}


class Person{
	public void show(){
		System.out.println("Person");
	}
}
class Student extends Person{
	public void show(){
		System.out.println("Student");
	}
	public void school(){
		System.out.println("我是子类的方法");
	}

}
