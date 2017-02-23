package backend;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import commands.Command;

public class CommandHandler {

	private Queue<Command> myCommands;
	private Command currentCommand;
	//private Model myModel;
	private Turtle myTurtle;
	
	public CommandHandler(Turtle turtle) {
		// TODO Auto-generated constructor stub
		myTurtle = turtle;
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
	public Double executeCommands() {
		Double current = null;
		if (!myCommands.isEmpty()) {
			/*
			currentCommand = myCommands.peek();
			if (currentCommand.isFinished()) {
				myCommands.remove();
			} else {
				current = currentCommand.execute(myTurtle);
				System.out.println(current + "   *print statement in CommandHandler");
			}*/
			currentCommand = myCommands.poll();
			current = currentCommand.execute(myTurtle);
			System.out.println(current + "   *print statement in CommandHandler");
		}
		return current;
	}
	
}
