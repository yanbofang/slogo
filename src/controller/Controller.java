package controller;

import java.util.Observer;

import backend.Model;
import backend.VariableManager;
import frontend.VariableManagerObserver;
import frontend.View;
import javafx.stage.Stage;

public class Controller {

	private final String[] ENGLISH_SYNTAX = new String[]{"resources/languages/English", "resources/languages/Syntax"};
	private Model model;
	private View view;
	private VariableManager variables;
	private VariableManagerObserver vmo;

	
	public Controller(Stage arg0) throws Exception {
		variables = new VariableManager();
		model = new Model(ENGLISH_SYNTAX, null, variables);
		view = new View(arg0);
		view.runView(model);
		vmo =  new VariableManagerObserver(variables, view);
		addVariableManagerObserver();
	}
	
	public void setInput(String input){
		model.handleInput(input);
	}
	
	public void userUpdateVariable(String var, String value){
		model.updateVariable(var, value);
 	}
	
	public void changeLanguage(String newLanguage) throws Exception{
		String[] newSyntax = new String[]{"resources/languages/" + newLanguage, "resources/languages/Syntax"};
		model = new Model(newSyntax, null, variables);
		view.runView(model);
	}
	
	public void updateVar(String name, String value){
		view.updateVar(name, value);
	}
	
	
	public void addVariableManagerObserver(){
		variables.addObserver(vmo);
	}
	
}
