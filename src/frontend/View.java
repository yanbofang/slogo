package frontend;

import java.util.ResourceBundle;

import backend.Model;
import breakout.Breakout;
import coordinate.Coordinate;
import frontend.API.ViewAPI;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Duration;

public class View implements ViewAPI{
	private static final int HEIGHT = 600;
	private static final int WIDTH = 1000;
	private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final String RESOURCE_BUNDLE = "resources/Display";
	
	private Stage stage;
	private Scene scene;
	private GridPane root;
	private Controller controller;
	private Timeline timeline;
	private ResourceBundle resource;
	
	private TurtleView turtleView;
	private MethodsView methodsView;
	private OptionsView optionsView;
	private VariablesView variablesView;
	private PromptView promptView;
	
	
	public View(Stage stageIn, Controller controllerIn){
		stage = stageIn;
		resource = ResourceBundle.getBundle(RESOURCE_BUNDLE);
		controller = controllerIn;
		timeline = createTimeline();
		turtleView = new TurtleView(this);
		methodsView = new MethodsView(this);
		optionsView = new OptionsView(this);
		variablesView = new VariablesView(this);
		promptView = new PromptView(this);		
		this.setView();
	}

	@Override
	public Coordinate getBounds() {
		return turtleView.getBounds();
	}

	@Override
	public void updateVar(String a, String b) {
		variablesView.updateVar(a, b);		
	}

	@Override
	public void showError(String a) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Error Found");
		alert.setContentText(a);
		alert.showAndWait();
	}
	
	public void setTurtle(ImageView imageView){
		turtleView.placeTurtle(imageView);
	}
	
	public void update(Coordinate oldC, Coordinate newC){
		turtleView.changePosition(oldC, newC);
	}

	@Override
	public void updateUMethod(String a, String b) {
		methodsView.updateUMethods(a, b);
	}

	@Override
	public void changeVariable(String a, String b) {
		controller.updateVariable(a, b);
	}

	@Override
	public void useUMethod(String a, String b) {
		controller.handleInput(b);
	}

	@Override
	public void changeBackground(String a) {
		turtleView.setBackgroundColor(a);
	}

	@Override
	public void changeImage(Image a) {
	}

	@Override
	public void changePenColor(String a) {
		turtleView.setPenColor(a);
	}

	@Override
	public void changeLanguage(String a) {
	}

	@Override
	public void runCommand(String a) {
		controller.handleInput(a);
	}
	
	private void step(double dt){
		controller.runCommand();
	}
	
	private Timeline createTimeline(){
		Timeline ret = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		ret.setCycleCount(Animation.INDEFINITE);
		ret.getKeyFrames().add(frame);
		return ret;
	}
	
	private void setView(){
		root = new GridPane();
		scene = new Scene(root, WIDTH, HEIGHT);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(50);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(50);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(50);
		root.getColumnConstraints().addAll(column1, column2, column3);
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(10);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(50);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(50);
		RowConstraints row4 = new RowConstraints();
		row4.setPercentHeight(30);
		root.getRowConstraints().addAll(row1, row2, row3, row4);
		
		root.add(optionsView.getParent(), 0, 0, 3, 1);
		root.add(turtleView.getParent(), 0, 1, 2, 2);
		root.add(variablesView.getParent(), 0, 3, 1, 1);
		root.add(methodsView.getParent(), 1, 3, 1, 1);
		root.add(promptView.getParent(), 2, 1, 1, 3);
		
		stage.setScene(scene);
		stage.show();
	}

}
