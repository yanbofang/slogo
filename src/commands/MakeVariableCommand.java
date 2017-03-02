package commands;

import java.util.List;

import backend.Turtle;
import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;

public class MakeVariableCommand extends AbstractCommand {

	private String myVariableName;
	private Variable myVariable;
	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public MakeVariableCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		// TODO Auto-generated constructor stub
	}

	/***
	 * NEED TO ADD THE VARIABLE THE SECOND IT IS CALLED, IN CASE THE CALL USES
	 * THE VARIABLE LATER
	 */
	@Override
	public Double getValue(List<Object> args) {
		myValue = (Double) args.get(1);
		checkVariable();
		return myValue;
	}

	private void checkVariable() {

		//System.out.println(myArguments.get(0));

		System.out.println(myValue);
		String varName = (String) myArguments.get(0);
		if (myVariables.get(varName) != null) {
			if (myVariables.get(varName).getValue() != myValue) {
				myVariables.addVariable(new Variable(varName, myValue));
				return;
			}
		} else {
			myVariable = new Variable(varName, myValue);
			myVariables.addVariable(myVariable);
		}
	}

}
