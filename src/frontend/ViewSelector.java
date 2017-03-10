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

/**
 * Pop up window to allow user to select which views to display
 * @author Gordon
 *
 */
public class ViewSelector {
	private ResourceBundle resource;
	private HashMap<String, String> viewToField;
	private ViewObservable<String> activeViews;
	
	private Stage stage;
	private Scene scene;
	private VBox views;
	
	// Constructor
	public ViewSelector(ViewObservable<String> initViews){
		resource = ResourceBundle.getBundle(View.RESOURCE_BUNDLE);
		activeViews = initViews;
		viewToField = new HashMap<String,String>();
		
		setUpWindow();
		setUpCheckBoxes();
		setUpSubmitButton();
	}
	
	/**
	 * run the window
	 */
	public void run(){
		stage.show();
	}
	
	/**
	 * Set up the submit button
	 */
	private void setUpSubmitButton(){
		Button submit = new Button(resource.getString("Submit"));
		submit.setOnAction(e -> submitViews());
		submit.setId("submit");
		views.getChildren().add(submit);
	}
	
	/**
	 * Listener event for submit button
	 */
	private void submitViews(){
		stage.hide();
	}
	
	/**
	 * Set up check boxes to select views
	 */
	private void setUpCheckBoxes(){
		String[] temp = resource.getString("ViewName").split(",");
		for(int i = 0; i < temp.length; i = i + 2){
			viewToField.put(temp[i], temp[i+1]);
			views.getChildren().add(makeCheckBox(temp[i]));
		}
	}
	
	/**
	 * Create actual check box instances
	 * @param text
	 * text for check box
	 * @return
	 * check box instance
	 */
	private CheckBox makeCheckBox(String text){
		CheckBox tempCB = new CheckBox(text);
		if(activeViews.contains(viewToField.get(text))){
			tempCB.setSelected(true);
		}
		tempCB.setOnAction(e -> viewChanged(tempCB));
		return tempCB;
	}
	
	/**
	 * records which views are to be shown
	 * @param target
	 */
	private void viewChanged(CheckBox target){
		if(target.isSelected()){
			activeViews.add(viewToField.get(target.getText()));
		}
		else{
			activeViews.remove(viewToField.get(target.getText()));
		}
	}
	
	/**
	 * Sets up the initial view of the window
	 */
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
