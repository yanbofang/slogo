package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class IfCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public IfCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double executeCommand() {
		// TODO Auto-generated method stub
		Double returnValue = 0.0;
		if ((Double) myArguments.get(0) == 1.0) {
			for (Command c : (List<Command>) myArguments.get(1)) {
				while (!c.isFinished()) {
					returnValue = c.executeCommand();
				}
			}
		}
		this.changeToFinished();
		return returnValue;
	}

}
