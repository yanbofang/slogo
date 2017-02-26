package backend;

import java.util.HashMap;

public class VariableManager {

//	//Singleton design pattern
//	private static VariableManager instance;
	private HashMap<String, Variable> myVariableMap;

	public VariableManager() {
		myVariableMap = new HashMap<String, Variable>();
	}
	
//	public static synchronized VariableManager getInstance(){
//		if(instance == null) instance = new VariableManager();
//		return instance;
//	}

	public void addVariable(Variable var) {
		// if already existed, just update the variable
		if (myVariableMap.containsKey(var.getVariableName())) {
			myVariableMap.get(var.getVariableName()).setValue(var.getValue());
		} else {
			myVariableMap.put(var.getVariableName(), var);
		}
	}

	public Variable getVariable(String key) {
		return myVariableMap.get(key);
	}
	
	public int size(){
		return myVariableMap.size();
	}

}
