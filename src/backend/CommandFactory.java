package backend;

import commands.Command;

public class CommandFactory {
	
	private static final String COMMAND_PACKAGE = "commands.";
	private PatternParse myPatterns;
	
	public CommandFactory(String syntax) {
		myPatterns = new PatternParse();
		myPatterns.addPattern(syntax, false);
	}
	
	public Command reflectCommand(String s, VariableManager variables) {
		String className = myPatterns.getSymbol(s, false);
		try {
			Class<?> clazz = Class.forName( COMMAND_PACKAGE + className + "Command" );
			return (Command) clazz.getDeclaredConstructor(java.lang.String.class, backend.VariableManager.class).newInstance(s, variables);
		} catch (Exception e) {
			return null;
		}
	}
	
	

}
