package commands;

import java.util.ArrayList;
import java.util.List;

import backend.ParserException;
import backend.UserMethodManager;
import backend.VariableManager;

public class TellCommand extends AbstractCommand{

	private static final int NUM_OF_EXPRESSIONS = 1;
	public TellCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}
	@Override
	public Double getValue(List<Object> args) {
		ArrayList<Double> newActiveTurtles = new ArrayList<Double>(); 
		for (Double o : (List<Double>) args.get(0)) {
			try {
				newActiveTurtles.add(o);
			} catch (Exception e) {
				throw new ParserException(String.format("NOT A VALID TURTLE ID %s", o));
			}
		}
		myTurtleManager.addActiveTurtles(newActiveTurtles);
		return newActiveTurtles.get(newActiveTurtles.size()-1);
	}

}
