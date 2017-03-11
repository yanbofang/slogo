package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class VariableCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9068230354576988445L;

	public VariableCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager localVariables) {
		if (localVariables.get(getInstruction()) == null) {
			return null;
		}
		return localVariables.get(getInstruction()).getValue();
	}

}
