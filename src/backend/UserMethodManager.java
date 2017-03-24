package backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * This is the wrapper class for user method storage. It is essentially just a wrapper for a HashMap, 
 * which allows it to be easily passed around and generic. The hashmap maps a string (userMethod name) 
 * to the actual userMethod class that contains the variables and list of commands for that userMethod 
 * name
 * @author Henry and Yanbo
 *
 */
public class UserMethodManager extends Observable implements java.io.Serializable {

	private static final long serialVersionUID = -992355869776980965L;
	private HashMap<String, UserMethod> myMethodMap;

	public UserMethodManager() {
		myMethodMap = new HashMap<String, UserMethod>();
	}

	/**
	 * Add a key-value pair
	 * @param key - name of the userMethod
	 * @param method - userMethod for the key value
	 */
	public void add(String key, UserMethod method) {
		myMethodMap.put(key, method);
		setChanged();
		notifyObservers(key);
	}

	/**
	 * Used to add a all of the userMethods from a previous state (called by Controller). 
	 * @param methodMap - map of userMethod names to userMethods
	 */
	public void addAll(Map<String, UserMethod> methodMap) {
		if (methodMap == null)
			return;
		for (String key : methodMap.keySet()) {
			UserMethod newMethod = new UserMethod(key, methodMap.get(key).getListOfCommands(),
					methodMap.get(key).getListOfVariables());
			add(key, newMethod);
		}
	}

	/**
	 * 
	 * @param key - name of userMethod
	 * @return - return userMethod for that key
	 */
	public UserMethod getUserMethod(String key) {
		return myMethodMap.get(key);
	}

	/**
	 * @return entire map containing the userMethod name mapped to corresponding userMethod
	 */
	public Map<String, UserMethod> getMethodMap() {
		return myMethodMap;
	}

	/**
	 * Check if the method manager contains already contains the method
	 * 
	 * @param key - userMethod name
	 * @return true or false
	 */
	public boolean contains(String key) {
		return myMethodMap.containsKey(key);
	}
}
