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
import turtles.Pen;

public class PenView implements SubcomponentAPI {

	
	private View view;
	private ResourceBundle resource;
	private VBox box;
	private Pen p;
	
	
	public PenView(View viewIn) {
		view = viewIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		box = new VBox();
		p = new Pen();
		createBox();
	}
	
	private void createBox() {
		Label boxTitle = new Label("Pen Features");
		box.getChildren().add(boxTitle);
		setPenPosition();
		setPenWidth();
	}
	
	private void setPenPosition() {
		VBox positionOptions = new VBox();
		Label positionTitle = new Label("Choose the state of the pen: \n"
				+ "if pen is up, the turtle will not draw a line \n"
				+ "if the pen is down, the turtle will draw a line");
		positionTitle.setWrapText(true);
		createPenUpBtn(positionOptions);
		createPenDownBtn(positionOptions);	
		box.getChildren().add(positionOptions);
	}
	
	private void createPenUpBtn(VBox group) {
		Button btn = new Button(resource.getString("penUp"));
		group.getChildren().add(btn);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				p.setPen(false);
				setPenState();
			}
		});
	}
	
	private void createPenDownBtn(VBox group) {
		Button btn = new Button(resource.getString("penDown"));
		group.getChildren().add(btn);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				p.setPen(true);
				setPenState();
			}
		});
	}
	
	private void setPenWidth() {
		Slider sizeSlide = new Slider(1, 100, 10);
		sizeSlide.valueProperty().addListener(e -> {
			p.setSize(sizeSlide.getValue());
			setPenSize();
		});
		box.getChildren().add(sizeSlide);
	}
	
	private void setPenSize() {
		view.setPenSize(p.getSize());
	}
	
	private void setPenState() {
		view.setPenState(p.showPen());
	}
	
	@Override
	public Parent getParent() {
		return box;
	}
	
}
