package frontend;

import java.util.ResourceBundle;

import frontend.API.SubcomponentAPI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PenView implements SubcomponentAPI {

	
	View view;
	ResourceBundle resource;
	HBox box;
	
	
	public PenView(View viewIn) {
		view = viewIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		box = new HBox();
		createBox();
	}
	
	private void createBox() {
		Label boxTitle = new Label("Pen Features");
		box.getChildren().add(boxTitle);
		setPenPosition();
		setPenWidth();
	}
	
	private void setPenPosition() {
		HBox positionOptions = new HBox();
		Label positionTitle = new Label("Choose the state of the pen: \n"
				+ "if pen is up, the turtle will not draw a line \n"
				+ "if the pen is down, the turtle will draw a line");
		positionTitle.setWrapText(true);
		createPenUpBtn(positionOptions);
		createPenDownBtn(positionOptions);	
		box.getChildren().add(positionOptions);
	}
	
	private void createPenUpBtn(HBox group) {
		Button btn = new Button(resource.getString("penUp"));
		group.getChildren().add(btn);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				view.setPen(true);
			}
		});
	}
	
	private void createPenDownBtn(HBox group) {
		Button btn = new Button(resource.getString("penDown"));
		group.getChildren().add(btn);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				view.setPen(false);
			}
		});
	}
	
	private void setPenWidth() {
		Slider sizeSlide = new Slider(1, 100, 10);
		sizeSlide.valueProperty().addListener(e -> {
			view.setPenSize(sizeSlide.getValue());
		});
		box.getChildren().add(sizeSlide);
	}
	
	@Override
	public Parent getParent() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
