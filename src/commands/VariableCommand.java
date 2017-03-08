package commands;

import java.util.List;

import backend.ParserException;
import backend.UserMethodManager;
import backend.VariableManager;

public class VariableCommand extends AbstractCommand {

	public VariableCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager localVariables) {
		if (localVariables.get(myInstruction) == null ){
			return null;
		}
		return localVariables.get(myInstruction).getValue();
	}

}
