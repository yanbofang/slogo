package frontend.API;

/**
 * API for the variables display subcomponent
 * @author Gordon
 *
 */
public interface VariablesViewAPI extends SubcomponentAPI{
	/**
	 * Adds or updates a variable to the list of variables to display
	 * @param a
	 * The name of the variable
	 * @param b
	 * The value of the variable
	 */
	public void updateVar(String a, String b);
}
