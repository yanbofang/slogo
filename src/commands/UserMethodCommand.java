package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;

public class UserMethodCommand extends AbstractCommand {

	public UserMethodCommand(String instruction, VariableManager variables, UserMethodManager methods,
			int numOfExpressions) {
		super(instruction, variables, methods, numOfExpressions);
	}

	@Override
	public Double getValue(List<Object> args) {
		Double returnValue = 0.0;
		// Create the variables
<<<<<<< HEAD
		System.out.println("!     " + myInstruction + " NUM "+ this.getNumOfExpressions());
		for (int i = 0; i < this.getNumOfExpressions(); i++) {
=======
		System.out.println(myInstruction);
		System.out.println(args);
		System.out.println(args.size());
		for (int i = 0; i < args.size(); i++) {
>>>>>>> eaea47f80dbeb5f6c385572c2c9b4f25a799d3cb
			myVariables.addVariable(
					new Variable(myUserMethods.getUserMethod(myInstruction).getListOfVariables().get(i), (Double) args.get(i)));
		}

		List<Command> commands = myUserMethods.getUserMethod(myInstruction).getListOfCommands();
		System.out.println(commands);
		for (Command c : commands) {
			c.resetCommand();
			while (!c.isFinished()) {
				returnValue = c.executeCommand(myTurtle);
			}
		}
		return returnValue;
	}

}
