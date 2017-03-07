package frontend;

import java.util.HashMap;
import java.util.ResourceBundle;

import controller.TurtleInfo;
import frontend.API.StateViewAPI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class StateView implements StateViewAPI {
	private HashMap<Double, Label> turtleStatus;
	
	private ScrollPane scrollPane;
	private VBox vBox;
	private View view;

	private ResourceBundle resource;

	public StateView(View viewIn) {
		view = viewIn;
		turtleStatus = new HashMap<Double, Label>();
		
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		scrollPane = new ScrollPane();
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setPrefHeight(200);
		VBox layout = new VBox();
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setPadding(new Insets(10, 10, 0, 20));
		layout.setSpacing(20);
		scrollPane.setContent(layout);
		scrollPane.setFitToWidth(true);

		vBox = new VBox();
		Label title = new Label(resource.getString("StateTitle"));
		title.setTextAlignment(TextAlignment.CENTER);
		layout.getChildren().addAll(title, vBox);
	}

	public void updateStatus(TurtleInfo turtleInfo) {
		Label temp;
		if(!turtleStatus.containsKey(turtleInfo.getID())){
			temp = new Label();
			vBox.getChildren().add(temp);
		}
		else{
			temp = turtleStatus.get(turtleInfo.getID());
		}
		temp.setText(turtleInfo.toString());
	}

	public Parent getParent() {
		return scrollPane;
	}
}
