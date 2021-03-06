package com.robertobsc.simplenote.domain;

import java.util.List;

public class Note extends Entity {

	private String note;
	private String title;
	private Category category;
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public static Note fromJson(String json){
		return Entity.fromJson(Note.class, json);
	}
	public static Note save(Note note) {
		
		Category cat = note.getCategory();
		if (cat != null) {
			cat = Category.get(cat.getKey());
			note.setCategory(cat);
		}
		
		return Entity.save(note);
	}
	public static Note get(String key) {
		return Entity.get(Note.class, key);
	}
	public static List<Note> findAll() {
		return Entity.findAll(Note.class);
	}
	public static void delete(String key) {
		Entity.delete(Note.class, key);
	}
}
