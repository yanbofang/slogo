package commands;

import backend.Variable;
import backend.VariableManager;

public class MakeCommand extends AbstractCommand {

	private String myVariableName;
	private Variable myVariable;

	public MakeCommand(String instruction) {
		super(instruction);
		// TODO Auto-generated constructor stub
	}

	/***
	 * NEED TO ADD THE VARIABLE THE SECOND IT IS CALLED, IN CASE THE CALL USES THE VARIABLE LATER
	 */
	@Override
	public Double getValue() {
		myValue = (Double) myArguments.get(1);
		checkVariable();
		return null;
	}

	private void checkVariable() {
		String varName = (String) myArguments.get(0);
		VariableManager varMan = VariableManager.getInstance();
		if (varMan.get(varName) != null) {
			if (varMan.get(varName).getValue() == myValue) {
				return;
			}
		} else{
			myVariable = new Variable(varName, myValue);
			varMan.addVariable(myVariable);
		}
		
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		return myValue;
	}

}
