package backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class VariableManager extends Observable implements java.io.Serializable {

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
		setChanged();
		notifyObservers(var.getVariableName() + " " + var.getValue());
	}

	public Map<String, Variable> getVariableMap() {
		return myVariableMap;
	}

	public void addAll(Map<String, Variable> variableMap) {
		for (String key : variableMap.keySet()) {
			Variable newVar = new Variable(key, variableMap.get(key).getValue());
			myVariableMap.put(key, newVar);
		}
	}

	public Variable get(String key) {
		Variable value = myVariableMap.get(key);
		while (myVariableMap.containsKey(value)) {
			value = myVariableMap.get(value);
		}
		return value;
	}

	public int size() {
		return myVariableMap.size();
	}

}
