package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	public List<String> getInputString() {
		List<String> strList = new ArrayList<String>();
		for (String s : myVariableMap.keySet()) {
			strList.add(" " + s + " " + myVariableMap.get(s).getValue().toString());
		}
		return strList;
	}

	public void addAll(Map<String, Variable> variableMap) {
		if (variableMap == null)
			return;
		for (String key : variableMap.keySet()) {
			Variable newVar = new Variable(key, variableMap.get(key).getValue());
			// myVariableMap.put(key, newVar);
			addVariable(newVar);
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
