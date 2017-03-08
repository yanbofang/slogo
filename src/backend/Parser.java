package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import commands.Command;
import commands.ListCommand;
import commands.MakeUserInstructionCommand;
import commands.MakeVariableCommand;
import commands.UserMethodCommand;

public class Parser {

	private PatternParse myPatterns;
	private CommandFactory myFactory;
	private List<Command> myCommands;
	private Model myModel;
	private VariableManager myVariables;
	private UserMethodManager myUserMethods;
	private Command myCurrentCommand;

	public Parser(String[] syntax, Model m, VariableManager variables, UserMethodManager methods) {
		myModel = m;
		myPatterns = new PatternParse();
		for (String each : syntax) {
			myPatterns.addPattern(each);
		}
		myVariables = variables;
		myUserMethods = methods;
		myFactory = new CommandFactory();
		myCommands = new ArrayList<Command>();
	}

	public List<Command> parse(String input) {
		myCommands.clear();
		Scanner s = new Scanner(input);
		while (s.hasNext()) {
			Command currentCommand = recurseParse(s);
			myCommands.add(currentCommand);
		}
		return myCommands;
	}
	
	private Command reflect(String current) {
		Command currentCommand = myFactory.reflectCommand(current, myPatterns.getSymbol(current), myVariables, myUserMethods);
		return currentCommand;
	}


	/**
	 * Pass in a single instruction, iterate through the scanner recursively
	 * until the command is completed
	 * 
	 * @param s
	 *            - scanner of the input
	 * @param currentList
	 *            - list of commands to be added to
	 * @return - returns an object depending on the the command/value
	 */
	private Command recurseParse(Scanner s) {

		Command currentCommand;

		if (s.hasNext()) {
			String current = s.next();
			
			// Creates the actual command (i.e. movement, math)
			// from the user input translation (i.e. sum, forward)
			currentCommand = reflect(current);
			// System.out.println(current);
			if (currentCommand != null) {

				while (currentCommand.getCurrentArgumentSize() < currentCommand.getNumOfExpressions() ||
						currentCommand.getNumOfExpressions() < 0) {
					Command toBeAdded = recurseParse(s);
					if (toBeAdded == null) {
						break;
					}
					currentCommand.add(toBeAdded);
				}
				currentCommand.performBeforeExecution();
				return currentCommand;
			} else {
				return getDataObject(current, s);
			}
		} else {
			throw new ParserException(String.format("Improper input"));
		}
	}

	private Command getDataObject(String current, Scanner s) {
		if (myPatterns.getSymbol(current).equals("ListEnd")) {
			return null;
		} else if (myPatterns.getSymbol(current).equals("Comment")){
			s.nextLine();
			return recurseParse(s);
		} else {
			throw new ParserException(String.format("NOT A VALID TYPE %s", current));
		}
	}

	/*
	private ArrayList<Object> getSubList(Scanner s) {
		ArrayList<Object> subList = new ArrayList<Object>();
		Object current;
		while (s.hasNext()) {
			current = recurseParse(s);
			try {
				String check = (String) current;
				if (myPatterns.getSymbol(check).equals("ListEnd")) {
					return subList;
				} else if (myPatterns.getSymbol(check).equals("Variable")) {
					subList.add(current);
				}
			} catch (Exception e) {
				subList.add(current);
			}
		}
		throw new ParserException(String.format("NO LIST END CHARACTER"));
	}
	*/

}
