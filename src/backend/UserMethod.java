package backend;

import commands.Command;
import java.util.List;

public class UserMethod implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2573242562810160606L;
	private String myMethodName;
	private List<Command> myListOfCommands;
	private List<String> myListOfVariableNames;

	public UserMethod(String name, List<Command> commandsList, List<String> variableNameList) {
		myMethodName = name;
		myListOfCommands = commandsList;
		myListOfVariableNames = variableNameList;
	}

	public String getMethodName() {
		return myMethodName;
	}

	public List<Command> getListOfCommands() {
		return myListOfCommands;
	}

	public List<String> getListOfVariables() {
		return myListOfVariableNames;
	}
}
