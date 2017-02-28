package commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import backend.CommandFactory;
import backend.VariableManager;

public class RepeatCommand extends AbstractCommand{

	private static final Integer NUM_OF_EXPRESSIONS = 2;
	private CommandFactory myCommandFactory;
	private Queue<Command> myCommands;
	

	
	public RepeatCommand(String instruction, VariableManager variables) {
		super(instruction, variables, NUM_OF_EXPRESSIONS);
		myCommandFactory = new CommandFactory();
	}
	
	@Override
	public Double getValue() {
		ArrayList<Command> commandList = (ArrayList<Command>) myArguments.get(1);
		myCommands = new LinkedList<Command>();
		myCommands.addAll(commandList);
		//Haven't tested, myTurtle might be null if there is an error?
		return (commandList.isEmpty()) ? 0.0 : commandList.get(commandList.size()-1).getValue(myTurtle);
	}

	@Override
	public Double executeCommand() {
		// TODO Auto-generated method stub
		Double returnValue = 0.0;
		if(!myCommands.isEmpty()){
			Command currentCommand = myCommands.peek();
			if(!currentCommand.isFinished()){
				currentCommand.executeCommand();
			}
			returnValue = currentCommand.getValue(myTurtle);
			myCommands.remove();
		}
		return returnValue;
	}

}
