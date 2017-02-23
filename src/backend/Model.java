package backend;

import java.util.List;

import commands.Command;
import frontend.View;
import javafx.scene.Node;
import interfaces.ModelInterface;

public class Model implements ModelInterface {
	
	private VariableManager myVariables;
	private MethodManager myMethods;
	private Turtle myTurtle;
	private Parser myParser;
	private View myView;
	private CommandHandler myCommandHandler;
	
	public Model(String[] syntax, String commandProperties, View view) {
		myView = view;
		myVariables = VariableManager.getInstance();
		myMethods = new MethodManager();
		myTurtle = new Turtle(50, 50);
		myParser = new Parser(syntax, commandProperties, this);
		myCommandHandler = new CommandHandler();
	}
	
	@Override
	public void handleInput(String input) {
		List<Command> commands = myParser.parse(input);
		myCommandHandler.addCommands(commands);
		myCommandHandler.executeCommands();
	}

	@Override
	public String getNextPos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateVariable(String var, String value) {
		myVariables.addVariable(new Variable(var, Double.parseDouble(value)));
	}
	

	@Override
	public Node getTurtle() {
		return myTurtle.getImage();
	}
	
	
	//USED FOR PARSER
	public Double getVariable(String var) {
		try {
			return myVariables.get(var).getValue();
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
