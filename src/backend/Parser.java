package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import commands.Command;
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
			ArrayList<Command> current = new ArrayList<Command>();
			Object currentCommand = recurseParse(s);
			try {
				Command c =  (Command) currentCommand;
				current.add((Command) currentCommand);
			} catch (Exception e) {
				try {
					current.addAll((List<Command>) currentCommand);
				} catch (Exception f) {
					System.out.println(currentCommand);
					throw new ParserException(String.format("NOT VALID INPUT: %s", input));
				}
			}
			myCommands.addAll(current);
		}
		return myCommands;
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
	private Object recurseParse(Scanner s) {

		Command currentCommand;

		if (s.hasNext()) {
			String current = s.next();
			
			// Creates the actual command (i.e. movement, math)
			// from the user input translation (i.e. sum, forward)
			currentCommand = myFactory.reflectCommand(current, myPatterns.getSymbol(current), myVariables, myUserMethods);
			// System.out.println(current);
			if (currentCommand != null) {
				for (int k = 0; k < currentCommand.getNumOfExpressions(); k++) {
					Object toBeAdded = recurseParse(s);
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

	private Object getDataObject(String current, Scanner s) {
		if (myUserMethods.getUserMethod(current) != null) {
			UserMethod method = (UserMethod) myUserMethods.getUserMethod(current);
			UserMethodCommand methodCommand = new UserMethodCommand(current, myVariables, myUserMethods, 
					method);
			for (int k = 0; k < methodCommand.getNumOfExpressions(); k++) {
				Object toBeAdded = recurseParse(s);
				methodCommand.add(toBeAdded);
			}
			return methodCommand;
		} else if (myPatterns.getSymbol(current).equals("Variable")) {
			return current;
		} else if (myPatterns.getSymbol(current).equals("ListStart")) {
			ArrayList<Object> sublist = new ArrayList<Object>();
			sublist = getSubList(s);
			return sublist;
		} else if (myPatterns.getSymbol(current).equals("ListEnd")) {
			return current;
		} else if (myPatterns.getSymbol(current).equals("Comment")){
			s.nextLine();
			return recurseParse(s);
		} else {
			throw new ParserException(String.format("NOT A VALID TYPE %s", current));
		}
	}

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

}
