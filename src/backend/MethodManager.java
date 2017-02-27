package backend;

import java.util.HashMap;
import java.util.List;

import commands.Command;

public class MethodManager {

	private HashMap<String, List<Command>> myMethodMap;
	
	public MethodManager() {
		myMethodMap = new HashMap<String, List<Command>>();
	}
	
	public void add(String key, List<Command> value) {
		myMethodMap.put(key, value);
	}
	
	public List<Command> get(String key) {
		List<Command> value = myMethodMap.get(key);
		return value;
	}
}
