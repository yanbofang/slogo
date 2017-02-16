API Exercise
===============
Team Members
* Faith
* Gordon
* Henry
* Yanbo

API Critque
---------------
## **Configuration**

#### Internal
* Grid(int rows, int cols)
* fillGrid()
* setPoly(Unit u, int row, int col)
* Get
* public Grid(int rows, int cols)
* public void fillGrid()
* public abstract void setPoly(Unit u, int row, int col);
* public abstract Map<Integer[], Unit> getNeighbors(int row, int col);
* public Map<Integer[], Unit> getInstances(Unit U)
* public int rows()
* public int cols()
* public Unit getUnit(int row, int col)
* public void setUnit(int row, int col, Unit u)
* public List<Unit> getChildren()
* public List<List<Unit>> getGrid()
* public void setGrid(List<List<Unit>> newGrid)
* public void setNeighbors(List<Integer> list)
* public void makeToroidal()
* public void undoToroidal()
* public Map<Integer[], Unit> getFiniteNeighbors(int row, int col) 
* public Map<Integer[], Unit> getToroidalNeighbors(int row, int col) 

#### External
* getNeighbors(int row, int col)
* getInstances(Unit U)
* public Reads(String type, int is, int st)
* public Character get(int i, int j)
* public int size()
* public int height()
* public int width()
* public GridGenerator(int st, int us, int gs, int nc, int bs, int is, int w, int h)
* public Grid returnCurrGrid()
* public Grid returnNextGrid()
* public Grid returnInitialGrid()

## **Simulation**

#### Internal
* Alive
* Blank
* Burning
* Burnt
* Ant
* Food
* Ground
* Nest
* Predator
* Prey
* Agent
* Sugar
* Type1
* Type2
* Unit

#### External
* fireModel
* Model Class
* lifeModel
* segregationModel
* sugarModel
* watorModel

## **Simulation**

#### Internal
* public SceneSetup(int gridWidth, int gridHeight, ResourceBundle resourcesPackage, Stage stage) 
* public Button createSimButton(Pane root, String label) 
* public Text readFile(Pane infoRoot, String fileName) 
* public Button buttonPlay(Pane infoRoot, Scene nextScene, int simChoice) 
* public Button buttonBack(Pane infoRoot, Scene nextScene) 
* public Integer[] getChoices() 
* public void createButtons(Group root, Scene nextScene, String[] options) 
* public Label createLabel(Group root, int width, int height, String message) 
* public Button createSizeButton(Group sizeSceneRoot, String label)
* public VBox createBox(Group root, int width, int height) 

#### External
* public static Map<String, List<List<Character>>> returnMap() 
* public Model(Grid curr, Grid next, Grid init)
* public Group getRoot()
* public void reset() 
* public abstract void updateGrid();
* public void step()
* public abstract int getUnitA();
* public abstract int getUnitB();

SLogo Architecture Design
----------------------
1. Parsing takes place after a user types in characters and presses a button to confirm his input. This needs to occur before backend processes begin and it must start properly after pressing start. It will take place in the View class.
2. The result will be added to a RawCode object that will be passed down to Model.
3. Errors will be detected once Model passes the RawCode object to the ParseCode class. Here, the class will try to translate the RawCode to a Commands object, while doing so