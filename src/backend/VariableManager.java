package backend;

import java.util.HashMap;

public class VariableManager {
	
	
	private HashMap<String,String> myVariableMap;
	
	
	public VariableManager() {
		myVariableMap = new HashMap<String, String>();
	}
	
	
	public void add(String key, String value) {
		myVariableMap.put(key, value);
	}

	public String get(String key) {
		String value = myVariableMap.get(key);
		while (myVariableMap.containsKey(value)) {
			value = myVariableMap.get(value);
		}
		return value;
	}
}
