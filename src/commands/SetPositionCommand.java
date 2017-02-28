package commands;


import backend.VariableManager;
import coordinate.Coordinate;

public class SetPositionCommand extends MoveCommand {
	
	private static final Integer NUM_OF_EXPRESSIONS = 2;
	private Coordinate myCoord;
	private Double myDistance;

	public SetPositionCommand(String instruction, VariableManager manager) {
		super(instruction, manager, NUM_OF_EXPRESSIONS);
	}
	
	@Override
	public Double calculateValue() {
		Double updatedX = (Double) myArguments.get(0);
		Double updatedY = (Double) myArguments.get(1);
		myCoord = new Coordinate(updatedX, updatedY);
		myDistance = calcDistance(myCoord, myTurtle.getFutureLocation());
		myTurtle.setFutureLocation(myCoord);
		return myDistance;
	}
	
	public Double executeCommand() {
		myTurtle.setLocation(myCoord);
		return myDistance;
		
	}


}