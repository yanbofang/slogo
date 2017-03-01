package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import commands.Command;

/**
 * public class Parser {
 * 
 * private PatternParse myPatterns; private CommandFactory myFactory; private
 * List<Command> myCommands; private Model myModel; private VariableManager
 * myVariables; private UserMethodManager myUserMethods; private Turtle
 * myTurtle;
 * 
 * 
 * 
 * public Parser(String[] syntax, Model m, VariableManager variables,
 * UserMethodManager methods, Turtle turtle) { myModel = m; myPatterns = new
 * PatternParse(); for (String each : syntax) { myPatterns.addPattern(each); }
 * myVariables = variables; myUserMethods = methods; myFactory = new
 * CommandFactory(); myCommands = new ArrayList<Command>(); myTurtle = turtle; }
 * 
 * public List<Command> parse(String input) { myCommands.clear(); Scanner s =
 * new Scanner(input); while (s.hasNext()) { ArrayList<Command> current = new
 * ArrayList<Command>(); recurseParse(s, current); System.out.println(current);
 * myCommands.addAll(current); } System.out.println("In Parser: " + myCommands);
 * return myCommands; }
 **
 * 
 * Pass in a single instruction, iterate through the scanner recursively until
 * the command is completed
 * 
 * @param s
 *            - scanner of the input
 * @param currentList
 *            - list of commands to be added to
 * @return - returns an object depending on the the command/value
 * 
 *         private Object recurseParse(Scanner s, List<Command> currentList) {
 * 
 *         Command currentCommand;
 * 
 *         if (s.hasNext()) { String current = s.next(); // Creates the actual
 *         command (i.e. movement, math) // from the user input translation
 *         (i.e. sum, forward) currentCommand =
 *         myFactory.reflectCommand(myPatterns.getSymbol(current), myVariables,
 *         myUserMethods); //System.out.println(current); if (currentCommand !=
 *         null) { for (int k = 0; k < currentCommand.getNumOfExpressions();
 *         k++) { Object toBeAdded = recurseParse(s, currentList); if (toBeAdded
 *         == null) { toBeAdded = recurseParse(s, currentList); }
 *         currentCommand.add(toBeAdded); } currentList.add(currentCommand);
 *         return currentCommand.getValue(myTurtle); } else { return
 *         getDataObject(current, currentList, s); } } else { throw new
 *         ParserException(String.format("NOT ENOUGH ARGUMENTS FOR COMMAND %s",
 *         currentList.get(currentList.size() - 1).getInstruction())); } }
 * 
 *         private Object getDataObject(String current, List<Command>
 *         currentList, Scanner s) { System.out.println("HEllo");
 *         System.out.println(current); if (myModel.getMethodVariable(current)
 *         != null) { List<Command> methodList =
 *         myModel.getMethodVariable(current); currentList.addAll(methodList);
 *         return methodList; } else if
 *         (myPatterns.getSymbol(current).equals("Variable")) { return current;
 *         } else if (myPatterns.getSymbol(current).equals("ListStart")) {
 *         ArrayList<Command> sublist = getSubList(s);
 *         System.out.println("HERE"); System.out.println(sublist); return
 *         sublist; } else if (myPatterns.getSymbol(current).equals("ListEnd"))
 *         { System.out.println("GOTHERE"); return current; } try { return
 *         Double.parseDouble(current); } catch (Exception e) { throw new
 *         ParserException(String.format("NOT A VALID TYPE %s", current)); } }
 * 
 *         private ArrayList<Command> getSubList(Scanner s) { ArrayList<Command>
 *         subList = new ArrayList<Command>(); Object current; while
 *         (s.hasNext()) { current = recurseParse(s, subList); try { String
 *         check = (String) current; if
 *         (myPatterns.getSymbol(check).equals("ListEnd")) { return subList; } }
 *         catch (Exception e) { continue; } } throw new
 *         ParserException(String.format("NO LIST END CHARACTER")); }
 * 
 *         }
 **/

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
			Object currentCommand = recurseParse(s);
			try {
				current.add((Command) currentCommand);
			} catch (Exception e) {
				try {
					current.addAll((List<Command>) currentCommand);
				} catch (Exception f) {
					throw new ParserException(String.format("NOT VALID INPUT: %s", input));
				}
			}
			System.out.println(current);
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
			currentCommand = myFactory.reflectCommand(myPatterns.getSymbol(current), myVariables, myUserMethods);
			// System.out.println(current);
			if (currentCommand != null) {
				for (int k = 0; k < currentCommand.getNumOfExpressions(); k++) {
					Object toBeAdded = recurseParse(s);
					currentCommand.add(toBeAdded);
				}
				return currentCommand;
			} else {
				return getDataObject(current, s);
			}
		} else {
			throw new ParserException(String.format("Improper input"));
		}
	}

	private Object getDataObject(String current, Scanner s) {
		if (myModel.getMethodVariable(current) != null) {
			List<Command> methodList = myModel.getMethodVariable(current);
			// System.out.println("here!!!!!!" + methodList);
			// methodList.addAll(methodList);
			return methodList;
		} else if (myPatterns.getSymbol(current).equals("Variable")) {
			return current;
		} else if (myPatterns.getSymbol(current).equals("ListStart")) {
			ArrayList<Command> sublist = new ArrayList<Command>();
			sublist = getSubList(s);
			return sublist;
		} else if (myPatterns.getSymbol(current).equals("ListEnd")) {
			return current;
		}
		try {
			return Double.parseDouble(current);
		} catch (Exception e) {
			throw new ParserException(String.format("NOT A VALID TYPE %s", current));
		}
	}

	private ArrayList<Command> getSubList(Scanner s) {
		ArrayList<Command> subList = new ArrayList<Command>();
		Object current;
		while (s.hasNext()) {
			current = recurseParse(s);

			try {
				String check = (String) current;
				if (myPatterns.getSymbol(check).equals("ListEnd")) {
					return subList;
				}
			} catch (Exception e) {
				subList.add((Command) current);
			}
		}
		throw new ParserException(String.format("NO LIST END CHARACTER"));
	}

}
