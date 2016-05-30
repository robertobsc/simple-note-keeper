package com.robertobsc.simplenote.domain.registry;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.robertobsc.simplenote.domain.Entity;

public class Registry {
	
	private static SecureRandom random;
	private Map<Class<? extends Entity>,Map<String, Entity>> registryMap; 
	
	public Registry() {
		random = new SecureRandom();
		registryMap = new HashMap<>();
	}
	
	public <T extends Entity> T create(T entity) {
		return save(entity);
	}
	
	public <T extends Entity> T save(T entity) {
		if (entity.getKey() == null || entity.getKey().isEmpty()) {
			entity.setKey(generateKey());
		}		
		Map<String, Entity> mapKeyEntity = registryMap.get(entity.getClass()); 
		if (mapKeyEntity == null) {
			mapKeyEntity = new HashMap<>();
			registryMap.put(entity.getClass(), mapKeyEntity);
		}

		mapKeyEntity.put(entity.getKey(), entity);
		
		return entity;
	}
	
	public <T extends Entity> List<T> findAll(Class<T> clazz) {
		Map<String, Entity> mapKeyEntity = registryMap.get(clazz);
		return mapKeyEntity == null ? null : 
				mapKeyEntity.values().stream()
							.map(e->(T)e)
							.collect(Collectors.toList());
	}
	
	
	@SuppressWarnings("unchecked")
	public <T extends Entity> T get(Class<T> clazz, String key) {
		Map<String, Entity> mapKeyEntity = registryMap.get(clazz);
		return mapKeyEntity == null ? null : (T)mapKeyEntity.get(key);
	}
	
	public <T extends Entity> void delete(Class<T> clazz, String key){
		Map<String, Entity> mapKeyEntity = registryMap.get(clazz);
		if (mapKeyEntity != null) mapKeyEntity.remove(key);
	}

	private String generateKey() {
		return new BigInteger(100, random).toString(32);
	}
}