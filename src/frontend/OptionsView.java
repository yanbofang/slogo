package frontend;

import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class OptionsView {
	
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.language/English");
	private Group root = new Group();
	
	public Node getNode() {
		return root;
	}
	
	private void createButton(String label) {
		ComboBox btn = new ComboBox();
		root.getChildren().add(btn);
		btn.setPromptText(myResources.getString(label));
	
		
	}
}
