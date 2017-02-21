## Introduction

In creating this program, our team hopes to create an Integrated Development Environment or IDE that allows users to write programs with SLogo.  SLogo is a simplified version of Logo that allows beginner programmers to perform basic functions.  In our program specifically they will be able to create commands, that when executed, cause a turtle graphic to move to different points around a screen.  This will allow them to draw simple graphics.  In doing so, they will be able to create methods and variables that will make designing their graphics easier and will introduce them to basic programming concepts.

In designing this project, an emphasis was placed on making the features that the user could interact with the most flexible aspect.  This means that in creating a design for this project, we focused on making sure that once completed, either we or other programmers will be able to easily expand on what the user is able to do with the IDE.  In order to do this, we separated the frontend and backend of the project into two separate classes and designed a frontend that simply displays the results of the work done by the backend.  This means that in adding new features, a change will simply have to be made to the backend.  We split this part of the project into multiple classes so that in adding a feature, ideally a new class could be created and only one class, the main backend class, would have to be changed.

## Design Overview

For our SLogo software, we plan to have 4 APIs: Frontend external, backend external, frontend internal, and backend internal.

#### Frontend External

The function of frontend external is to communicate with the backend, which primarily involves passing back the user input text, telling the backend when to update the turtle’s movement, updating user-made variables on click, and finally obtaining the Turtle’s image to be displayed. Within this API, there is only one class, which we call View that communicates to the backend.

#### Backend External

The class that frontend will be communicating to, which is the only class a part of the backend external API, is Model. Model, like View, serves as the backend communicator with the front end. It will be the class receiving input text, variable changes, and update calls. It will also be communicating with the frontend to get UI screen bounds, update variables to be displayed to the user, update user-made methods to be displayed to the user, and displaying errors to the user. We chose to set up our code this way because it makes it easier for frontend/backend developers to work on their code when they only need to worry about a few methods found in a single class.

#### Frontend Internal

For our program, ```View``` serves as the sole communicator with the backend. With this responsibility, it made the most sense for ```View``` to also serve as the central hub for all frontend functions. The front end will primarily be composed of ```View``` as a center node, branching from ```View``` will be various subcomponents that function independently of each other. These subcomponents can only communicate through ```View```. With this structure we hope to allow high modularity in our frontend, while have a strict structure.

The current subcomponents planned out for the project are ```OptionsView```, ```TurtleViewer```, ```PromptView```, ```VariablesView```, and ```MethodsView```. Most of these are pretty self-explanatory for their specific purpose. ```OptionsView``` will handle UI interactions involving configurations for the program and the UI itself. ```TurtleViewer``` is simply the dedicated region for the turtle to reside in and to move around. ```PromptView``` is the subcomponent that will handle user inputs and history. ```VariablesView``` is a display of current variables in the environment. ```MethodsView``` is a display of all the user defined methods.

Each subcomponent is required to provide ```View``` a node that is a graphical representation of its purpose. Most commonly this would be some sort of JavaFX Pane in order to fit the UI design of our project. This can be seen in our API for ```Subcomponent```. For now there is only the general requirement that the subcomponent is able to provide its node. In addition, specific subcomponents may be required to have additional methods to perform their functions properly. These APIs extend subcomponent and include their required methods for use. Classes that will utilize such APIs will be VariablesView, MethodsView, and TurtleViewer.

#### Backend Internal

The function of the backend internal is primarily to parse the input, interpret all the logic associated with that command, and move the turtle/make adjustments to UI. There are several classes in this API. Parser will be the class involved in parsing the input string, and error checking to make sure it is at least in a valid format. Therefore, the only public method parser will call is parse(String), which will return a list of Commands back to our Model. Command will essentially be a wrapper class for the data of a command (such as instruction, and the values associated with the instruction). Model will then add these commands to our CommandHandler, which is a class that holds a queue full of the Commands, in the order of user input. CommandHandler will then be able to pop the next command off (when simulation triggers getNextPosition() through Model) and interpret the command through a MovementFactory (matches the correct type of command) and the abstract class Movement, which is responsible for actually doing the calculations. CommandHandler will return the next coordinate to model, which will then update the X and Y coordinates of the turtle, which will be reflected on the UI.

