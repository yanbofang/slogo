package controller;

import java.util.Observer;

import backend.Model;
import backend.Turtle;
import backend.VariableManager;
import backend.UserMethodManager;
import frontend.TurtleObserver;
import frontend.UserMethodObserver;
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
	private UserMethodManager userMethods;
	private UserMethodObserver userMethodsObserver;

	public Controller(Stage arg0) throws Exception {
		variables = new VariableManager();
		turtle = new Turtle(50, 50, 100, 100);
		userMethods = new UserMethodManager();
		model = new Model(ENGLISH_SYNTAX, null, variables, userMethods, turtle);
		view = new View(arg0, this);
		view.setTurtle(turtle.getImage());
		variablesObserver = new VariableManagerObserver(variables, view);
		addVariableManagerObserver();
		turtleObserver = new TurtleObserver(turtle, view);
		addTurtleObserver();
		userMethodsObserver = new UserMethodObserver(userMethods, view);
		addUserMethodsObserver();
	}

	public void handleInput(String input) {
		System.out.println(input);
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
		model = new Model(newSyntax, null, variables, userMethods, turtle);
	}

	public void updateVar(String name, String value) {
		view.updateVar(name, value);
	}

	private void addVariableManagerObserver() {
		variables.addObserver(variablesObserver);
	}

	private void addTurtleObserver() {
		turtle.addObserver(turtleObserver);
	}

	private void addUserMethodsObserver(){
		userMethods.addObserver(userMethodsObserver);
	}
	
}
