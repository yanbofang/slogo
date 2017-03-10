package commands;

import java.util.ArrayList;
import java.util.List;

import turtles.TurtleManagerCommandAPI;
import backend.ParserException;
import backend.UserMethodManager;
import backend.VariableManager;

public class TellCommand extends AbstractCommand {

	private static final int NUM_OF_EXPRESSIONS = 1;

	public TellCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		ArrayList<Double> newActiveTurtles = new ArrayList<Double>();
		for (Object o : (List<Object>) args) {
			try {
				newActiveTurtles.add((Double) o);
			} catch (Exception e) {
				throw new ParserException(String.format("NOT A VALID TURTLE ID %s", o));
			}
		}
		getTurtleManager().addActiveTurtles(newActiveTurtles);
		return newActiveTurtles.get(newActiveTurtles.size() - 1);
	}
	
	@Override
	protected ArrayList<Object> argumentsToConvert(VariableManager vars) {
		ArrayList<Object> convArgs = convertArguments(getArguments().get(0).getAllArguments(), vars, true);
		return convArgs;
	}


}
