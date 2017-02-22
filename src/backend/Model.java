package backend;

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
		myParser = new Parser();
	}
	
	@Override
	public void handleInput(String input) {
		myParser.parse(input);
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

}
