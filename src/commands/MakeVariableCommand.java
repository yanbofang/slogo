package commands;

import backend.Turtle;
import backend.Variable;
import backend.VariableManager;

public class MakeVariableCommand extends AbstractCommand {

	private String myVariableName;
	private Variable myVariable;
	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public MakeVariableCommand(String instruction, VariableManager variables) {
		super(instruction, variables, NUM_OF_EXPRESSIONS);
		// TODO Auto-generated constructor stub
	}

	/***
	 * NEED TO ADD THE VARIABLE THE SECOND IT IS CALLED, IN CASE THE CALL USES
	 * THE VARIABLE LATER
	 */
	@Override
	public Double getValue() {
		myValue = (Double) myArguments.get(1);
		checkVariable();
		return null;
	}

	private void checkVariable() {
		String varName = (String) myArguments.get(0);
		if (myVariables.get(varName) != null) {
			if (myVariables.get(varName).getValue() == myValue) {
				return;
			}
		} else {
			myVariable = new Variable(varName, myValue);
			myVariables.addVariable(myVariable);
		}
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		return myValue;
	}

}
