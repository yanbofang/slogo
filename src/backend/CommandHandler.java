package backend;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import turtles.TurtleManager;
import commands.Command;


//This entire file is a part of my masterpiece
//Henry Taylor 

/**
 * This class is responsible for handling all of the commands when the controller tells the backend 
 * to process them. We use a queue to store and process commands in order as they are received.
 * I think this is good design because we delegate a class to only worry about the execution of commands
 * @author Henry
 *
 */
public class CommandHandler {

	private Queue<Command> myCommands;
	private Command currentCommand;
	private VariableManager myVariables;
	private TurtleManager myTurtles;

	public CommandHandler(TurtleManager turtle, VariableManager vars) {
		myTurtles = turtle;
		myCommands = new LinkedList<Command>();
		myVariables = vars;
	}

	/**
	 * Allows model to add more commands to execute
	 * @param lst - list of Commands to add to myCommands
	 */
	public void addCommands(Collection<Command> lst) {
		for (Command c : lst) {
			myCommands.add(c);
		}
	}

	/**
	 * Execute a single command -- assures we return our values for each command.
	 * Checks to see if command is a command that can be run by all turtles, if it is, it
	 * runs the command for all active turtles
	 * @return value of the executed command
	 */

	public Double executeCommands() {
		Double current = null;
		if (!myCommands.isEmpty()) {
			currentCommand = myCommands.peek();
			List<Double> activeTurtles = myTurtles.getActiveTurtleIDs();
			if (currentCommand.isFinished()) {
				myCommands.remove();
			} else if (currentCommand.isRunAllTurtles()) {
				for (Double k : activeTurtles) {
					current = currentCommand.executeCommand(myTurtles, myVariables, k);
				}
			} else {
				current = currentCommand.executeCommand(myTurtles, myVariables, activeTurtles.get(0));
			}
		}
		return current;
	}

}
