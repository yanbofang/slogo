package commands;

import backend.VariableManager;

public class RightCommand extends MoveCommand {
	
	private static final Integer NUM_OF_EXPRESSIONS = 1;

	public RightCommand(String instruction, VariableManager manager) {
		super(instruction, manager, NUM_OF_EXPRESSIONS);
	}
	
	public Double calculateValue() {
		return right();
	}
	
	private Double right() {
		Double degrees = (Double) myArguments.get(0);
		myTurtle.setRotate(myTurtle.getRotate() + degrees);
		return degrees;
	}
	
}
