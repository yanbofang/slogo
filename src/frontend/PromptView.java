package frontend;

import frontend.API.SubcomponentAPI;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PromptView implements SubcomponentAPI{

	@Override
	public Parent getParent() {
		// TODO Auto-generated method stub
		Pane temp = new Pane();
		temp.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
		return temp;
	}

}
