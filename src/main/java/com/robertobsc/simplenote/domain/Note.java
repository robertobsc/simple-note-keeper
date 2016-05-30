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
