package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class ConstantCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 0;

	public ConstantCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args) {
		return Double.parseDouble(myInstruction);
	}

	@Override
	public void performBeforeExecution() {
		return;		
	}

}
