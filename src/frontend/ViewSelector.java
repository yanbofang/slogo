package frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewSelector {
	private ResourceBundle resource;
	private HashMap<String, String> viewToField;
	private ViewObservable<String> activeViews;
	
	private Stage stage;
	private Scene scene;
	private VBox views;
	
	public ViewSelector(ViewObservable<String> initViews){
		resource = ResourceBundle.getBundle(View.RESOURCE_BUNDLE);
		activeViews = initViews;
		viewToField = new HashMap<String,String>();
		
		setUpWindow();
		setUpCheckBoxes();
		setUpSubmitButton();
	}
	
	public void run(){
		stage.show();
	}
	
	private void setUpSubmitButton(){
		Button submit = new Button(resource.getString("Submit"));
		submit.setOnAction(e -> submitViews());
		submit.setId("submit");
		views.getChildren().add(submit);
	}
	
	private void submitViews(){
		stage.hide();
	}
	
	private void setUpCheckBoxes(){
		String[] temp = resource.getString("ViewName").split(",");
		for(int i = 0; i < temp.length; i = i + 2){
			viewToField.put(temp[i], temp[i+1]);
			views.getChildren().add(makeCheckBox(temp[i]));
		}
	}
	
	private CheckBox makeCheckBox(String text){
		CheckBox tempCB = new CheckBox(text);
		if(activeViews.contains(viewToField.get(text))){
			tempCB.setSelected(true);
		}
		tempCB.setOnAction(e -> viewChanged(tempCB));
		return tempCB;
	}
	
	private void viewChanged(CheckBox target){
		if(target.isSelected()){
			activeViews.add(viewToField.get(target.getText()));
		}
		else{
			activeViews.remove(viewToField.get(target.getText()));
		}
	}
	
	private void setUpWindow(){
		stage = new Stage();
		views = new VBox();
		scene = new Scene(views);
		
		Label title = new Label(resource.getString("ViewSelectorTitle"));
		views.getChildren().add(title);
		views.setPadding(new Insets(10,10,10,10));
		views.setSpacing(10);
		
		scene.getStylesheets().add(View.CSS_STYLESHEET);
		stage.setScene(scene);
	}
	
}
