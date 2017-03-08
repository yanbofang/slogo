package commands;

import java.util.ArrayList;
import java.util.List;

import turtles.TurtleManagerCommandAPI;
import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;

public class DoTimesCommand extends LoopCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public DoTimesCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		runAllTurtles = true;
	}

	public Double getValue(List<Object> args, VariableManager vars) {
		myListCommand = (Command) args.get(1);
		List<Object> lst = (List<Object>) args.get(0);
		Variable var = new Variable(((Command) lst.get(0)).getInstruction(), 1.0);
		myVariables.addVariable(var);
		return runCommands(1.0, ((Command) lst.get(1)).executeCommand(myTurtleManager, vars, myTurtle.getID()), 
				1.0, var, vars, myTurtle.getID());
	}

	@Override
	public Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double k) {
		myTurtleManager = turtles;
		myTurtle = turtles.getTurtle(k);
		myConvertedArguments = new ArrayList<Object>();
		myConvertedArguments.add(myArguments.get(0).getAllArguments());
		myConvertedArguments.add(myArguments.get(1));
		this.changeToFinished();
		return this.getValue(myConvertedArguments, vars);
	}

}
