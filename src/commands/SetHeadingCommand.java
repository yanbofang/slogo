package commands;

import backend.VariableManager;


public class SetHeadingCommand extends MoveCommand {
	
	private static final Integer NUM_OF_EXPRESSIONS = 1;

	public SetHeadingCommand(String instruction, VariableManager manager) {
		super(instruction, manager, NUM_OF_EXPRESSIONS);
	}
	
	public Double calculateValue() {
		return setHeading();
	}
	
	private Double setHeading() {
		Double degrees = (Double) myArguments.get(0);
		Double difference = myTurtle.getRotate() - degrees;
		myTurtle.setRotate(degrees);
		return difference;
	}

}
