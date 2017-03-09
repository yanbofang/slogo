package commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import turtles.TurtleManagerCommandAPI;
import backend.CommandFactory;
import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;

public class RepeatCommand extends LoopCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;
	private static final String REPEAT_VARIABLE_NAME = ":repcount";

	public RepeatCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		runAllTurtles = true;
	}

	@Override
	protected Double calculate(List<Object> args, VariableManager vars) {
		Variable var = new Variable(REPEAT_VARIABLE_NAME, 1.0);
		vars.addVariable(var);
		return runCommands(1.0, (Double) args.get(0), 1.0, var, vars, myTurtle.getID());
	}
	
	@Override
	public Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double k) {
		myTurtleManager = turtles;
		localVariables = new VariableManager();
		localVariables.addAll(vars.getVariableMap());
		myTurtle = turtles.getTurtle(k);
		myConvertedArguments = new ArrayList<Object>();
		myConvertedArguments.add(myArguments.get(0).executeCommand(turtles, vars, k));
		myConvertedArguments.add(myArguments.get(1));
		this.changeToFinished();
		return this.getValue(myConvertedArguments, localVariables);
	}

}
