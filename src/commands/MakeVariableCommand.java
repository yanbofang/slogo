package commands;

import java.util.ArrayList;
import java.util.List;

import turtles.Turtle;
import turtles.TurtleManagerCommandAPI;
import backend.UserMethod;
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
	public Double getValue(List<Object> args, VariableManager vars) {
		setValue((Double) args.get(1));
		checkVariable(vars);
		return getValue();
	}

	private void checkVariable(VariableManager vars) {
		String varName = (String) getArguments().get(0).getInstruction();
		if (vars.get(varName) != null) {
			if (vars.get(varName).getValue() != getValue()) {
				vars.addVariable(new Variable(varName, getValue()));
				return;
			}
		} else {
			myVariable = new Variable(varName, getValue());
			vars.addVariable(myVariable);
		}
	}
	/*
	public void performBeforeExecution() {
		String varName = (String) myArguments.get(0).getInstruction();
		if (myVariables.get(varName) != null) {
			if (myVariables.get(varName).getValue() != myValue) {
				myVariables.addVariable(new Variable(varName, myValue));
				return;
			}
		} else {
			myVariable = new Variable(varName, myValue);
			myVariables.addVariable(myVariable);
		}
		return;
	}*/
	
	public Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double k) {
		setTurtleManager(turtles);
		VariableManager localVariables = vars;
		setTurtle(turtles.getTurtle(k));
		setConvertedArguments(argumentsToConvert(localVariables));
		this.changeToFinished();
		return this.getValue(getConvertedArguments(), localVariables);
	}
	
	protected ArrayList<Object> argumentsToConvert(VariableManager vars) {
		ArrayList<Object> convArgs = new ArrayList<Object>();
		convArgs.add(getArguments().get(0));
		convArgs.addAll(convertArguments(getArguments().get(1), vars, true));
		return convArgs;
	}

	

}
