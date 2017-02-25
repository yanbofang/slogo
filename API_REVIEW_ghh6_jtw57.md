SLOGO API Review
===============

> Justin Wang (jtw57)
> Gordon Huynh (ghh6)

**Part 1**
----------------------

Gordon's SLOGO
----------------------

 1. The sub-view hierarchy is conducive to the addition of new GUI elements (buttons, drop-downs, etc.). 
 2. The idea that view is a central mediator between sub components to other sub components as well as front end to back end encapsulates the idea that front end is strictly responsible for direct user interactivity.
 3. The front end is solely responsible for checking the validity of variable inputs (i.e. hex values for background color, user-defined instance variables, etc).
 4. It's modular, it's easy to follow the path of how things are handled, it's extensible yet remains closed enough to where sub-components are granted a level of independence that allows for customized implementations that won't affect other components. 

Justin's SLOGO
----------------------

1. The fact that `GameEngine` handles most of the outputs from the back end, the front end for the project is solely responsible for updating display and properly handling user inputs to transfer to the back end.
2. The API ensures that user inputs are the only values being passed down to back end; and the API ensures that only values to be displayed are sent back up to the view. It also captures the idea that only UI changes can be exempted from being sent down to back end.
3. Most user input errors such as commands, variable changes, and methods should be checked for errors in the back end and then relayed to the front end to display. Otherwise, the only error checking required by the front end is UI changes.
4. Errors are handled mainly in the `GameEngine` which centralizes error checking code for easier troubleshooting. Components to the GUI are easily addable to the program which allows for modularity.

**Part 2**
------------------------

Gordon's SLOGO
------------------------

1. There is an obvious design of a chain for responsibility. As seen in the API and design, `View` serves as the main medium for communication and `Subcomponents` are the main point of interaction between the user and the program. Additionally, the implementation of a singleton would most likely improve the encapsulation of the program as a whole.
2. 

