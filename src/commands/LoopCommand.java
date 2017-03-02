package commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;

public abstract class LoopCommand extends AbstractCommand {

	protected List<Command> myCommands;

	public LoopCommand(String instruction, VariableManager variables, UserMethodManager methods, int numOfExpressions) {
		super(instruction, variables, methods, numOfExpressions);
	}

	@Override
	public abstract Double getValue(List<Object> args);

	protected Double runCommands(Double start, Double end, Double increment, Variable var) {
		Double returnValue = 0.0;
		for (int i = start.intValue(); i < end.intValue() + 1; i += increment.intValue()) {
			if(var != null){
				myVariables.addVariable(new Variable(var.getVariableName(), (double) i));
			}
			for (Command c : myCommands) {
				c.resetCommand();
			}
			for (Command c : myCommands) {
				if (!c.isFinished()) {
					returnValue = c.executeCommand(myTurtle);
				}
			}
	
		}
		return returnValue;
	}
}
