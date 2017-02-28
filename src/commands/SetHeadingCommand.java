package commands;


public class SetHeadingCommand extends MoveCommand {

	public SetHeadingCommand(String instruction) {
		super(instruction);
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