In addition to these classes that create the flow from user input to turtle movement, the backend will also contain a VariableManager, UMethodManager, and Turtle. VariableManager will essentially be a wrapper class for a Map, and will keep track of user-made variables. VaraibleManager will have two public classes, get(String), which returns the value that is mapped to that variable key, and add(String, String), which adds the variable and its value to the map. UMethodManager will also be a wrapper class of a Map and keep track of the user-made method variables. But, unlike VariableManager which maps a String to a String, UMethodManager will map a String to a List of Commands (in order as the user inputted). UMethodManager will have the same two classes. get(String) that returns a List of Commands, and add(String, List<Commands>) to add the key, value pair to the map. Turtle, while being primarily reflected on front end, will be incorporated into backend for ease of changing the coordinate.

The way we designed our internal backend was for Model to be the main class controlling the pipeline through the program. We wanted all of our classes to return to Model before Model calls another class function. This way, we simplify the API, and reduce the direct dependencies each class has on each other. For example, Model will initialize the VariableManager, UMethodManager, Parser, Turtle, and CommandHandler. Whenever it calls a method from those classes, such as Parser.parse(String input), Parser will return the necessary data (List<Commands>) that Model will then use to call CommandHandler.addCommands(List<Commands>). In addition, the internal backend will only communicate with the front end through Model (which is the external backend API).

![Design Picture 1](/images/designPic1.jpeg)


## User Interface

The user interface will be organized as shown in the image below.  Within the square labeled Image Display, the user will not be able to control anything, as this part of the screen will simply show the image created by the turtle.  The variables and methods rectangles will display the variables and methods respectively that the user has already created.  The user will be able to change the variables by clicking on them and typing in a new value, but the methods will be final.  The square titled user controlled visual changes will contain buttons allowing the user to change different aspects of the appearance of the graphic, such as line color.  The console will allow the user to type in commands that will be executed once they press a button which will be located in the bottom right corner of this section.  Finally, the command history will display the most recent commands used by the user.  

If the user tries to execute a command that does not exist, or define a variable that has an error in it, then a statement declaring that there was an error will appear in the command history below the erroneous line.


## API Details

#### Frontend External

**View**

The main task for ```View``` is to take inputs from the user and promptly display pertinent information about past or current code. As described earlier, ```View``` acts as the central hub for all frontend functions. It then has the capabilities of communicating to the backend with new inputs or to request information. By having this class be the central hub, it allows the frontend to freely add new parts to the user interface without creating a complex system of references and pointers. This class also can be closed to additions but open to extensions. Through the API, the backend is guaranteed to have classes that it needs to function properly; however, if a different layout is sought then ```View``` can easily be extended to change up its appearances.

1. getBounds(): Returns the bounds of the area in which the turtle resides in. This allows the backend to know where the turtle can go, regardless of the size of the window. This method returns the bottom right most point as a Coordinate object.
2. updateVar(String, String): Backend should call this method whenever a Variable is created or updated by the user. This updates the current list of variables to be displayed by either changing variable or adding a new variable. This method returns void.
3. showError(String): Backend or errors in the frontend will call this method in order to alert the user of an error made. This method returns void.
4. updateUMethod(String, String): Backend should call this method whenever a new User Defined Method is created. This will update or add a method in a list of User Methods to be displayed. This method returns void.


#### Backend External

**Model**

The Model is designed to be used by the frontend View class. It has all the information the frontend need and can have access to. In addition to the description above on Model, this part will includes the descriptions of all of its public methods:
1. handleInput(String): the View needs to call handleInput(String) to ask the backend to start parsing the information. View needs to pass the user’s input as a String.
2. getNextPos(): the View needs to call getNextPos() to get the next coordinate that the turtle will be. It returns the coordinate as a Coordinate object.
3. updateVar(String, String): When the user changed a previously created variable, the View can call updateVar(String, String) to update. The first String is the name of the variable, and the second String parameter is the value of that variable. This method returns void.
4. getTurtle(): the View calls this method to get the Node of the turtle, then the View can add it into its Group in order for the turtle to show up. This method returns the Node of the turtle.



#### Frontend Internal

