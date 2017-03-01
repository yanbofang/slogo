package frontend;

import java.util.ResourceBundle;

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
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

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
	
	public void clearHistory(){
		history.getChildren().clear();
	}
	
	private void promptSetup() {
		prompt = new VBox();
		userText = new TextArea();
		prompt.getChildren().add(userText);
	}
	
	private void historySetup() {
		scrollPane = new ScrollPane();
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		VBox layout = new VBox();
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setPadding(new Insets(10, 10, 0, 20));
		layout.setSpacing(20);
		layout.setPrefHeight(425);
		scrollPane.setContent(layout);
		scrollPane.setFitToWidth(true);
		history = new VBox();	
		Label title = new Label("Command History\n(click to execute)");
		layout.getChildren().addAll(title,history);
	}
	
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
	
	private void runCommand(String a) {
		view.runCommand(a);
	}
	
	@Override
	public Parent getParent() {
		return wholeView;
	}
	


}
