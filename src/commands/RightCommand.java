package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class RightCommand extends MoveCommand {
	
	private static final Integer NUM_OF_EXPRESSIONS = 1;

	public RightCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}
	
	public Double calculateValue(List<Object> args) {
		args.stream()
			.forEach(d-> myTurtle.setRotate(myTurtle.getRotate() + (Double) d));
		return (Double) args.get(0);
	}
	
}
