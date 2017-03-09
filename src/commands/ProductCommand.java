package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class ProductCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public ProductCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		myValue = (Double) args.get(0) * (Double) args.get(1);
		System.out.println("MY PRODUCT VALUE " + myValue);
		return myValue;
	}


}
