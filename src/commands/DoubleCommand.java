package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class DoubleCommand extends AbstractCommand{

	private static final Integer NUM_OF_EXPRESSIONS = 0;

	public DoubleCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double getValue(List<Object> args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
