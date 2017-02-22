package interfaces;

import javafx.scene.Node;

public interface ModelInterface {

	
	/**
	 * Take in user Input and pass to Parser
	 * @param input - the String version of the user input
	 */
	public void handleInput(String input);
	
	
	/**
	 * Go to CommandHandler, and handle next command. 
	 * @return
	 */
	public String getNextPos();
	
	
	/**
	 * Update the variable in VariableManager
	 * @param var - variable name
	 * @param value - new value of variable
	 */
	public void updateVariable(String var, String value);
	
	/**
	 * Return turtle image for UI Scene
	 */
	public Node getTurtle();
}
