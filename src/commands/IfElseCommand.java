package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class IfElseCommand extends ConditionalCommand{
	
	private static final Integer NUM_OF_EXPRESSIONS = 3;

	public IfElseCommand(String instruction, VariableManager variables, UserMethodManager methods) {
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
		Double returnValue = 0.0;
		if((Double) myArguments.get(0) == 0.0){
			returnValue = execute(returnValue, (List<Command>) myArguments.get(2), true);
		}else{
			returnValue = execute(returnValue, (List<Command>) myArguments.get(1), true);
		}
		this.changeToFinished();
		return returnValue;
	}


}
