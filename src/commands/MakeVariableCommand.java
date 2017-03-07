package commands;

import java.util.List;

import turtles.Turtle;
import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;

public class MakeVariableCommand extends AbstractCommand {

	private String myVariableName;
	private Variable myVariable;
	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public MakeVariableCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	/***
	 * NEED TO ADD THE VARIABLE THE SECOND IT IS CALLED, IN CASE THE CALL USES
	 * THE VARIABLE LATER
	 */
	@Override
<<<<<<< HEAD
	public Double getValue(List<Object> args, VariableManager vars) {
		System.out.println("makevar!!!!!!! " + args);
=======
	public Double getValue(List<Object> args) {
>>>>>>> ae34916c40d34c41e75bf348766f340649d34ff2
		myValue = (Double) args.get(1);
		checkVariable(vars);
		return myValue;
	}

	private void checkVariable(VariableManager vars) {
		System.out.println(myValue);
		String varName = (String) myArguments.get(0);
		if (vars.get(varName) != null) {
			if (vars.get(varName).getValue() != myValue) {
				vars.addVariable(new Variable(varName, myValue));
				return;
			}
		} else {
			myVariable = new Variable(varName, myValue);
			vars.addVariable(myVariable);
		}
	}

}
