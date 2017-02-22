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
	
	public Parser(String[] syntax) {
		myPatterns = new PatternParse();
		/*
		for (String[] each:syntax) {
			myPatterns.addPattern(each);
		}
		*/
		myPatterns.addPattern("resources/languages/English");
		myPatterns.addPattern("resources/languages/Syntax");
		myFactory = new CommandFactory("resources/languages/Commands");
		myCommands = new ArrayList<Command>();
	}
	
	public List<Command> parse(String input) {
		myCommands.clear();
		recurseParse(new Scanner(input));
		System.out.println(myCommands);
		return myCommands;
	}
	
	private Object recurseParse(Scanner s) {
		Command currentCommand;
		if (s.hasNext()) {
			String current = s.next();
			currentCommand = myFactory.reflectCommand(myPatterns.getSymbol(current));

			System.out.println(currentCommand);
			if (currentCommand != null) {
				myCommands.add(currentCommand);
				for (int k = 0; k < currentCommand.getNumberExpressions(); k++) {
					currentCommand.add(recurseParse(s));
				}
				recurseParse(s);
				return currentCommand.getValue();
			} else {
				recurseParse(s);
				return current;
			}
		} else {
			return new Double(0);
		}
		/*
		List<Command> commandList = new ArrayList<Command>();
		Command currentCommand;
		while (s.hasNext()) {
			currentCommand = myFactory.reflectCommand(myPatterns.getSymbol(s.next()));
			//System.out.println(s.toString());
			if (currentCommand == null) {
				ArrayList<Object> arguments = new ArrayList<Object>();
				for (int k = 0; k < currentCommand.getNumberExpressions(); k++) {
					arguments.add()
				}
			} else {
				
			}
		}
		return new ArrayList<Command>();*/
	}
	
	/*
	private Command generateCommand() {
		return new Command();
	}
	*/

}
