package frontend.API;

import javafx.scene.Parent;

/**
 * Essential Methods for every subcomponent in the user interface
 * Any subcomponent should at least implement this
 * @author Gordon
 *
 */
public interface SubcomponentAPI {
	/**
	 * Returns the node that represents the subcomponent
	 * @return
	 */
	public Parent getParent();
}
