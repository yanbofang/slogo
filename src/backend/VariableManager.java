package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
/**
 * This class keeps track of all our current variables by mapping a String (the variable name) to 
 * its corresponding variable class. It is simply just a HashMap wrapper to allow the ease of passing the manager
 * around instead of a specific map. 
 * @author Henry and Yanbo
 *
 */
public class VariableManager extends Observable implements java.io.Serializable {
	
	private static final long serialVersionUID = -265641316491387581L;
	private HashMap<String, Variable> myVariableMap;

	/**
	 * Initialize variableMap with no entries
	 */
	public VariableManager() {
		myVariableMap = new HashMap<String, Variable>();
	}

	/**
	 * 
	 * @param var - the actual Variable to be added
	 */
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

	/**
	 * @return the full variable map
	 */
	public Map<String, Variable> getVariableMap() {
		return myVariableMap;
	}
	
	/**
	 * returns a list of all the variables in string form in the map -- useful for debugging
	 * @return a list of all the variables in string form
	 */
	public List<String> getInputString() {
		List<String> strList = new ArrayList<String>();
		for (String s : myVariableMap.keySet()) {
			strList.add(" " + s + " " + myVariableMap.get(s).getValue().toString());
		}
		return strList;
	}

	/**
	 * Adds all the variables in a given map
	 * @param variableMap - previous map of variables
	 */
	public void addAll(Map<String, Variable> variableMap) {
		if (variableMap == null)
			return;
		for (String key : variableMap.keySet()) {
			Variable newVar = new Variable(key, variableMap.get(key).getValue());
			// myVariableMap.put(key, newVar);
			addVariable(newVar);
		}
	}

	/**
	 * returns a variable given a specific variable name (key)
	 * @param key - variable name
	 * @return - variable
	 */
	public Variable get(String key) {
		Variable value = myVariableMap.get(key);
		while (myVariableMap.containsKey(value)) {
			value = myVariableMap.get(value);
		}
		return value;
	}

	/**
	 * @return number of total variables
	 */
	public int size() {
		return myVariableMap.size();
	}

}
