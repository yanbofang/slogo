package frontend;
import java.util.Arrays;
import java.util.ResourceBundle;

import frontend.API.SubcomponentAPI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class OptionsView implements SubcomponentAPI{
	
	private ResourceBundle myResources = ResourceBundle.getBundle("resources/Display");
	private Group root = new Group();
	
	public Node getNode() {
		return root;
	}
	
	private void createButton(String label) {
		ComboBox btn = new ComboBox();
		root.getChildren().add(btn);
		btn.setPromptText(myResources.getString(label));
	
	}

	@Override
	public Parent getParent() {
		// TODO Auto-generated method stub
		VBox temp = new VBox();
		temp.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		return temp;
	}

}
