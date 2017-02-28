package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import commands.Command;

public class Parser {

	private PatternParse myPatterns;
	private CommandFactory myFactory;
	private List<Command> myCommands;
	private Model myModel;
	private VariableManager myVariables;
	private UserMethodManager myUserMethods;
	private Turtle myTurtle;

	

	public Parser(String[] syntax, Model m, VariableManager variables, UserMethodManager methods, Turtle turtle) {
		myModel = m;
		myPatterns = new PatternParse();
		for (String each : syntax) {
			myPatterns.addPattern(each);
		}
		myVariables = variables;
		myUserMethods = methods;
		myFactory = new CommandFactory();
		myCommands = new ArrayList<Command>();
		myTurtle = turtle;
	}

	public List<Command> parse(String input) {
		myCommands.clear();
		Scanner s = new Scanner(input);
		while (s.hasNext()) {
			ArrayList<Command> current = new ArrayList<Command>();
			recurseParse(s, current);
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
	private Object recurseParse(Scanner s, List<Command> currentList) {

		Command currentCommand;

		if (s.hasNext()) {
			String current = s.next();
			// Creates the actual command (i.e. movement, math)
			// from the user input translation (i.e. sum, forward)
			currentCommand = myFactory.reflectCommand(myPatterns.getSymbol(current), myVariables, myUserMethods);

			if (currentCommand != null) {
				for (int k = 0; k < currentCommand.getNumOfExpressions(); k++) {
					Object toBeAdded = recurseParse(s, currentList);
					if (toBeAdded == null) {
						toBeAdded = recurseParse(s, currentList);
					}
					currentCommand.add(toBeAdded);
				}
				currentList.add(currentCommand);
				return currentCommand.getValue(myTurtle);
			} else {
				return getDataObject(current, currentList, s);
			}
		} else {
			throw new ParserException(String.format("NOT ENOUGH ARGUMENTS FOR COMMAND %s",
					currentList.get(currentList.size() - 1).getInstruction()));
		}
	}

	private Object getDataObject(String current, List<Command> currentList, Scanner s) {
		if (myModel.getVariable(current) != null) {
			return myModel.getVariable(current);
		} else if (myModel.getMethodVariable(current) != null) {
			List<Command> methodList = myModel.getMethodVariable(current);
			System.out.println("here!!!!!!" + methodList);
			currentList.addAll(methodList);
			return methodList.get(0).getValue(myTurtle);
		} else if (myPatterns.getSymbol(current).equals("Variable")) {
			return current;
		} else if (myPatterns.getSymbol(current).equals("ListStart")) {
			ArrayList<Command> sublist = new ArrayList<Command>();
			recurseParse(s, sublist);
			return sublist;
		} else if (myPatterns.getSymbol(current).equals("ListEnd")) {
			System.out.println("In ListEnd: " + currentList);
			return currentList;
		}
		try {
			return Double.parseDouble(current);
		} catch (Exception e) {
			throw new ParserException(String.format("NOT A VALID TYPE %s", current));
		}
	}

}
