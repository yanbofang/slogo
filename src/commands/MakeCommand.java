package commands;

import backend.Variable;
import backend.VariableManager;

public class MakeCommand extends Command {

	private String myVariableName;
	private Double myValue;
	private Variable myVariable;

	public MakeCommand(String instruction) {
		super(instruction);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Double getValue() {
		// TODO Auto-generated method stub
		myValue = (Double) myArguments.get(1);
		return myValue;
	}

	@Override
	public void executeCommand() {
		myVariable = new Variable((String) myArguments.get(0), (Double) myArguments.get(1));
		VariableManager.getInstance().addVariable(myVariable);
		this.changeToFinished();
	}

}
