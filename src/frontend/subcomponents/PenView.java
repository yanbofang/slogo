package frontend.subcomponents;

import java.util.ResourceBundle;

import frontend.View;
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

/**
 * Creates the Pen State subcomponent and controls visual aspects of pen
 * @author Faith
 * Relies on the use of the View class and Pen object
 */
public class PenView implements SubcomponentAPI {

	
	private View view;
	private ResourceBundle resource;
	private VBox box;
	private Pen p;
	
	// Constructor
	public PenView(View viewIn) {
		view = viewIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		box = new VBox();
		p = new Pen();
		createBox();
	}
	
	/**
	 * creates Pen State box
	 */
	private void createBox() {
		Label boxTitle = new Label("Pen Features");
		box.getChildren().add(boxTitle);
		setPenPosition();
		setPenWidth();
		box.setStyle(resource.getString("Border"));
	}
	
	/**
	 * creates buttons to control whether pen is up or down
	 */
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
	
	/**
	 * creates pen that sets pen state to up
	 * @param group
	 * the VBox of which this button will be a child of
	 */
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
	
	/**
	 * creates pen that sets pen state to down
	 * @param group
	 * the VBox of which this button will be a child of
	 */
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
	
	/**
	 * Creates a slider that allows the user to change the size of the line creates by the pen
	 */
	private void setPenWidth() {
		Slider sizeSlide = new Slider(1, 10, 10);
		sizeSlide.setValue(p.getSize());
		sizeSlide.valueProperty().addListener(e -> {
			p.setSize(sizeSlide.getValue());
			setPenSize();
		});
		box.getChildren().add(sizeSlide);
	}
	
	/**
	 * sets pen size by calling a method in View
	 */
	private void setPenSize() {
		view.setPenSize(p.getSize());
	}
	
	/**
	 * sets pen state by calling a method in view
	 */
	private void setPenState() {
		view.setPenState(p.showPen());
	}
	
	@Override
	public Parent getParent() {
		return box;
	}
	
}
