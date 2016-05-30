package com.robertobsc.simplenote.domain;

import java.util.List;

public class Category extends Entity{

	private String name;
	private String color;

	@Override
	public String getKey() {
		return getName();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public static Category get(String key) {
		return Entity.get(Category.class, key);
	}
	public static void delete(String key) {
		Entity.delete(Category.class, key);
	}
	public static List<Category> findAll(){
		return Entity.findAll(Category.class);
	}
	
}
