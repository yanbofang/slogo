package frontend;

import frontend.API.SubcomponentAPI;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class OptionsView implements SubcomponentAPI{

	@Override
	public Parent getParent() {
		// TODO Auto-generated method stub
		VBox temp = new VBox();
		temp.setBackground(new Background(new BackgroundFill(Color.BLUE,CornerRadii.EMPTY,Insets.EMPTY)));
		return temp;
	}

}
