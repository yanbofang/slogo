package commands;


import coordinate.Coordinate;

public class SetPositionCommand extends MoveCommand {

	public SetPositionCommand(String instruction) {
		super(instruction);
	}
	
	public Double calculateValue() {
		return setPosition();
	}
	
	private Double setPosition() {
		Double updatedX = (Double) myArguments.get(0);
		Double updatedY = (Double) myArguments.get(1);
		Coordinate coord = new Coordinate(updatedX, updatedY);
		Double distance = calcDistance(coord, new Coordinate(myTurtle.getX(), myTurtle.getY()));
		moveTurtle(coord);
		return distance;
		
	}

}
