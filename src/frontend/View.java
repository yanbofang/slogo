package frontend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import controller.Controller;
import coordinate.Coordinate;
import frontend.API.SubcomponentAPI;
import frontend.API.ViewAPI;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import turtles.Pen;
import turtles.Turtle;
import turtles.TurtleAPI;
import turtles.TurtleManager;
import turtles.TurtleManagerAPI;

public class View implements ViewAPI, Observer {
	private static final int HEIGHT = 600;
	private static final int WIDTH = 1000;
	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 10000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final String SER_FILEPATH = "src/resources/";
	private static final String DEFAULT_SER = "src/resources/default.ser";
	private static final String FILEPATHS = "src/resources/paths.ser";
	public static final String RESOURCE_BUNDLE = "resources/Display";
	public static final String CSS_STYLESHEET = "resources/UI.css";

	private Stage stage;
	private Scene scene;
	private GridPane root;
	private Controller controller;
	private Timeline timeline;
	private ResourceBundle resource;
	private WorkSpace workSpace;
	private TurtleManagerAPI turtleManager;

	private OptionsTab optionsTab;
	private TurtleView turtleView;
	private MethodsView methodsView;
	private OptionsView optionsView;
	private VariablesView variablesView;
	private PromptView promptView;
	private StateView stateView;
	private PaletteView paletteView;

	private VBox views;
	private ViewObservable<String> activeViews;
	private Map<String, String> filePath;
	private ObservableList<String> fileName;

