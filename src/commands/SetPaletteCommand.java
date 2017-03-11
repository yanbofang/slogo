package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class SetPaletteCommand extends AbstractCommand {

	private static final int NUM_OF_EXPRESSIONS = 4;
	public SetPaletteCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager localVariables) {
		int size = args.size();
		getTurtleManager().addColor((Double) args.get(size-4), (Double) args.get(size -3), 
				(Double) args.get(size -2), (Double) args.get(size -1));
		return (Double) args.get(size-4);
	}

}
