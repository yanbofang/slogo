package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;


public class LeftCommand extends MoveCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 1;
	
	public LeftCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}
	
	@Override
	public Double calculateValue(List<Object> args) {
		Double degrees = (Double) args.get(0);
		getTurtle().setRotate(getTurtle().getRotate() - degrees);
		return degrees;
	}

}
