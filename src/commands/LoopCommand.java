package commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import backend.UserMethodManager;
import backend.VariableManager;

public abstract class LoopCommand extends AbstractCommand{

	protected List<Command> myCommands;

	public LoopCommand(String instruction, VariableManager variables, UserMethodManager methods, int numOfExpressions) {
		super(instruction, variables, methods, numOfExpressions);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract Double getValue(List<Object> args);
	
	protected Double runCommands(Double start, Double end, Double increment) {
		Double returnValue = 0.0;
//		Queue<Command> tempCommands = new LinkedList<Command>();
//		tempCommands.addAll(myCommands);
		for (int i = (int) (start - 1); i < end; i++) {
			// myCommands.addAll(tempCommands);
			for(Command c: myCommands){
				c.resetCommand();
			}
			for (Command c : myCommands) {
				if (!c.isFinished()) {
					returnValue = c.executeCommand(myTurtle);
				}
			}
			/*
			 * while (!myCommands.isEmpty()) { Command currentCommand =
			 * myCommands.peek(); if (!currentCommand.isFinished()) {
			 * currentCommand.executeCommand(myTurtle);
			 * System.out.println(currentCommand + "THIS IS IN REPEAT COMMAND");
			 * 
			 * } returnValue = currentCommand.getValue(myTurtle);
			 * myCommands.remove(); }
			 */
		}
		return returnValue;
	}

}
