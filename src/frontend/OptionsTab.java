package frontend;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import frontend.API.SubcomponentAPI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class OptionsTab implements SubcomponentAPI{
	
	private ResourceBundle resource;
	private View view;
	private HBox buttonPanel;
	private ViewSelector viewSelector;
	
	private Stage s;
	private Scene scene;
	private String url = getClass().getClassLoader().getResource("help.html").toExternalForm();
	private WebEngine webEngine;
	
	public Parent getParent() {
		return buttonPanel;
	}
	
	public OptionsTab(View viewIn, Map<String, String> files, ViewObservable<String> views){
		view = viewIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		buttonPanel = new HBox();
		WebView browser = new WebView();
		webEngine = browser.getEngine();
		s = new Stage(); 
		scene = new Scene(browser);

		createNewButton();
		createViewsButton(views);
		createLanguageCombo();
		createHelpButton();
	}
	
	private void createNewButton(){
		Button newWorkSpace = new Button(resource.getString("NewWS"));
		newWorkSpace.setOnAction(e -> {
			try {
				view.newWorkSpace();
			} catch (Exception e1) {
				view.showError(resource.getString("WSError"));
			}
		});
		newWorkSpace.setId("option");
		buttonPanel.getChildren().add(newWorkSpace);
	}
	
	private void createViewsButton(ViewObservable<String> views){
		viewSelector = new ViewSelector(views);
		Button openViews = new Button(resource.getString("ViewButton"));
		openViews.setId("option");
		openViews.setOnAction(e -> viewSelector.run());
		buttonPanel.getChildren().add(openViews);
	}
	
	private void createLanguageCombo(){
		ComboBox<String> langCombo = new ComboBox<String>();
		langCombo.setPromptText(resource.getString("lang"));
		langCombo.getItems().addAll(resource.getString("Languages").split(","));
		langCombo.setOnAction(e -> changeLanguage(langCombo.getValue()));
		buttonPanel.getChildren().add(langCombo);
	}
	
	private void changeLanguage(String language){
		view.changeLanguage(language);
	}

	private void createHelpButton(){
		Button helpBtn = new Button(resource.getString("help"));
		helpBtn.setId("option");
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
}
