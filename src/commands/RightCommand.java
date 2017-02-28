package commands;

public class RightCommand extends MoveCommand {

	public RightCommand(String instruction) {
		super(instruction);
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
