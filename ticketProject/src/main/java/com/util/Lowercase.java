package com.util;

import java.util.HashMap;

@SuppressWarnings("serial")
public class Lowercase extends HashMap<String , Object> {

	@Override
	public Object put(String key, Object value) {
		return super.put(key.toLowerCase(), value);
	}
	// super.put 이 오브젝트라서 String 으로 전달하면 안됨
	@Override
	public Object get(Object key) {
		return super.get(((String)key).toLowerCase());
	}
	
}
