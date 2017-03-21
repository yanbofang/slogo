package frontend;

import java.util.ResourceBundle;

import coordinate.Coordinate;
import frontend.API.SubcomponentAPI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyCode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * Class to create and control the prompt window and history of previous commands
 * @author Faith
 * Runs methods from View (passed into the constructor)
 */

public class PromptView implements SubcomponentAPI{

	private View view;
	private VBox history;
	private VBox prompt;
	private VBox wholeView;
	private ScrollPane scrollPane;
	private ResourceBundle resource;
	private Button submitBtn;
	private TextArea userText;
	String text;
	
	// Constructor
	public PromptView(View viewIn) {
		view = viewIn; 
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		wholeView = new VBox();
		wholeView.setAlignment(Pos.TOP_CENTER);
		promptSetup();
		historySetup();
		buttonSetup();
		wholeView.getChildren().addAll(scrollPane, prompt, submitBtn);
	}
	
	
	/**
	 * clears the history window of previous commands
	 */
	public void clearHistory(){
		history.getChildren().clear();
	}
	
	/**
	 * creates prompt window
	 */
	private void promptSetup() {
		prompt = new VBox();
		userText = new TextArea();
		prompt.getChildren().add(userText);
	}
	
	/**
	 * creates history window
	 */
	private void historySetup() {
		scrollPane = new ScrollPane();
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		VBox layout = new VBox();
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setPadding(new Insets(10, 10, 0, 20));
		layout.setSpacing(20);
		scrollPane.setContent(layout);
		scrollPane.setFitToWidth(true);
		scrollPane.setPrefHeight(425);
		history = new VBox();	
		Label title = new Label("Command History\n(click to execute)");
		layout.getChildren().addAll(title,history);
	}
	
	/**
	 * Adds text to the list of previous commands and allows that string,
	 * when clicked, to execute as a command
	 * @param text
	 * a String that is to be added to the history window 
	 */
	private void updateHistory(String text) {
		Hyperlink textToAdd = new Hyperlink(text);
		history.getChildren().add(textToAdd);
		textToAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				String tempText = textToAdd.getText();
				runCommand(tempText);
			}
		});
	}
	
	/**
	 * creates a button that runs the user typed string as a command
	 */
	private void buttonSetup() {
		submitBtn = new Button(resource.getString("Submit"));
		submitBtn.setId("submit");
		submitBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				text = userText.getText();
				userText.setText("");
				updateHistory(text);
				runCommand(text);
			}
		});
		
	}
	
	/**
	 * runs the string as a command by calling method in View.java
	 * @param a
	 * String to be run as a command
	 */
	private void runCommand(String a) {
		view.runCommand(a);
	}
	
	/**
	 * returns the Parent object containing the prompt and history windows
	 * @return wholeView
	 * The Parent object that contains the prompt and history windows
	 */
	@Override
	public Parent getParent() {
		return wholeView;
	}
	


}
