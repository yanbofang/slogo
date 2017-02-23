package backend;

import java.util.HashMap;

public class VariableManager {

	private HashMap<String, Variable> myVariableMap;

	public VariableManager() {
		myVariableMap = new HashMap<String, Variable>();
	}

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

}
