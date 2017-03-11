# slogo

A development environment that helps users write SLogo programs.

![SLOGO UI](/images/slogoUI.png)

## How to use
1. Running Command: Entering command in the box on the bottom right corner and hit submit. You can click the command in the history window to execute. You can change the variable value by clicking on the variable in the Active Variables window.
2. Change the views of the workspace: clicking change views and adjust to your preferences
3. Save the current workspace and variables/functions: pressing the save button.
4. Creating new workspace/load previously saved workspace: pressing New to create another window, clicking load to load previously saved workspace
4. Change language: click on Language and a list of options will appear.

## commands supported
* FORWARD pixels
* FD pixels	
* BACK pixels
* BK pixels	moves turtle backward in its current heading by pixels distance
* LEFT degrees
* LT degrees	turns turtle counterclockwise by degrees angle
* RIGHT degrees
* RT degrees	turns turtle clockwise by degrees angle
* SETHEADING degrees
* SETH degrees	turns turtle to an absolute heading
* TOWARDS x y	turns turtle to face the point (x, y), where (0, 0) is the center of the screen
* SETXY x y
* GOTO x y	moves turtle to an absolute screen position, where (0, 0) is the center of the screen
* PENDOWN
* PD	puts pen down such that when the turtle moves, it leaves a trail
* PENUP
* PU	puts pen up such that when the turtle moves, it does not leave a trail
* SHOWTURTLE
* ST	makes turtle visible
* HIDETURTLE
* HT	makes turtle invisible
* HOME	moves turtle to the center of the screen (0 0)
* CLEARSCREEN
* CS	erases turtle's trails and sends it to the home position
* XCOR	returns the turtle's X coordinate from the center of the screen
* YCOR	returns the turtle's Y coordinate from the center of the screen
* HEADING	returns the turtle's heading in degrees
* PENDOWN?
* PENDOWNP	returns 1 if turtle's pen is down, 0 if it is up
* SHOWING?
* SHOWINGP	returns 1 if turtle is showing, 0 if it is hiding
* SUM expr1 expr2
* + expr1 expr2	returns sum of the values of expr1 and expr2
* DIFFERENCE expr1 expr2
* - expr1 expr2	returns difference of the values of expr1 and expr2
* PRODUCT expr1 expr2
* * expr1 expr2	returns product of the values of expr1 and expr2
* QUOTIENT expr1 expr2
* / expr1 expr2	returns quotient of the values of expr1 and expr2
* REMAINDER expr1 expr2
* % expr1 expr2	returns remainder on dividing the values of expr1 by expr2
* MINUS expr
* ~ expr	returns negative of the values of expr
* RANDOM max	returns random non-negative number strictly less than max
* SIN degrees	returns sine of degrees
* COS degrees	return cosine of degrees
* TAN degrees	returns tangent of degrees
* ATAN degrees	returns arctangent of degrees
* LOG expr	returns natural log of expr
* POW base exponent	returns base raised to the power of the exponent
* PI	Reports the number Pi
* Boolean Operations
* Name	Description
* LESS? expr1 expr2
* LESSP expr1 expr2	returns 1 if the value of expr1 is strictly less than the value of expr2, otherwise 0
* GREATER? expr1 expr2
* GREATERP expr1 expr2	returns 1 if the value of expr1 is strictly greater than the value of expr2, otherwise 0
* EQUAL? expr1 expr2
* EQUALP expr1 expr2	returns 1 if the value of expr1 and the value of expr2 are equal, otherwise 0
* NOTEQUAL? expr1 expr2
* NOTEQUALP expr1 expr2  	returns 1 if the value of expr1 and the value of expr2 are not equal, otherwise 0
* AND test1 test2	returns 1 if test1 and test2 are non-zero, otherwise 0
* OR test1 test2	returns 1 if test1 or test2 are non-zero, otherwise 0
* NOT test	returns 1 if test is 0 and 0 if test is non-zero
* MAKE variable expr
* SET variable expr	assigns the value of expr to variable, creating the variable if necessary
* REPEAT expr [ command(s) ]	runs command(s) given in the list the value of expr number of times
* DOTIMES [ variable limit ]
[ command(s) ]	runs command(s) for each value specified in the range, i.e., from (1 - limit) inclusive 
* FOR [ variable start end increment ]
[ command(s) ]	runs command(s) for each value specified in the range, i.e., from (start - end), going by increment
* IF expr [ command(s) ]	if expr is not 0, runs the command(s) given in the list
* IFELSE expr [ trueCommand(s) ] [ falseCommand(s) ]if expr is not 0, runs the trueCommands given in the first list, otherwise runs the falseCommands given in the second list
* TO commandName [ variable(s) ] [ command(s) ] assigns command(s) given in the second list to commandName using parameters given in first list as variables
when commandName is used later in a program, any given values are assigned to variables that can be accessed when the command list is run
* SETBACKGROUND index
* SETBG index	
* SETPENCOLOR index
* SETPC index
* SETPENSIZE pixels
* SETPS pixels
* SETSHAPE index
* SETSH index
* SETPALETTE index r g b
* PC	returns turtle's current color index
* SHAPE returns turtle's current shape index
* SH	returns turtle's current shape index
* ID	returns current active turtle's ID number
* TURTLES returns number of turtles created so far
* TELL [ turtle(s) ]	sets turtles that will follow commands hereafter
* ASK [ turtle(s) ]  [ command(s) ] only the turtles given in first list all run commands given in the second list
* ASKWITH [ condition ] [ command(s) ] tell turtles matching given condition to run commands given in the second list


## Additional Notes

* Grouping arguments supported: allow variable number of parameters to procedures where appropriate by using parentheses (e.g., sum, difference, product, and, or, etc.)
 
* Recursion supported: allow user defined commands to include recursive calls

* Variable scope supported: dynamic variable scope and local variables