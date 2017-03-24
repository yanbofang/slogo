#### Controller API Changes:

We created `ControllerAPI`, it includes the public methods that will be used by View. We didn't have this API previously since we used to allow frontend directly call backend. But now we changed to the MVC model. This is why we added `ControllerAPI`.

### Changes in API

## added

* `handleInput(String input)`

This method simply acts as a messenger between the frontend and the backend. It is a necessary command that allows the front end to be able to make a variety of calls to the backend to execute commands. We knew this would be necessary for the model and this method will most likely remain since it acts as a messenger.

* `updateVariable(String var, String value)`

This method allows the frontend to make changes to variables that are active in the current environment. We chose to have the frontend make calls to the backend of variable changes in order to preserve a one way observer. Currently, backend can freely edit variables and this will notify frontend of these changes. As a choice, we decided to not allow front end to directly change variables in the backend at all. For this reason, frontend must be able to notify backend of a change through controller. An alternate choice may have been to make model an observer also for the variables; however, we made the decision to not allow frontend to directly change values directly involved with executing commands.

* `changeLanguage(String newLanguage)`

This method allows the frontend to change the language the model will interpret commands with. This is a necessary addition in order to allow for the user to change languages as they wish without having to rerun the program.

* `saveWorkspace(String s, Map<String, String> filePath, ObservableList<String> fileName, ResourceBundle resource, WorkSpace workspace)`

This method signals that the user is saving a workspace and all of its methods and variables. For this reason it is the `Controller` responsibility to get the frontend parameters to save and the backend resources to save. This also allows extensibility for additional backend information to be able to be saved.

* `loadVariablesandMethods(WorkSpace workspace)`

When frontend is parsing a workspace, frontend will signal for backend to also parse variables and defined methods. This ensures that frontend and backend remain separate, but able to remain in sync.

#### View External API Changes

### Changes in API

## Added

* `update(Obj arg0, obj arg1);`

Adding this method was necessary for making the `View` class become an observer. By doing this, we avoid multiple changes to API for frontend to react to changes in the backend. This is a major change, since it allows us to observe multiple objects that are only changed in the backend. By being an observer, this change gives us a much wider range of implementation without having to create more calls between the frontend and the backend.

* `setTurtleManager(TurtleManager tmIn)`

This method is necessary for frontend to be able to visually interact with turtles. Originally our intentions were to leave `TurtleManager` in the backend; however, it made more sense to have it be able to be changed in the frontend and the backend. The only times changes affected the other is only when a executes a command that changes a visual in the backend which is reflected in the frontend through the observer. This gives us much more versatility with how frontend can retrieve information about turtles, without having to make several extra calls.
