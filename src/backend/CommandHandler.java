package backend;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import commands.Command;

public class CommandHandler {

	private Queue<Command> myCommands;
	private Command currentCommand;

	public CommandHandler() {
		// TODO Auto-generated constructor stub
		myCommands = new LinkedList<Command>();
	}

	public void addCommands(List<Command> lst) {
		for (Command c : lst) {
			myCommands.add(c);
		}
	}

	public void executeCommands() {
		currentCommand = myCommands.peek();
		if (currentCommand.isFinished()) {
			myCommands.remove();
			return;
		} else {
			currentCommand.executeCommand();
		}
	}

}
