package frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
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
	Map<Double,String> colorMap;
	Map<Double,String> turtleMap;

	private String[][] viewOptions = {{"backgroundColor", null},{"penColor", null}, 
			{"turtle", null}};
	
	private List<Double> colors;
	
	private ObservableList<String> colorButtonLabels = FXCollections.observableArrayList();

	private ObservableList<String> turtleButtonLabels = FXCollections.observableArrayList();
	
	

	public PaletteView(View viewIn, Map<Double,String> colorIn, Map<Double,String> turtleIn){

		view = viewIn;
		colorMap = colorIn;
		turtleMap = turtleIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		visualViews = new VBox();
		visualViews.getChildren().add(createColorView());
		visualViews.getChildren().add(createImageView());
	}  
	
	private VBox createColorView() {
		VBox colorView = new VBox();
		String text = "Color Pallete \n";
		Label colorPalette = new Label(text);
		colorView.getChildren().add(colorPalette);
		colorButtonLabels = createFeatureButton("backgroundColor", colorMap, colorView);
		createFeatureButton("penColor", colorMap, colorView);
		return colorView;
		}
	
	private VBox createImageView() {
		VBox imageView = new VBox();
		Label imagePallete = new Label("Image Pallete \n");
		imageView.getChildren().add(imagePallete);
		turtleButtonLabels = createFeatureButton("turtle", turtleMap, imageView);
		return imageView;
	}
	
	private ObservableList<String> updateLists(Map map) {
		List<Double> values = new ArrayList<Double>(map.keySet());	
		ObservableList<String> buttonLabels = FXCollections.observableArrayList();
		for (Double value: values) {
			buttonLabels.add(value.toString() + ": " + map.get(value));
		}
		return buttonLabels;
	}
	
	@SuppressWarnings("unchecked")
	private ObservableList<String> createFeatureButton(String feature, Map map, VBox group) {
		ObservableList<String> options = FXCollections.observableArrayList(updateLists(map));
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
		group.getChildren().add(btn);
		return options;
	}
	
	private void updateVariables() {
		changePenColor();
		changeBackgroundColor();
		changeImage();
	}
	
	private void changePenColor() {
		if (viewOptions[1][1] != null) {
			view.changePenColor(viewOptions[1][1]);
			viewOptions[1][1] = null;
		}
		
	}
	
	private void changeBackgroundColor() {
		if (viewOptions[0][1] != null) {
			view.changeBackground(viewOptions[0][1]);
			viewOptions[0][1] = null;
		}	
	}
	
	
	private void changeImage() {
		if (viewOptions[2][1] != null) {
			String imageName = resource.getString(viewOptions[3][1]);
			Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(imageName));
			view.changeImage(turtleImage);
			viewOptions[2][1] = null;
		}
	}
	
	@Override
	public Parent getParent() {
		return visualViews;
	}

	public void updateColorPalette(String color, double index) {
		colorMap.put(index, color);
		colorButtonLabels = updateLists(colorMap);
	}
	
	public void updateTurtlePalette(String color, double index) {
		turtleMap.put(index, color);
		turtleButtonLabels = updateLists(turtleMap);
	}
}
