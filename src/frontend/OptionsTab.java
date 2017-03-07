package frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import frontend.API.SubcomponentAPI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
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
	
	public OptionsTab(View viewIn, ObservableList<String> files, ViewObservable<String> views){
		view = viewIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		buttonPanel = new HBox();
		WebView browser = new WebView();
		webEngine = browser.getEngine();
		s = new Stage(); 
		scene = new Scene(browser);

		createNewWorkspaceButton();
		createViewsButton(views);
		createSaveButton();
		createLoadCombo(files);
		createLanguageCombo();
		createHelpButton();
	}
	
	private void createLoadCombo(ObservableList<String> files){
		ComboBox loadCombo = new ComboBox(files);
		loadCombo.setPromptText(resource.getString("Load"));
		loadCombo.setOnAction(e -> view.loadWorkspace((String) loadCombo.getValue()));
		buttonPanel.getChildren().add(loadCombo);
	}
	
	private void createSaveButton(){
		Button saveButton = createButton(resource.getString("Save"));
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				TextInputDialog dialog = new TextInputDialog(resource.getString("Name"));
				dialog.setTitle(resource.getString("SaveDialogTitle"));
				dialog.setContentText(resource.getString("SaveDialogContent"));
				Optional<String> output = dialog.showAndWait();
				if(output.isPresent()){
					view.saveWorkspace(output.get());
				}
			}
		});
	}
	
	private void createNewWorkspaceButton(){
		Button newWorkSpace = createButton(resource.getString("NewWS"));
		newWorkSpace.setOnAction(e -> {
			try {
				view.newWorkspace();
			} catch (Exception e1) {
				view.showError(resource.getString("WSError"));
			}
		});
	}
	
	private void createViewsButton(ViewObservable<String> views){
		viewSelector = new ViewSelector(views);
		Button openViews = createButton(resource.getString("ViewButton"));
		openViews.setOnAction(e -> viewSelector.run());
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
		Button helpBtn = createButton(resource.getString("help"));
		helpBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				webEngine.load(url);
				s.setScene(scene);
				s.show();
			}	
		});
	}
	
	private Button createButton(String s){
		Button ret = new Button(s);
		ret.setId("option");
		buttonPanel.getChildren().add(ret);
		return ret;
	}

}
