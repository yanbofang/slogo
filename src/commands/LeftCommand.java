package commands;

import backend.VariableManager;


public class LeftCommand extends MoveCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 1;
	
	public LeftCommand(String instruction, VariableManager manager) {
		super(instruction, manager, NUM_OF_EXPRESSIONS);
	}
	
	public Double calculateValue() {
		return left();
	}
	
	private Double left() {
		Double degrees = (Double) myArguments.get(0);
		myTurtle.getImage().setRotate(myTurtle.getImage().getRotate() - degrees);
		return degrees;
	}

}
