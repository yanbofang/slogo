package backend;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import commands.Command;

public class CommandHandler {

	private Queue<Command> myCommands;
	private Command currentCommand;
	private VariableManager myVariables;
	private Turtle myTurtle;

	public CommandHandler(Turtle turtle, VariableManager vars) {
		myTurtle = turtle;
		myCommands = new LinkedList<Command>();
		myVariables = vars;
	}

	public void addCommands(List<Command> lst) {
		for (Command c : lst) {
			myCommands.add(c);
		}
	}

	/**
	 * Execute a single command -- assures we return our values for each command
	 * 
	 * @return
	 */
	public Double executeCommands() {
		Double current = null;
		if (!myCommands.isEmpty()) {
			currentCommand = myCommands.peek();
			if (currentCommand.isFinished()) {
				myCommands.remove();
			} else {
				current = currentCommand.executeCommand(myTurtle, myVariables);
				System.out.println(current + "   *print statement in CommandHandler, currentCommand is: " + currentCommand);
			}
		}
		return current;
	}

}
