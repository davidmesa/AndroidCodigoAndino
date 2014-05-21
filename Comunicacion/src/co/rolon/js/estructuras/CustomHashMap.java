package co.rolon.js.estructuras;

import java.util.HashMap;

public class CustomHashMap {
	private HashMap<String, String> hash;
	
	public CustomHashMap() {
		hash = new HashMap<String, String>();
	}
	
	public CustomHashMap put(String key, String value) {
		hash.put(key, value);
		return this;
	}
	
	public String toString() {
		StringBuilder params = new StringBuilder();
		for(String k : hash.keySet()) {
			params.append(k).append("=").append(hash.get(k)).append("&");
		}
		return params.substring(0, params.length() - 2);
	}
}
