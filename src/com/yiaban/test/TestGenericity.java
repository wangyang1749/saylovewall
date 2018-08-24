package com.yiaban.test;

import java.util.ArrayList;
import java.util.List;

import com.yiban.model.User;

public class TestGenericity {
	public static void main(String[] args) {
		User u = new User();
		u.setUsername("zhansan");
		u.setPassword("1223");
		u.setAge(18);
		Animal<User> as = new Animal<User>();
		List<User> list = as.show(u);
		System.out.println(list.get(0).getUsername());
	}
}
class Animal<T>{
	List<T> list = new ArrayList<T>();
	public  List<T> show(T u){
		
		list.add(u);
		System.out.println(u);
		return list;
	}
	
}
