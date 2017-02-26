package frontend;

import coordinate.Coordinate;
import frontend.API.TurtleViewerAPI;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TurtleView implements TurtleViewerAPI{

	@Override
	public Parent getParent() {
		// TODO Auto-generated method stub
		Pane temp = new Pane();
		temp.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		return temp;
	}

	@Override
	public Coordinate getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBackgroundColor(String a) {
		// TODO Auto-generated method stub
		
	}

}
