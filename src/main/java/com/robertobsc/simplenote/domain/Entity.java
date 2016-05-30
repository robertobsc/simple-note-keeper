package com.robertobsc.simplenote.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robertobsc.simplenote.domain.registry.Registry;

public class Entity {
	private static final Registry registry = new Registry();
	
	private final static Logger logger = LoggerFactory.getLogger(Entity.class);
	
	private String key;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public static <T extends Entity> T fromJson(Class<T> clazz, String json) {
		T entity = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		
		try {
			entity = mapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.error("Error while converting object {} from this json: {}", 
					clazz.getSimpleName(), json);
		}
		
		return entity;
	}
	
	public <T extends Entity> String toJson(Class<T> clazz) {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			logger.error("Error while converting object {} to json", clazz.getSimpleName());
			return null;
		}
	}
	
	public static <T extends Entity> T create(T entity) {
		return registry.create(entity);
	}
	public static <T extends Entity> T save(T entity) {
		return registry.save(entity);
	}
	public static <T extends Entity> T get(Class<T> clazz, String key) {
		return registry.get(clazz,key);
	}
	public static <T extends Entity> List<T> findAll(Class<T> clazz) {
		return registry.findAll(clazz);
	}
	
	public static <T extends Entity> void delete(Class<T> clazz, String key) {
		registry.delete(clazz,key);
	}
}
