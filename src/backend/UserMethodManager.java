package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import commands.Command;
import commands.UserMethodCommand;

public class UserMethodManager extends Observable {

	private HashMap<String, UserMethod> myMethodMap;
	private HashMap<String, UserMethodCommand> myMethodCommandMap;

	public UserMethodManager() {
		myMethodMap = new HashMap<String, UserMethod>();
		myMethodCommandMap = new HashMap<String, UserMethodCommand>();
	}

	public void add(String key, UserMethod method, UserMethodCommand methodCommand) {
		myMethodMap.put(key, method);
		myMethodCommandMap.put(key, methodCommand);
		setChanged();
		notifyObservers(key);
	}
	
	//for testing
	public HashMap<String, UserMethodCommand> getCommandMap(){
		return myMethodCommandMap;
	}

	public UserMethod getUserMethod(String key) {
		UserMethod method = myMethodMap.get(key);
		return method;
	}

	/**
	 * Return the UserMethodCommand corresponding to the key
	 * @param key
	 * @return
	 */
	public UserMethodCommand getUserMethodCommand(String key) {
		UserMethodCommand methodCommand = myMethodCommandMap.get(key);
		System.out.println("In UserMethodManager: " +  myMethodCommandMap.toString());
		return methodCommand;
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
