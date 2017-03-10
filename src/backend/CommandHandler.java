package backend;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import turtles.Turtle;
import turtles.TurtleManager;
import commands.Command;

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

	// will need to change something in commands -- need to check to see if the
	// command runs with turtles
	public Double executeCommands() {
		Double current = null;
		if (!myCommands.isEmpty()) {
			currentCommand = myCommands.peek();
			List<Double> activeTurtles = myTurtles.getActiveTurtleIDs();
			System.out.println(currentCommand);
			System.out.println(currentCommand.getAllArguments());
			if (currentCommand.isFinished()) {
				myCommands.remove();
			} else if (currentCommand.isRunAllTurtles()) {
				for (Double k : activeTurtles) {
					current = currentCommand.executeCommand(myTurtles, myVariables, k);
					System.out.println(current + "   *print statement in CommandHandler");
				}
			} else {
				current = currentCommand.executeCommand(myTurtles, myVariables, activeTurtles.get(0));
			}
		}
		return current;
	}

}