	public View(Stage stageIn, Controller controllerIn) {
		stage = stageIn;
		controller = controllerIn;
		timeline = createTimeline();
		resource = ResourceBundle.getBundle(RESOURCE_BUNDLE);

		this.initializeCore();
		this.getFilePaths();
		this.parseWorkspace(DEFAULT_SER);

		timeline.play();
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

	public void setTurtle(TurtleManager tmIn) {
		turtleManager = tmIn;
		tmIn.addObserver(this);
		for (Turtle t : turtleManager.allTurtles()) {
			turtleView.placeTurtle(t.getImage());
			t.addObserver(this);
		}
	}

	public void updateTurtle(Coordinate oldC, Coordinate newC, Pen p) {
		turtleView.changePosition(oldC, newC, p);
	}

	@Override
	public void updateUMethod(String a) {
		methodsView.updateUMethods(a);
	}

	@Override
	public void changeVariable(String a, String b) {
		controller.updateVariable(a, b);
	}

	@Override
	public void useUMethod(String a) {
		controller.handleInput(a);
	}

	@Override
	public void changeBackground(String a) {
		turtleView.setBackgroundColor(a);
	}

	@Override
	public void changeImage(Image a) {
		controller.changeImage(a);
	}

	@Override
	public void changePenColor(String a) {
		turtleView.setPenColor(a);
	}

	@Override
	public void changeLanguage(String a) {
		try {
			controller.changeLanguage(a);
		} catch (Exception e) {
			this.showError(resource.getString("WrongLanguage"));
		}
		this.clearLines();
		this.clearVariables();
		this.clearMethods();
		this.clearHistory();
		workSpace.language = a;
	}

	@Override
	public void runCommand(String a) {
		controller.handleInput(a);
	}

	public void clearVariables() {
		variablesView.clearVars();
	}

	public void clearMethods() {
		methodsView.clearMethods();
	}

	public void clearHistory() {
		promptView.clearHistory();
	}

	public void clearLines() {
		turtleView.clear();
	}
	
	public void deleteWorkspace(String s) {
		String fp = filePath.get(s);
		fileName.remove(s);
		File file = new File(fp);
		file.delete();
	}

	public void saveWorkspace(String s) {
		// TODO: background, files
		String fp = SER_FILEPATH + s + ".ser";
		filePath.put(s, fp);
		fileName.add(s);
		try {
			File file = new File(fp);
			file.getParentFile().mkdirs();
			file.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(workSpace);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			showError(resource.getString("SavingError"));
		}
	}

	public void loadWorkspace(String s) {
		views.getChildren().clear();
		parseWorkspace(filePath.get(s));
	}

	public void newWorkspace() throws Exception {
		new Controller(new Stage());
	}

	private void step(double dt) {
		controller.runCommand();
	}

	private Timeline createTimeline() {
		Timeline ret = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		ret.setCycleCount(Animation.INDEFINITE);
		ret.getKeyFrames().add(frame);
		return ret;
	}

	private void parseWorkspace(String fp) {
		try {
			FileInputStream fileIn = new FileInputStream(fp);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			workSpace = (WorkSpace) in.readObject();
			in.close();
			fileIn.close();
			updateWorkspace();
		} catch (IOException i) {
			showError(resource.getString("FileNotFound"));
			if (fp.equals(DEFAULT_SER)) {
				showError(resource.getString("DefaultMissing"));
				makeNewDefault();
			} else {
				parseWorkspace(DEFAULT_SER);
			}
		} catch (ClassNotFoundException c) {
			showError(resource.getString("ClassNotFound"));
		}
	}

	private void makeNewDefault() {
		workSpace = new WorkSpace();
		workSpace.language = resource.getString("DefaultLanguage");
		workSpace.background = Integer.parseInt(resource.getString("DefaultBackground"));
		workSpace.views = new ArrayList<String>(Arrays.asList(resource.getString("DefaultViews").split(",")));
		workSpace.colorPalette = createMap(resource.getString("defaultColors"));
		workSpace.imagePalette = createMap(resource.getString("defaultImages"));
		try {
			File file = new File(DEFAULT_SER);
			file.getParentFile().mkdirs();
			file.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(workSpace);
			out.close();
			fileOut.close();
			updateWorkspace();
		} catch (IOException i) {
			showError(resource.getString("SavingError"));
		}
	}
	
	private Map<Double, String> createMap(String keysAndValues) {
		Map<Double, String> map = new HashMap<Double,String>();
		String[] defaults = (String[]) (Arrays.asList(keysAndValues.split(";"))).toArray();
		for(String defaultChoice: defaults) {
			String[] tempChoice = (String[]) (Arrays.asList(defaultChoice.split(","))).toArray();
			map.put(tempChoice[0], Double.parseDouble(tempChoice[1]));
		}
		return map;
	}

	private void initializeCore() {
		root = new GridPane();
		scene = new Scene(root, WIDTH, HEIGHT);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(25);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(50);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(25);
		root.getColumnConstraints().addAll(column1, column2, column3);
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(10);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(90);
		root.getRowConstraints().addAll(row1, row2);

		ScrollPane scrollPane = new ScrollPane();
		views = new VBox();
		scrollPane.setContent(views);
		scrollPane.setFitToWidth(true);

		root.add(scrollPane, 0, 1, 1, 1);

		scene.getStylesheets().add(CSS_STYLESHEET);
		stage.setScene(scene);
		stage.show();
	}

	private Object handleKeyInput(KeyCode code) {
		double x = 50;
		double y = 60;
		Coordinate current = new Coordinate(x, y);
		if (code == KeyCode.L) {
			controller.handleInput("left 5");
		}
		if (code == KeyCode.R) {
			controller.handleInput("right 5");
		}
		if (code == KeyCode.F) {
			controller.handleInput("fd 5");
		}
		if (code == KeyCode.B) {
			controller.handleInput("back 5");
		}
		return null;
	}

	private void showInitialViews(List<String> myViews) {
		for (String s : myViews) {
			updateView(s);
		}
	}

	private void updateView(String viewName) {
		try {
			Field f = this.getClass().getDeclaredField(viewName);
			try {
				SubcomponentAPI temp = (SubcomponentAPI) f.get(this);
				Parent toAdd = temp.getParent();
				if (views.getChildren().contains(toAdd)) {
					views.getChildren().remove(toAdd);
				} else {
					views.getChildren().add(temp.getParent());
				}
			} catch (IllegalArgumentException e) {
				// Shouldn't happen
				showError("Illegal Argument Exception");
			} catch (IllegalAccessException e) {
				// Shouldn't happen
				showError("Illegal Access Exception");
			}
		} catch (NoSuchFieldException e) {
			showError(resource.getString("BadField") + viewName);
		} catch (SecurityException e) {
			// Shouldn't happen
			showError("Security Exception");
		}
	}

	private void initializeViews() {
		optionsTab = new OptionsTab(this, fileName, activeViews);
		promptView = new PromptView(this);
		turtleView = new TurtleView(this, workSpace.background);
		methodsView = new MethodsView(this);
		optionsView = new OptionsView(this);
		variablesView = new VariablesView(this);
		stateView = new StateView(this);
		paletteView = new PaletteView(this);

		root.add(optionsTab.getParent(), 0, 0, 3, 1);
		root.add(turtleView.getParent(), 1, 1, 1, 1);
		root.add(promptView.getParent(), 2, 1, 1, 1);
	}

	private void updateWorkspace() {
		// TODO finish updates i.e. background
		activeViews = new ViewObservable<String>(workSpace.views);
		activeViews.addObserver(this);
		this.initializeViews();
		this.showInitialViews(workSpace.views);
		changeLanguage(workSpace.language);
	}

	private void getFilePaths() {
		fileName = FXCollections.observableArrayList();
		filePath = new HashMap<String, String>();
		File dir = new File(SER_FILEPATH);
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith(".ser")) {
				String tempName = file.getName().replaceAll(".ser", "");
				fileName.add(tempName);
				filePath.put(tempName, file.getPath());
			}
		}
	}

	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof ViewObservable) {
			updateView((String) arg1);
			workSpace.views = activeViews.getList();
		}
		if (arg0 instanceof TurtleManager) {
			Turtle t = (Turtle) arg1;
			turtleView.placeTurtle(t.getImage());
			t.addObserver(this);
		}
		if (arg0 instanceof Turtle) {
			if(arg1 instanceof ArrayList<?>){
				ArrayList<Coordinate> temp = (ArrayList<Coordinate>) arg1;
				
			}
		}
	}

}