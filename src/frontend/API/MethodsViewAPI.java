package frontend.API;

/**
 * API for the user defined methods subcomponent
 * Contains a list of available user defined methods
 * @author Gordon
 *
 */
public interface MethodsViewAPI extends SubcomponentAPI{
	/**
	 * Adds or updates a user defined method into the list to display
	 * @param a
	 * The name of the user defined method
	 * @param b
	 * The raw string of commands that the method calls
	 */
	public void updateUMethods(String a, String b);
}
