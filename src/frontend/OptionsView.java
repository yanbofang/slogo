package frontend;
import java.util.ResourceBundle;
import frontend.API.SubcomponentAPI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class OptionsView implements SubcomponentAPI{

	private Stage s;
	private Scene scene;
	private ResourceBundle resource;
	private View view;
	private HBox buttonPanel;
	private String defaultBackgroundColor = "white";
	private String defaultPenColor = "black";
	private String defaultLang = "English";
	private String helpTitle = "help";
	private String defaultTurtle = "turtle";
	private String url = getClass().getClassLoader().getResource("help.html").toExternalForm();
	private WebEngine webEngine;
	private String[][] viewOptions = {{"backgroundColor", defaultBackgroundColor},{"penColor", defaultPenColor}, 
										{"lang", defaultLang}, {"turtle", defaultTurtle}};
	
	private ObservableList<String> colors = FXCollections.observableArrayList(
			"black", "white", "red", "green", "blue", "yellow");
	
	private ObservableList<String> languages = FXCollections.observableArrayList(
			"Chinese", "English", "French", "German", "Italian", 
			"Portuguese", "Russian", "Spanish");

	private ObservableList<String> turtles = FXCollections.observableArrayList(
			"greenturtle", "blueturtle", "pinkturtle", "turtle");



	public OptionsView(View viewIn){
		view = viewIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		buttonPanel = new HBox();
		WebView browser = new WebView();
		webEngine = browser.getEngine();
		s = new Stage(); 
		scene = new Scene(browser);
		setVariables();
	} 
	
	private void setVariables() {
		createFeatureButton("penColor", colors);
		createFeatureButton("backgroundColor", colors);
		createFeatureButton("lang", languages);
		createFeatureButton("turtle", turtles);	
		createHelpButton();
	}
	
	@SuppressWarnings("unchecked")
	private void createFeatureButton(String feature,@SuppressWarnings("rawtypes") ObservableList options) {
		@SuppressWarnings({ "rawtypes" })
		ComboBox btn = new ComboBox(options);
		btn.setPromptText(resource.getString(feature));
		buttonPanel.getChildren().add(btn);
		btn.valueProperty().addListener(new ChangeListener<String> () {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				for (String[] option: viewOptions) {
					if (feature.equals(option[0])) {
						option[1] = newValue;
						updateVariables();
					}
				}
			}			
		});
	}
	
	private void updateVariables() {
		changePenColor();
		changeBackgroundColor();
		changeImage();
		changeLanguage();
	}
	
	private void createHelpButton(){
		Button helpBtn = new Button(resource.getString(helpTitle));
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

	@Override
	public Parent getParent() {
		return buttonPanel;
	}
	
	private void changePenColor() {
		view.changePenColor(viewOptions[1][1]);
	}
	
	private void changeBackgroundColor() {
		view.changeBackground(viewOptions[0][1]);	
	}
	
	private void changeLanguage() {
		view.changeLanguage(viewOptions[2][1]);
	}
	
	private void changeImage() {
		String imageName = resource.getString(viewOptions[3][1]);
		Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(imageName));
		view.changeImage(turtleImage);

	}
}
