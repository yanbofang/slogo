package backend;

import java.util.HashMap;
import java.util.List;

import commands.Command;

public class UserMethodManager {

	private HashMap<String, UserMethod> myMethodMap;

	public UserMethodManager() {
		myMethodMap = new HashMap<String, UserMethod>();
	}

	public void add(String key, UserMethod method) {
		myMethodMap.put(key, method);
	}

	public UserMethod get(String key) {
		UserMethod method = myMethodMap.get(key);
		return method;
	}
}
