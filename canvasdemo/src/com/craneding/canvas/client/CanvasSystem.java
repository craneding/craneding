/**
 * 
 */
package com.craneding.canvas.client;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 丁丁
 * 
 */
public class CanvasSystem {

	private final static Map<String, String> cache = new HashMap<String, String>();

	public static void setProperty(String key, String value) {
		cache.put(key, value);
	}

	public static String getProperty(String key) {
		return cache.get(key);
	}
}
