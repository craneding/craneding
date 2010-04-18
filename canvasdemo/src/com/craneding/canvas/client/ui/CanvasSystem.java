/**
 * 
 */
package com.craneding.canvas.client.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @author 丁丁
 * 
 */
public class CanvasSystem {

	private final static Map<String, String> cache = new HashMap<String, String>();
	private final static Vector<ChangeListenter> cls = new Vector<ChangeListenter>();

	public static void setProperty(String key, String value) {
		String oldVal = cache.get(key);
		cache.put(key, value);
		if(oldVal != value) {
			for (ChangeListenter listenter : cls) {
				listenter.onChange(key, oldVal, value);
			}
		}
	}

	public static String getProperty(String key) {
		return cache.get(key);
	}
	
	public static void addListener(ChangeListenter changeListenter){
		cls.add(changeListenter);
	}
	
	public static void removeListener(ChangeListenter changeListenter){
		cls.remove(changeListenter);
	}
	
	public static interface ChangeListenter {
		void onChange(String name, String oldVal, String newVal);
	}
}
