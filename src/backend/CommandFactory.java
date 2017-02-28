package backend;

import commands.Command;

public class CommandFactory {
	
	private static final String COMMAND_PACKAGE = "commands.";
	
	public Command reflectCommand(String s, VariableManager variables) {
		try {
			System.out.println(s);
			Class<?> clazz = Class.forName( COMMAND_PACKAGE + s + "Command" );
			return (Command) clazz.getDeclaredConstructor(java.lang.String.class, backend.VariableManager.class).newInstance(s, variables);
		} catch (Exception e) {
			return null;
		}
	}
	
	

}
