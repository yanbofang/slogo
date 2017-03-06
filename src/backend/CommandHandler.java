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
	private TurtleManager myTurtles;
	
	public CommandHandler(TurtleManager turtle) {
		myTurtles = turtle;
		myCommands = new LinkedList<Command>();
	}

	public void addCommands(List<Command> lst) {
		for (Command c : lst) {
			myCommands.add(c);
		}
	}

	/**
	 * Execute a single command -- assures we return our values for each command
	 * @return
	 */
	
	//will need to change something in commands -- need to check to see if the command runs with turtles
	public Double executeCommands() {
		Double current = null;
		if (!myCommands.isEmpty()) {
			currentCommand = myCommands.peek();
			if (currentCommand.isFinished()) {
				myCommands.remove();
			} else {
				List<Double> activeTurtles = myTurtles.getActiveTurtleIDs();
				for (Double k: activeTurtles) {
					current = currentCommand.executeCommand(myTurtles, k);
					System.out.println(current + "   *print statement in CommandHandler");
				}
			}
		}
		return current;
	}
	
}
