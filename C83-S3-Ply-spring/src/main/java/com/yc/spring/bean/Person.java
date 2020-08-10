package com.yc.spring.bean;

import java.util.List;

public class Person {

	private String name;
	
	private String id;

	private int age;
	
	private List<String> killeds;
	
	//身高
	private int Height;
	
	//绰号
	private String alisa;

	public List<String> getKilleds() {
		return killeds;
	}

	public void setKilleds(List<String> killeds) {
		this.killeds = killeds;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public String getAlisa() {
		return alisa;
	}

	public void setAlisa(String alisa) {
		this.alisa = alisa;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", id=" + id + ", age=" + age + ", killeds=" + killeds + ", Height=" + Height
				+ ", alisa=" + alisa + "]";
	}

}
