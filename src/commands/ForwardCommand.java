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
		System.out.println("SETTING FUTURE");
		System.out.println(myCoord.getX());
		System.out.println(myCoord.getY());
		myTurtle.setFutureLocation(myCoord, false);
		return movement;
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		myTurtle.setLocation(myCoord, false);
		return myValue;
	}

}
