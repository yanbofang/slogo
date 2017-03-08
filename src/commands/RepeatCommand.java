package commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import turtles.TurtleManagerCommandAPI;
import backend.CommandFactory;
import backend.UserMethodManager;
import backend.VariableManager;

public class RepeatCommand extends LoopCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public RepeatCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		runAllTurtles = true;
	}

	public Double getValue(List<Object> args, VariableManager vars) {
		myListCommand = (Command) args.get(1);
		System.out.println("myCommands in RepeatCommand: " + myListCommand.getAllArguments());
		return runCommands(1.0, (Double) args.get(0), 1.0, null, vars, myTurtle.getID());
	}
}
