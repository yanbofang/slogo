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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import turtles.TurtleManager;

public class VisualOptionsView implements SubcomponentAPI {

	private ResourceBundle resource;
	private View view;
	private VariablesView vView;
	private MethodsView mView;
	private PaletteView pView;
	private TurtleVisualView tView;
	private VBox visualViews;
	private VBox layout;
	private ScrollPane scrollPane;

	public VisualOptionsView(View viewIn){
		view = viewIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		visualViews = new VBox();
		setUpScrollPane();
		setIndividualViews();
	}  

	private void setUpScrollPane() {
		scrollPane = new ScrollPane();
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		layout = new VBox();
		scrollPane.setContent(layout);
		scrollPane.setFitToWidth(true);
		scrollPane.setPrefHeight(600);
		visualViews.getChildren().add(scrollPane);
	}
	
	private void setIndividualViews() {
		mView = new MethodsView(view);
		layout.getChildren().add(mView.getParent());
		vView = new VariablesView(view);
		layout.getChildren().add(vView.getParent());
		pView = new PaletteView(view);
		layout.getChildren().add(pView.getParent());
		tView = new TurtleVisualView(view);
		layout.getChildren().add(tView.getParent());
	}


	@Override
	public Parent getParent() {
		return visualViews;
	}
}

