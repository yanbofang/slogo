package commands;

import java.util.List;
import turtles.TurtleManagerCommandAPI;
import backend.VariableManager;

// This entire file is part of my masterpiece.
// Yanbo Fang
// This class is the API of the Commands, one of the most important ideas I learned during this project is the use of API. 
// A good API is the key to a good encapsulation, hiding implementation details of its concrete classes from the clients. 
// By abstracting the underlying implementation and only exposing objects or actions the developer needs, 
// this Command Interface provides its users with nine public methods that they would need, nothing more and nothing less.
// This masterpiece also aims to present my inheritance hierarchy in Commands, of which I drew a graph to illustrate in my Analysis. Command -> AbstractCommand -> LoopCommand -> DoTimesCommand

/**
 * The API of Command
 * 
 * @author Yanbo Fang
 * @author Henry Taylor
 */
public interface Command extends java.io.Serializable {
	/**
	 * Add all the command in args into myArguments
	 * 
	 * @param args
	 */
	void add(Command... args);

	/**
	 * Get the list of command in myArguments
	 * 
	 * @return a list of commands
	 */
	List<Command> getArguments();

	/**
	 * Execute the command, returns a double value
	 * 
	 * @param turtles
	 * @param vars
	 * @param turtleID
	 * @return Double value calculated in getValue
	 */
	Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double turtleID);

	/**
	 * Reset the command to its unfinished status
	 */
	void resetCommand();

	/**
	 * Get the number of expressions (parameters) for a command
	 * 
	 * @return Integer value of the number of parameters needed for a command
	 */
	Integer getNumOfExpressions();

	/**
	 * Get the name of the instruction
	 * 
	 * @return String
	 */
	String getInstruction();

	/**
	 * Check if a command has been executed
	 * 
	 * @return a boolean
	 */
	boolean isFinished();

	/**
	 * Check if needs to run all the turtles
	 * 
	 * @return a boolean
	 */
	boolean isRunAllTurtles();

	/**
	 * Perform the steps in the implementation before executing the command
	 */
	void performBeforeExecution();
}
