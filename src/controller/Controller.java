package controller;

import backend.Model;
import frontend.View;
import javafx.stage.Stage;

public class Controller {

	private final String[] ENGLISH_SYNTAX = new String[]{"resources/languages/English", "resources/languages/Syntax"};
	private Model model;
	private View view;
	
	public Controller(Stage arg0) throws Exception {
		model = new Model(ENGLISH_SYNTAX, null);
		view = new View(arg0);
		view.runView(model);
	}
	
	public void setInput(String input){
		model.handleInput(input);
	}
	
	public void userUpdateVariable(String var, String value){
		model.updateVariable(var, value);
 	}
	
	public void changeLanguage(String newLanguage) throws Exception{
		String[] newSyntax = new String[]{"resources/languages/" + newLanguage, "resources/languages/Syntax"};
		model = new Model(newSyntax, null);
		view.runView(model);
	}
	
	

}
