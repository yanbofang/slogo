package frontend;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import frontend.API.SubcomponentAPI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class VisualOptionsView implements SubcomponentAPI {

	private ResourceBundle resource;
	private View view;
	private VariablesView vView;
	private MethodsView mView;
	private PalleteView pView;
	private HBox visualViews;
	private String defaultBackgroundColor = "white";
	private String defaultPenColor = "black";
	private String defaultLang = "English";
	private String defaultTurtle = "turtle";
	
	private String[][] viewOptions = {{"backgroundColor", defaultBackgroundColor},{"penColor", defaultPenColor}, 
			{"lang", defaultLang}, {"turtle", defaultTurtle}};

	Map<String,Integer> colorMap = new TreeMap<String,Integer>();
	Map<String,Integer> turtleMap = new TreeMap<String,Integer>();

	private ObservableList<String> colors = FXCollections.observableArrayList(
			"black", "white", "red", "green", "blue", "yellow");

	private ObservableList<String> turtles = FXCollections.observableArrayList(
			"greenturtle", "blueturtle", "pinkturtle", "turtle");
	

	public VisualOptionsView(View viewIn){
		view = viewIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		visualViews = new HBox();
		setVariables();
	}  
	
	private Map<String,Integer> createMap(List<String> keys) {
		Map<String,Integer> map = new TreeMap<String,Integer>();
		for(Integer i = 0; i<keys.size(); i++) {
			map.put(keys.get(i), i);
		}
		return map;
	}

	private void setVariables() {
		mView = new MethodsView(view);
		visualViews.getChildren().add(mView.getParent());
		vView = new VariablesView(view);
		visualViews.getChildren().add(vView.getParent());
		pView = new PalleteView(view);
		visualViews.getChildren().add(pView.getParent());
	}
	
	@SuppressWarnings("unchecked")
	private void createFeatureButton(String feature,@SuppressWarnings("rawtypes") ObservableList options) {
		@SuppressWarnings({ "rawtypes" })
		ComboBox btn = new ComboBox(options);
		btn.setPromptText(resource.getString(feature));
		visualViews.getChildren().add(btn);
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
	}

	@Override
	public Parent getParent() {
		return visualViews;
	}
	
	private void changePenColor() {
		view.changePenColor(viewOptions[1][1]);
	}
	
	private void changeBackgroundColor() {
		view.changeBackground(viewOptions[0][1]);	
	}
	
	
	private void changeImage() {
		String imageName = resource.getString(viewOptions[3][1]);
		Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(imageName));
		view.changeImage(turtleImage);

	}
}

