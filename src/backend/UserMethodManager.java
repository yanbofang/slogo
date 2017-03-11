package backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class UserMethodManager extends Observable implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -992355869776980965L;
	private HashMap<String, UserMethod> myMethodMap;

	public UserMethodManager() {
		myMethodMap = new HashMap<String, UserMethod>();
	}

	public void add(String key, UserMethod method) {
		myMethodMap.put(key, method);
		setChanged();
		notifyObservers(key);
	}

	public void addAll(Map<String, UserMethod> methodMap) {
		if (methodMap == null)
			return;
		for (String key : methodMap.keySet()) {
			UserMethod newMethod = new UserMethod(key, methodMap.get(key).getListOfCommands(),
					methodMap.get(key).getListOfVariables());
			add(key, newMethod);
		}
	}

	public UserMethod getUserMethod(String key) {
		return myMethodMap.get(key);
	}

	public Map<String, UserMethod> getMethodMap() {
		return myMethodMap;
	}

	/**
	 * Check if the method manager contains already contains the method
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(String key) {
		return myMethodMap.containsKey(key);
	}
}
