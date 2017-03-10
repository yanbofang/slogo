package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

import frontend.API.PaletteViewAPI;
import frontend.API.SubcomponentAPI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;

public class PaletteView implements PaletteViewAPI {


	private ResourceBundle resource;
	private View view;
	private VBox visualViews;
	Map<Double,String> colorMap;
	Map<Double,String> turtleMap;
	private String tempChoice;

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
		visualViews.setStyle(resource.getString("Border"));
	}  
	
	private VBox createColorView() {
		VBox colorView = new VBox();
		String text = resource.getString("CPalette");
		Label colorPalette = new Label(text);
		colorView.getChildren().add(colorPalette);
		colorButtonLabels = createFeatureButton("backgroundColor", colorMap, colorView);
		createFeatureButton("penColor", colorMap, colorView);
		return colorView;
		}
	
	private VBox createImageView() {
		VBox imageView = new VBox();
		Label imagePallete = new Label(resource.getString("TPalette"));
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
						String[] choice = (String[]) (Arrays.asList(newValue.split(": "))).toArray();
						option[1] = choice[1];
						tempChoice = choice[0];
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
			Double temp = Double.parseDouble(tempChoice);
			view.changePenColor(temp);
			viewOptions[1][1] = null;
		}
		
	}
	
	private void changeBackgroundColor() {
		if (viewOptions[0][1] != null) {
			Double temp = Double.parseDouble(tempChoice);
			view.changeBackground(temp);
			viewOptions[0][1] = null;
		}	
	}
	
	
	private void changeImage() {
		if (viewOptions[2][1] != null) {
			String imageName = resource.getString(viewOptions[2][1]);
			Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(imageName));
			view.changeImage(turtleImage);
			viewOptions[2][1] = null;
		}
	}
	
	@Override
	public Parent getParent() {
		return visualViews;
	}
	
	@Override
	public void updateColorPalette(String color, double index) {
		colorMap.put(index, color);
		colorButtonLabels = updateLists(colorMap);
	}
	
	@Override
	public void updateTurtlePalette(String color, double index) {
		turtleMap.put(index, color);
		turtleButtonLabels = updateLists(turtleMap);
	}
	
	@Override
	public Image getImageOf(double d){
		if(turtleMap.containsKey(d)){
			return new Image(this.getClass().getClassLoader().getResourceAsStream(resource.getString(turtleMap.get(d))));
		}
		else{
			return null;
		}
	}
}
