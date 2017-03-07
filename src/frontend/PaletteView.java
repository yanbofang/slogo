package frontend;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import frontend.API.SubcomponentAPI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class PaletteView implements SubcomponentAPI {


	private ResourceBundle resource;
	private View view;
	private VBox visualViews;
	private String defaultBackgroundColor = "white";
	private String defaultPenColor = "black";
	private String defaultLang = "English";
	private String defaultTurtle = "turtle";
	
	Map<String,Integer> colorMap = new TreeMap<String,Integer>();
	Map<String,Integer> turtleMap = new TreeMap<String,Integer>();

	private String[][] viewOptions = {{"backgroundColor", defaultBackgroundColor},{"penColor", defaultPenColor}, 
			{"lang", defaultLang}, {"turtle", defaultTurtle}};
	
	private ObservableList<String> colors = FXCollections.observableArrayList(
			"black", "white", "red", "green", "blue", "yellow");

	private ObservableList<String> turtles = FXCollections.observableArrayList(
			"greenturtle", "blueturtle", "pinkturtle", "turtle");
	
	
	public PaletteView(View viewIn){
		view = viewIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		visualViews = new VBox();
		visualViews.getChildren().add(createColorView());
		visualViews.getChildren().add(createImageView());
		setVariables();
	}  
	
	private VBox createColorView() {
		VBox colorView = new VBox();
		colorMap = createMap(colors);
		String text = "Color Pallete \n";
		Label colorPallete = new Label(text);
		colorView.getChildren().add(colorPallete);
		ComboBox<String> backgroundBtn = createFeatureButton("backgroundColor", colors);
		colorView.getChildren().add(backgroundBtn);
		ComboBox<String> penBtn = createFeatureButton("penColor", colors);
		colorView.getChildren().add(penBtn);
		return colorView;
		}
	
	private VBox createImageView() {
		VBox imageView = new VBox();
		turtleMap = createMap(turtles);
		Label imagePallete = new Label("Image Pallete \n");
		imageView.getChildren().add(imagePallete);
		ComboBox<String> turtleBtn = createFeatureButton("turtle", turtles);
		imageView.getChildren().add(turtleBtn);
		return imageView;
	}
	
	private Map<String,Integer> createMap(List<String> keys) {
		Map<String,Integer> map = new TreeMap<String,Integer>();
		for(Integer i = 0; i<keys.size(); i++) {
			map.put(keys.get(i), i);
		}
		return map;
	}
	
	private void setVariables() {
		createFeatureButton("penColor", colors);
		createFeatureButton("backgroundColor", colors);
		createFeatureButton("turtle", turtles);
	}
	
	@SuppressWarnings("unchecked")
	private ComboBox<String> createFeatureButton(String feature,@SuppressWarnings("rawtypes") ObservableList options) {
		for (int i=0; i<options.size(); i++) {
			options.set(i, options.get(i) + ": " + colorMap.get(options.get(i)));
		}
		ComboBox btn = new ComboBox(options);
		btn.setPromptText(resource.getString(feature));
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
		return btn;
	}
	
	private void updateVariables() {
		changePenColor();
		changeBackgroundColor();
		changeImage();
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
	
	@Override
	public Parent getParent() {
		return visualViews;
	}

}
