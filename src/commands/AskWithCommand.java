package commands;

import java.util.ArrayList;
import java.util.List;

import turtles.Turtle;
import turtles.TurtleManagerCommandAPI;
import backend.UserMethodManager;
import backend.VariableManager;

public class AskWithCommand extends LoopCommand{

	private static final int NUM_OF_EXPRESSIONS = 2;
	public AskWithCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}
	@Override
	public Double getValue(List<Object> args) {
		myCommands = (ArrayList<Command>) args.get(1);
		Command condition = ((List<Command>) args.get(0)).get(0);
		List<Double> possibleTurtles = getPossibleTurtles(condition);
		Double returnValue = 0.0;
		for (Double d : possibleTurtles ){
			returnValue = runCommands(1.0, 1.0, 1.0, null, d);
		}
		return returnValue;
	}

	@Override
	public Double executeCommand(TurtleManagerCommandAPI turtles, Double k) {
		myTurtleManager = turtles;
		myTurtle = turtles.getTurtle(k);
		myConvertedArguments = new ArrayList<Object>();
		myConvertedArguments.add(convertArguments((List<Object>)myArguments.get(0), true));
		myConvertedArguments.add(convertArguments((List<Object>)myArguments.get(1), false));
		this.changeToFinished();
		return this.getValue(myConvertedArguments);
	}
	
	private List<Double> getPossibleTurtles(Command condition) {
		List<Double> myIDList = new ArrayList<Double>();
		for (Turtle t: myTurtleManager.allTurtles()) {
			if (condition.executeCommand(myTurtleManager, t.getID()) == 1) {
				myIDList.add(t.getID());
			}
		}
		return myIDList;
	}
	

}
