package frontend;

import java.util.ResourceBundle;

import backend.Model;
import coordinate.Coordinate;
import frontend.API.ViewAPI;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class View implements ViewAPI{
	private static final int HEIGHT = 600;
	private static final int WIDTH = 1000;
	public static final String RESOURCE_BUNDLE = "resources/Display";
	
	private Stage stage;
	private Scene scene;
	private GridPane root;
	private Model model;
	private Timeline timeline;
	private ResourceBundle resource;
	
	private TurtleView turtleView;
	private MethodsView methodsView;
	private OptionsView optionsView;
	private VariablesView variablesView;
	private PromptView promptView;
	
	
	public View(Stage stageIn){
		stage = stageIn;
	}
	
	public void runView(Model modelIn) throws Exception{
		resource = ResourceBundle.getBundle(RESOURCE_BUNDLE);
		model = modelIn;
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

	@Override
	public void updateUMethod(String a, String b) {
		methodsView.updateUMethods(a, b);
	}

	@Override
	public void changeVariable(String a, String b) {
		model.updateVariable(a, b);
	}

	@Override
	public void useUMethod(String a, String b) {
		model.handleInput(b);
	}

	@Override
	public void changeBackground(String a) {
		optionsView.changeBackgroundColor(a);
		turtleView.setBackgroundColor(a);
	}

	@Override
	public void changeImage(Image a) {
		optionsView.changeImage(a);
	}

	@Override
	public void changePenColor(String a) {
		optionsView.changePenColor(a);
		
	}

	@Override
	public void changeLanguage(String a) {
		optionsView.changeLanguage(a);
	}

	@Override
	public void runCommand(String a) {
		model.handleInput(a);
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
