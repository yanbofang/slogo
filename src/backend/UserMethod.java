package backend;

import commands.Command;
import java.util.List;
/**
 * The wrapper class for a user method. Every make command creates a UserMethod that is then
 * stored in UserMethodManager. This class wraps the method name, the list of commands that it runs,
 * and the local variables that it uses. 
 * @author Yanbo
 *
 */
public class UserMethod implements java.io.Serializable {
	
	private static final long serialVersionUID = 2573242562810160606L;
	private String myMethodName;
	private List<Command> myListOfCommands;
	private List<String> myListOfVariableNames;

	/**
	 * 
	 * @param name - name of UserMethod
	 * @param commandsList - list of commands executed on userMethod call
	 * @param variableNameList - list of localVariables
	 */
	public UserMethod(String name, List<Command> commandsList, List<String> variableNameList) {
		myMethodName = name;
		myListOfCommands = commandsList;
		myListOfVariableNames = variableNameList;
	}

	/**
	 * @return String of userMethod name
	 */
	public String getMethodName() {
		return myMethodName;
	}

	/**
	 * @return List of Commands to execute on userMethod call
	 */
	public List<Command> getListOfCommands() {
		return myListOfCommands;
	}

	/**
	 * @return List of all relevant variables 
	 */
	public List<String> getListOfVariables() {
		return myListOfVariableNames;
	}
}
