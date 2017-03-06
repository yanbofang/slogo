package frontend.API;

import javafx.scene.Parent;

/**
 * API for the prompt subcomponent
 * The graphical area that contains the prompt display and command history
 * @author Faith
 */
public interface PromptViewAPI {
	
	/**
	 * Returns the node that represents the subcomponent
	 * @return
	 */
	public Parent getParent();

	/**
	 * Clears the history of commands from the screen
	 */
	public void clearHistory();
}