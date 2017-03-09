package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.ResourceBundle;

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
import frontend.WorkSpace;
import frontend.API.ViewAPI;
import interfaces.ModelInterface;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Controller implements ControllerAPI {

	private final String[] ENGLISH_SYNTAX = new String[] { "resources/languages/English",
			"resources/languages/Syntax" };
	private static final String SER_FILEPATH = "src/resources/";
	private static final String DEFAULT_SER = "src/resources/default.ser";
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
		turtle = new TurtleManager(new Coordinate(view.getBounds().getX(), view.getBounds().getY()));
		view.setTurtle(turtle);
		userMethods = new UserMethodManager();
		model = new Model(ENGLISH_SYNTAX, variables, userMethods, turtle);
		variablesObserver = new VariableManagerObserver(variables, view);
		addVariableManagerObserver();
		// turtleObserver = new TurtleObserver(turtle, view);
		// addTurtleObserver();
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
		// turtle.setImage(a);
	}

	public void saveWorkspace(String s, Map<String, String> filePath, ObservableList<String> fileName,
			ResourceBundle resource, WorkSpace workspace) {
		String fp = SER_FILEPATH + s + ".ser";
		filePath.put(s, fp);
		fileName.add(s);
		workspace.variables = variables.getVariableMap();
		workspace.userMethods = userMethods.getMethodMap();
		try {
			File file = new File(fp);
			file.getParentFile().mkdirs();
			file.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(workspace);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			view.showError(resource.getString("SavingError"));
		}
	}

	private void addVariableManagerObserver() {
		variables.addObserver(variablesObserver);
	}

	private void addTurtleObserver() {
		// TODO: update turtle observer
		// turtle.addObserver(turtleObserver);
	}

	private void addUserMethodsObserver() {
		userMethods.addObserver(userMethodsObserver);
	}

	@Override
	public void loadVariablesandMethods(WorkSpace workspace) {
		if (workspace.variables != null) {
			variables.addAll(workspace.variables);
		}
		if (workspace.userMethods != null) {
			userMethods.addAll(workspace.userMethods);
		}
	}

}
