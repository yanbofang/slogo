package backend;

import java.util.List;

import commands.Command;

import javafx.scene.Node;
import interfaces.ModelInterface;

public class Model implements ModelInterface {
	
	private VariableManager myVariables;
	private MethodManager myMethods;
	private Turtle myTurtle;
	private Parser myParser;

	public Model() {
		myVariables = new VariableManager();
		myMethods = new MethodManager();
		myTurtle = new Turtle();
		String[] syntax = new String[]{"resources/languages/English", "resources/languages/Syntax"};
		myParser = new Parser(syntax, "resources/languages/Commands", this);
	}
	
	@Override
	public void handleInput(String input) {
		List<Command> commands = myParser.parse(input);
		for (Command c: commands) {
			System.out.println(c.getValue());
		}
	}

	@Override
	public String getNextPos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateVariable(String var, String value) {
		myVariables.add(var, value);
	}
	

	@Override
	public Node getTurtle() {
		return myTurtle.getImage();
	}
	
	
	//USED FOR PARSER
	public Double getVariable(String var) {
		try {
			return Double.parseDouble(myVariables.get(var));
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Command> getMethodVariable(String var) {
		try {
			return myMethods.get(var);
		} catch (Exception e) {
			return null;
		}
	}

}
