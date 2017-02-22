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
	
	public Parser(String[] syntax, Model m) {
		myModel = m;
		myPatterns = new PatternParse();
		for (String each: syntax) {
			myPatterns.addPattern(each);
		}
		myFactory = new CommandFactory("resources/languages/Commands");
		myCommands = new ArrayList<Command>();
	}
	
	public List<Command> parse(String input) {
		myCommands.clear();
		Scanner s = new Scanner(input);
		while (s.hasNext()) {
			recurseParse(s);
		}
		return myCommands;
	}
	
	private Double recurseParse(Scanner s) {
		
		Command currentCommand;
		
		if (s.hasNext()) {
			String current = s.next();
			currentCommand = myFactory.reflectCommand(myPatterns.getSymbol(current));
			if (currentCommand != null) {
				myCommands.add(currentCommand);
				for (int k = 0; k < currentCommand.getNumberExpressions(); k++) {
					Double curr = recurseParse(s);
					currentCommand.add(curr);
				}
				return currentCommand.getValue();
			} else {
				if (myModel.getVariable(current) != null) {
					return myModel.getVariable(current);
				}
				return Double.parseDouble(current);
			}
		} else {
			throw new ParserException(String.format("NOT ENOUGH ARGUMENTS FOR COMMAND %s", 
					myCommands.get(myCommands.size()-1).getInstruction()));
		}
	}
	
	/*
	private Command generateCommand() {
		return new Command();
	}
	*/

}
