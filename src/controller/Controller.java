package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Observer;

import coordinate.Coordinate;
import turtles.Turtle;
import turtles.TurtleManager;
import backend.Model;
import backend.VariableManager;
import backend.UserMethodManager;
import frontend.TurtleObserver;
import frontend.UserMethodObserver;
import frontend.VariableManagerObserver;
import frontend.View;
import frontend.API.ViewAPI;
import interfaces.ModelInterface;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Controller {

	private final String[] ENGLISH_SYNTAX = new String[] { "resources/languages/English",
			"resources/languages/Syntax" };
	private ModelInterface model;
	private ViewAPI view;
	private VariableManager variables;
	private VariableManagerObserver variablesObserver;
	private TurtleManager turtle;
	private TurtleObserver turtleObserver;
	private UserMethodManager userMethods;
	private UserMethodObserver userMethodsObserver;

	public Controller(Stage arg0) throws Exception {
		variables = new VariableManager();
		view = new View(arg0, this);
		//THIS WILL NOW HAPPEN IN BACKEND
		//turtle = new Turtle(25, 25, view.getBounds().getX(), view.getBounds().getY(), 1.0);
		turtle = new TurtleManager(new Coordinate(view.getBounds().getX(), view.getBounds().getY()));
		view.setTurtle(turtle);
		userMethods = new UserMethodManager();
		model = new Model(ENGLISH_SYNTAX, variables, userMethods, turtle);
		variablesObserver = new VariableManagerObserver(variables, view);
		addVariableManagerObserver();
		turtleObserver = new TurtleObserver(turtle, view);
		addTurtleObserver();
		userMethodsObserver = new UserMethodObserver(userMethods, view);
		addUserMethodsObserver();
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
		model = new Model(newSyntax, variables, userMethods, turtle);
	}

	public void changeImage(Image a) {
		// TODO: update image
//		turtle.setImage(a);
	}

	private void addVariableManagerObserver() {
		variables.addObserver(variablesObserver);
	}

	private void addTurtleObserver() {
		// TODO: update turtle observer
//		turtle.addObserver(turtleObserver);
	}

	private void addUserMethodsObserver() {
		userMethods.addObserver(userMethodsObserver);
	}

}
