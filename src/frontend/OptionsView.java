package frontend;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import frontend.API.SubcomponentAPI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class OptionsView implements SubcomponentAPI{

	private Stage s;
	private Scene scene;
	private ResourceBundle resource;
	private View view;
	private HBox buttonPanel;
	private String backgroundColor = "white";
	private String penColor = "black";
	private String lang = "English";
	private String url = getClass().getClassLoader().getResource("help.html").toExternalForm();
	private Image turtle = new Image(getClass().getClassLoader().getResourceAsStream("greenturtle.png"));
	private WebEngine webEngine;
	
	private ObservableList<String> colors = FXCollections.observableArrayList(
			"black", "white", "red", "green", "blue", "yellow");

	private ObservableList<String> languages = FXCollections.observableArrayList(
			"Chinese", "English", "French", "German", "Italian", 
			"Portugese", "Russsian", "Spanish");

	private ObservableList<String> turtles = FXCollections.observableArrayList(
			"greenturtle", "blueturtle", "pinkturtle");



	public OptionsView(View viewIn){
		view = viewIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		buttonPanel = new HBox();
		WebView browser = new WebView();
		webEngine = browser.getEngine();
		
		s = new Stage(); 
		scene = new Scene(browser);
		//buttonPanel.getChildren().add();
		changeVariables();
	}  
	
	@SuppressWarnings("unchecked")
	private void changeVariables() {
		ComboBox<String> penBtn = createButton(resource.getString("Pen"), colors);
		penBtn.valueProperty().addListener(new ChangeListener<String> () {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				penColor = newValue;
				changePenColor(penColor);
			}
			
		});

		ComboBox backBtn = createButton(resource.getString("Background"), colors);
		backBtn.valueProperty().addListener(new ChangeListener<String> () {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				backgroundColor = newValue;
				changeBackgroundColor(backgroundColor);
			}
			
		});
		
		ComboBox langBtn = createButton(resource.getString("Language"), languages);
		langBtn.valueProperty().addListener(new ChangeListener<String> () {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				lang = newValue;	
				changeLanguage(lang);
			}		
		});
		
		ComboBox turtleBtn = createButton(resource.getString("Turtle"), turtles);
		turtleBtn.valueProperty().addListener(new ChangeListener<String> () {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				turtle = new Image(getClass().getClassLoader().getResourceAsStream(newValue+".png"));;	
				changeImage(turtle);
			}		
		});
		
		Button helpBtn = new Button(resource.getString("Help"));
		helpBtn.setId("help");
		buttonPanel.getChildren().add(helpBtn);
		helpBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				webEngine.load(url);
				s.setScene(scene);
				s.show();
			}	
		});
	}
	
//	private int testColorInput(String input) throws Exception {
//		try {
//			Color test = Color.valueOf(input);
//		}
//		catch (IllegalArgumentException e) {
//			System.out.println("This is not a valid color");
//			return 0;
//		}
//		return 1;
//	}
	
	@SuppressWarnings("unchecked")
	private ComboBox<String> createButton(String label,@SuppressWarnings("rawtypes") ObservableList options) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ComboBox btn = new ComboBox(options);
		btn.setPromptText(label);
		buttonPanel.getChildren().add(btn);
		return btn;
		
//		btn.setOnAction(new EventHandler<ActionEvent>() {	
//			@Override
//			public void handle(ActionEvent e) {
////				String temp = (String) btn.getSelectionModel().getSelectedItem();
////				try {
////					int result = testColorInput(temp);
////				} catch (Exception exp) {
////					return;
////				}
//				btn.setValue(btn.getSelectionModel().getSelectedItem());
//			}
//		});
//		return (String) btn.getValue();
	}

	@Override
	public Parent getParent() {
		return buttonPanel;
	}
	
	private void changePenColor(String a) {
		view.changePenColor(penColor);
	}
	
	private void changeBackgroundColor(String a) {
		view.changeBackground(backgroundColor);
		
	}
	
	private void changeLanguage(String a) {
		view.changeLanguage(lang);
	}
	
	private void changeImage(Image a) {
		view.changeImage(turtle);
	}

}
