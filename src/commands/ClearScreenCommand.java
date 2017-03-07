package commands;

import java.util.List;

import coordinate.Coordinate;
import backend.UserMethodManager;
import backend.VariableManager;

public class ClearScreenCommand extends MoveCommand {
	
	private static final Integer NUM_OF_EXPRESSIONS = 0;
	private Coordinate myCoord;

	public ClearScreenCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		runAllTurtles = true;
	}

	@Override
	public Double calculateValue(List<Object> args) {
		myCoord = toHome();
		myTurtle.setClear(true);
		return myValue;
	}


}
