package commands;


public class LeftCommand extends MoveCommand {

	public LeftCommand(String instruction) {
		super(instruction);
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
