package commands;


import backend.UserMethodManager;
import backend.VariableManager;
import coordinate.Coordinate;

public class ForwardCommand extends MoveCommand {
	
	private static final Integer NUM_OF_EXPRESSIONS = 1;
	private Coordinate myCoord;

	public ForwardCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}
	
	public Double calculateValue() {
		Double movement = (Double) myArguments.get(0);
		myCoord = getNewCoord(movement);
		updateCoords(myCoord, myQuadrant);
		myTurtle.setFutureLocation(myCoord);
		return movement;
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		System.out.println(myTurtle.getLocation().getX());

		System.out.println(myTurtle.getLocation().getY());
		System.out.println(myCoord.getY());
		myTurtle.setLocation(myCoord);
		return myValue;
	}

}
