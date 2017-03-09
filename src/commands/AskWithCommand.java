package commands;

import java.util.ArrayList;
import java.util.List;

import turtles.Turtle;
import turtles.TurtleManagerCommandAPI;
import backend.UserMethodManager;
import backend.VariableManager;

public class AskWithCommand extends LoopCommand {

	private static final int NUM_OF_EXPRESSIONS = 2;

	public AskWithCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		myListCommand = (Command) args.get(1);
		Command condition = ((Command) args.get(0));
		List<Double> possibleTurtles = getPossibleTurtles(condition, vars);
		Double returnValue = 0.0;
		for (Double d : possibleTurtles) {
			returnValue = runCommands(1.0, 1.0, 1.0, null, vars, d);
		}
		return returnValue;
	}

	@Override
	public Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double k) {
		myTurtleManager = turtles;
		myTurtle = turtles.getTurtle(k);
		myConvertedArguments = new ArrayList<Object>();
		myConvertedArguments.add(myArguments.get(0));
		myConvertedArguments.add(myArguments.get(1));
		this.changeToFinished();
		return this.getValue(myConvertedArguments, vars);
	}

	private List<Double> getPossibleTurtles(Command condition, VariableManager vars) {
		List<Double> myIDList = new ArrayList<Double>();
		for (Turtle t : myTurtleManager.allTurtles()) {
			if (condition.executeCommand(myTurtleManager, vars, t.getID()) == 1) {
				myIDList.add(t.getID());
			}
		}
		return myIDList;
	}

}
