package controller;

import java.util.Observer;

import backend.Model;
import backend.Turtle;
import backend.VariableManager;
import frontend.TurtleObserver;
import frontend.VariableManagerObserver;
import frontend.View;
import javafx.stage.Stage;

public class Controller {

	private final String[] ENGLISH_SYNTAX = new String[] { "resources/languages/English",
			"resources/languages/Syntax" };
	private Model model;
	private View view;
	private VariableManager variables;
	private VariableManagerObserver variablesObserver;
	private Turtle turtle;
	private TurtleObserver turtleObserver;

	public Controller(Stage arg0) throws Exception {
		variables = new VariableManager();
		turtle = new Turtle(50, 50, 100, 100);
		model = new Model(ENGLISH_SYNTAX, null, variables, turtle);
		view = new View(arg0);
		view.runView(model);
		view.setTurtle(turtle.getImage());
		variablesObserver = new VariableManagerObserver(variables, view);
		addVariableManagerObserver();
		turtleObserver = new TurtleObserver(turtle, view);
		addTurtleObserver();
	}

	public void handleInput(String input) {
		model.handleInput(input);
	}

	public void runCommand() {
		model.getNextPos();
	}

	public void updateVariable(String var, String value) {
		model.updateVariable(var, value);
	}

	public void changeLanguage(String newLanguage) throws Exception {
		String[] newSyntax = new String[] { "resources/languages/" + newLanguage, "resources/languages/Syntax" };
		model = new Model(newSyntax, null, variables, turtle);
		view.runView(model);
	}

	public void updateVar(String name, String value) {
		view.updateVar(name, value);
	}

	public void addVariableManagerObserver() {
		variables.addObserver(variablesObserver);
	}

	public void addTurtleObserver() {
		turtle.addObserver(turtleObserver);
	}

}