The frontend internal API essentially modularize the User Interface in order to keep each subsection of the window distinct and allows future additions of visually components. With this design, ```View``` acts as the central hub for communication between subcomponents and with the backend. For this reason ```View``` has several methods to allow any necessary changes between the subcomponents or changes to send to the backend. On the other hand, all the subcomponents must have a getNode method which will return a node that should be fully functional for its specified purpose. Additionally the subcomponents will have additional methods depending on what exact purpose they serve.

**View**

1. changeVariable(String, String): Allows other frontend classes to update a variable through ```View```. It will simply call ```Model``` to update the variable but enforces the idea that only ```View``` can communicate to ```Model```. This method returns void.
2. useUMethod(Sting, String): Another method similar to changeVariable. It allows other frontend classes to communicate to backend only through ```View```. Will call and run a user defined method. This method returns void.
3. changeBackground(String): Takes in a string of hex values to change the background of the turtle’s area. Returns void.
4. changeImage(Image): Takes in an image to change the current image of the turtle. Returns void.
5. changePenColor(String): Takes in a string of hex values to change the color of the pen. Returns void.
6. changeLanguage(String): Takes in a string for language to be used to understand SLogo commands. Returns void.
7. runCommand(String): Allows other frontend classes to communicate through ```View``` to send a new input to ```Model```. This returns void.

**Subcomponent**
1. getNode(): Returns a node that represents the subcomponent

**OptionsView**
1. getNode(): Returns a node that contains buttons used to customize the IDE

**TurtleViewer**
1. getNode(): Returns a node that contains the area where the turtle is located.
2. getBounds(): Returns the bounds of the area

**PromptView**
1. getNode(): Returns a node that contains a history section, input field, and run button

**MethodsView**
1. getNode(): Returns a node that has a display for all the user defined methods.
2. updateUMethods(String, String): Adds or updates a user defined method. Returns void.

**VariablesView**
1. getNode(): Returns a node that has a display for all the variables available in the environment.
2. updateVar(String, String): Adds or updates a variable to be displayed. Returns void

#### Backend Internal

In addition to the descriptions on the internal backend APIs in the design overview, the following are a list of backend internal APIs and their descriptions:
1. Parser API:
   1. parse(String): This method returns a list of Command objects. Model calls this method to get the list command for later use. It also takes care of variables and new user defined method. Model needs to pass the String of user’s input.
2.  Command API:
   1. getInstruction(): Returns the String value of what command it is.
   2. getValue(): Returns the String value of the value related to the command.
3. CommandHandler API:
   1. getNextPos(): This method calculates and updates the next position of the turtle, it also return the value .
   2. addCommands(List<Command>): add a list of Command to the CommandHandler. Returns void.
   3. getResult(): Returns the String value of the result of executing that command.
4. Model Internal API:
   1. addVariable(String, String): When the parser detects a new variable being set up in the user’s input, it calls addVariable to add it to the VariableManager.
   2. addUMethod(String, List<Command>): When the parser detects a new method being defined by the user, it calls addUMethod to add it to the UMethodManager.
5. VariableManager API:
   1. getValue(String): Get the value of the variable by passing the name of that variable.
   2. add(String, String): Add a variable to the VariableManager, first parameter is the variable name, second is the value of that variable. If variable already exist, update its value.
6. UMethodManager API:
   1. getValue(String): Return a list of Command objects that corresponds to the user defined method.
   2. add(String, List<Command>): Add a user defined method. First String parameter is the name of the method. Second is the commands correlated to that command.
7. Turtle API:
   1. getNode(): Returns the Turtle Node.

## API Example Code

* *The user types ‘fd 50’ to move the Turtle forward 50*
 * PromptView -> View: void runCommand(String input) -> Model: void handleInput(String input) -> Parser: List<Commands> parse(String input) -> Model -> CommandHandler: add(List<Commands>) -> CommandHandler: pop() (Pops off recent command) -> MovementFactory: executeCommand(Command) -> ForwardMovement: move(int) -> Turtle: move(int)

* *The user clicks on a previously created variable ‘x’.  A popup appears and the user types in the number ‘8’ then presses Enter.*
 * VariablesView: catches event -> View: changeVar(String, String) -> Model: updateVar(String, String) -> VariableManager: add(String, String)

