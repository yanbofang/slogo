


**API Review**

**Part 1**

Alex's API: 

1) They allow for adding more commands
2) The specific implementation of the backend/frontend is hidden from the rest of the program, except for the controller. 
3) If you type an illegal command name or invalid parameters, the program will thrown an exception. 
4) It is good because method names are descriptive and it has great encapsulation. The measure of good is that there room for expected change, but it should reflect most details. 

Henry's API:

1) We can support more commands
2) Only Model and View talk, so neither need to know the implementation behind frontend/backend. 
3) Not enough arguments, Not a valid type of command
4) We think it is good because it separates the frontend from the backend, and allows for extended command classes. 

**Part 2**

Alex's API:

1) They have the Factory Design to initialize commands, the command class to handle user input, and the Mediator as a control. 
2) They will be using Regex to help with the parsing.
3) Parsing the user input 
4) Making sure the communication between the two sides is clean and separate 
5) 

**Fd 50: 	         
 Executor.parse(SlogoView.getCommandPromptViewNode().getCurrentCommand()) -> 
TurtleCommandFactory.getCommand(String userInput) -> 
TurtleCommand.execute(String userInput) -> 
TurtleModel.update() -> 
variables.update() -> 
SlogoView.update( TurtleInfo)

**XCOR: Executor.parse(SlogoView.getCommandPromptViewNode().getCurrentCommand()) -> 
TurtleCommandFactory.getCommand(String userInput) -> 
TurtleCommand.execute(String userInput) -> 
TurtleModel.update() ->
variables.update() - > 
SlogoView.update(TurtleInfo)]

**BK 50: Exectuor.parse(SlogoView.getCommandPromptViewNode().getCurrentCommand()) -> 
TurtleCommandFactory.getCommand(String userInput) ->
TurtleCommand.execute(String userInput) -> 
TurtleModel.update() -> 
variables.update() ->
SLogoView.update(TurtleInfo)

**SET myNum 12: Exectuor.parse(SlogoView.getCommandPromptViewNode().getCurrentCommand()) -> 
VariableCommandFactory.getCommand(String userInput) ->
VariableCommand.execute(String userInput) -> 
TurtleModel.update() -> 
variables.update() ->
SLogoView.update(TurtleInfo)

**Clicking options: [SLogoController]
SLogoView.getMenuBarViewNode().displayOptions();


Henry API:

1) They have the Factory Design to initialize commands, and the command class to handle user input. They don't have a mediator yet, but they will be adding it in the future as a Controller. 
2) They will be using Regex to help with the parsing and Reflection to instantiate particular classes or methods
3) Parsing the user input 
4) Making sure the communication between the two sides is clean and separate, and making sure the internal design is implemented in the best way possible
5) 
**The user types 'fd 50' to move the Turtle forward 50
PromptView -> View: void runCommand(String input) -> 
Model: void handleInput(String input) -> 
Parser: List parse(String input) -> Model -> 
CommandHandler: add(List) -> 
CommandHandler: pop() (Pops off recent command) -> 
MovementFactory: executeCommand(Command) -> 
ForwardMovement: move(int) -> Turtle: move(int

**The user clicks on a previously created variable 'x'. A popup appears and the user types in the number '8' then presses Enter.
VariablesView: catches event -> View: changeVar(String, String) -> 
Model: updateVar(String, String) -> VariableManager: add(String, String)

**The user types in 'froward 50', which is not a command that exists, and presses execute. This line appears in the history followed by an error message.
View -> Model: handleInput(String) -> Parse: parse(String)->
View:showError(String)

**The user types 'make var 50', which creates a variable named var, which is equals to 50
PromptView -> View: void runCommand(String input) -> 
Model: void handleInput(String input) -> 
Parser: List parse(String input) -> Model -> 
CommandHandler: add(List) -> 
CommandHandler: pop() (Pops off recent command) -> MovementFactory: executeCommand(Command) -> 
VariableFactory: makeVariable(String, String) -> 
Model: updateVar(String, String) -> VariableManager: add(String, String)

**The user then types 'MYWAY', calling the command he/she just created
PromptView -> View: void runCommand(String input) -> 
Model: void handleInput(String input) -> 
Parser: List parse(String input) -> 
UMethodManager: List getValue(String) -> Model -> 
CommandHandler: add(List) -> 
CommandHandler: pop() (Pops off recent command) -> 
MovementFactory: executeCommand(Command) -> Turtle: move(int)*