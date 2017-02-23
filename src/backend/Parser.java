package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import commands.Command;

public class Parser {
	
	private PatternParse myPatterns;
	private CommandFactory myFactory;
	private List<Command> myCommands;
	private Model myModel;
	
	public Parser(String[] syntax, String commandProperty, Model m) {
		myModel = m;
		myPatterns = new PatternParse();
		for (String each: syntax) {
			myPatterns.addPattern(each);
		}
		myFactory = new CommandFactory(commandProperty);
		myCommands = new ArrayList<Command>();
	}
	
	public List<Command> parse(String input) {
		myCommands.clear();
		Scanner s = new Scanner(input);
		while (s.hasNext()) {
			ArrayList<Command> current = new ArrayList<Command>();
			recurseParse(s, current);
			myCommands.add(current.get(0));
		}
		return myCommands;
	}
	
	private Object recurseParse(Scanner s, List<Command> currentList) {
		
		Command currentCommand;
		
		if (s.hasNext()) {
			String current = s.next();
			
			//Creates the actual command (i.e. movement, math) 
			//from the user input translation (i.e. sum, forward)
			currentCommand = myFactory.reflectCommand(myPatterns.getSymbol(current));
			
			
			if (currentCommand != null) {
				currentList.add(currentCommand);
				for (int k = 0; k < currentCommand.getNumberExpressions(); k++) {
					currentCommand.add(recurseParse(s, currentList));
				}
				return currentCommand.getValue();
			} else {
				return getDataObject(current, currentList, s);
			}
		} else {
			throw new ParserException(String.format("NOT ENOUGH ARGUMENTS FOR COMMAND %s", 
					currentList.get(currentList.size()-1).getInstruction()));
		}
	}
	
	
	private Object getDataObject(String current, List<Command> currentList, Scanner s) {
		if (myModel.getVariable(current) != null) {
			return myModel.getVariable(current);
		} else if (myModel.getMethodVariable(current) != null) {
			List<Command> methodList = myModel.getMethodVariable(current);
			currentList.addAll(methodList);
			return methodList.get(0).getValue();
		} else if (myPatterns.getSymbol(current).equals("Variable")) {
			return current;
		} else if (myPatterns.getSymbol(current).equals("ListStart")) {
			return recurseParse(s, new ArrayList<Command>());
		} else if (myPatterns.getSymbol(current).equals("ListEnd")) {
			return currentList;
		}
		return Double.parseDouble(current);
	}
	
	/*
	private Command generateCommand() {
		return new Command();
	}
	*/

}
