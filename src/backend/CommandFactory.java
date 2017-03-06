package backend;

import commands.Command;

public class CommandFactory {
	
	private static final String COMMAND_PACKAGE = "commands.";
	
	public Command reflectCommand(String input, String s, VariableManager variables, UserMethodManager methods) {
		try {
			Class<?> clazz = Class.forName( COMMAND_PACKAGE + s + "Command" );
			return (Command) clazz.getDeclaredConstructor(java.lang.String.class, backend.VariableManager.class, backend.UserMethodManager.class).newInstance(input, variables, methods);
		} catch (Exception e) {
			return null;
		}
	}
	
	

}