* *The user types in ‘froward 50’, which is not a command that exists, and presses execute.  This line appears in the history followed by an error message.*
 * View -> Model: handleInput(String) -> Parse: parse(String) ->View:showError(String)

* *The user wants to change the color of the background of the turtle’s area to blue*
 * The user clicks on “Background Color” button which prompts them to select a color or input 6 hexadecimals. OptionsView -> View: changeBackground(String) -> TurtleView: setBackgroundColor(String)

* *The user wants to change the language to French*
 * The user clicks on the “Language” button which prompts them to select a language from a dropdown menu. OptionsView -> View: changeLanguage(String)

* *The user wants a full list of commands available to them*
 * The user clicks on the "Help" button which opens a new window with a a list of documented commands. OptionsView displayHelp()

* *The user types ‘make var 50’, which creates a variable named var, which is equals to 50*
 * PromptView -> View: void runCommand(String input) -> Model: void handleInput(String input) -> Parser: List<Commands> parse(String input) -> Model -> CommandHandler: add(List<Commands>) -> CommandHandler: pop() (Pops off recent command) -> MovementFactory: executeCommand(Command) -> VariableFactory: makeVariable(String, String) -> Model: updateVar(String, String) -> VariableManager: add(String, String)

* *The user types ‘make var 60’, which reassigns the value of previously made variable var to 60*
 * PromptView -> View: void runCommand(String input) -> Model: void handleInput(String input) -> Parser: List<Commands> parse(String input) -> Model -> CommandHandler: add(List<Commands>) -> CommandHandler: pop() (Pops off recent command) -> MovementFactory: executeCommand(Command) -> VariableFactory: makeVariable(String, String) -> Model: updateVar(String, String) -> VariableManager: add(String, String)*
 * Our flow for typing out a reassignment of a variable is the same for making a new variable -- within our VariableManager: add(String, String) method, we check for existence then update.

* *The user types ‘TO ’,*
 * PromptView -> View: void runCommand(String input) -> Model: void handleInput(String input) -> Parser: List<Commands> parse(String input) -> Model -> CommandHandler: add(List<Commands>) -> CommandHandler: pop() (Pops off recent command) -> MovementFactory: executeCommand(Command) -> VariableFactory: makeVariable(String, String) -> Model: updateVar(String, String) -> VariableManager: add(String, String)






## Design Considerations

One of our biggest design choices was to base the frontend and the backend around View and Model classes, respectively. We made this choice because it allows only one class to be responsible for communication with the other half of the project, which allows for the different sub-teams in our group to worry about fewer classes and methods. Since it is also the centerpiece for the internal APIs, it reduces the number of dependencies each class has, as they only have to worry about keeping track of their caller (Model or View). We think this will provide both extensibility and better communication and organization between programming groups.

Another design decision we had to make was which side of the project we wanted include the Turtle class in. Since it is displayed in the UI, we originally grouped it with the frontend. But, as we were digging into all the code we were supposed to be able to interpret, we either had to give the backend direct access to the turtle class on the front end (which would compromise our original idea of having only Model and View communicate), or we had to make the Turtle apart of the backend. We decided to make the Turtle apart of the backend and only pass up an imageView of the turtle to be used in the UI. This allows us to easily manipulate characteristics of the Turtle.

Throughout the project, we also made decisions to create a Command class, Coordinate class, and a VariableManager class in order to better encapsulate information we were going to pass around. Instances of these classes also improve the teamwork in the backend by eliminating forced structures to be passed or to be returned. In addition to this, it allows us to avoid repetitive code that would otherwise be necessary when dealing with other data structures. The tradeoff to this though, is that we now have to go through an extra interface to retrieve a specific piece of data or to store data. In the end, encapsulating the data felt to be a much more elegant and proper way to handle information we are going to pass around.

## Team Responsibilities

Since we have talked about the methods each side of the project will need access to, we have the ability to separate into two sub-teams that will be able to work relatively independently.

* **FrontEnd**
 * Faith: OptionsView, TurtleViewer, PromptView, CSS styling
 * Gordon: View, MethodsView, VariablesView, CSS styling

* **BackEnd**
 * Henry: Parser, Command, Model, VariableManager, UMethodManager, Turtle
 * Yanbo: CommandHandler, MovementFactory, Movements
