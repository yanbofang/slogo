package animation;

import coordinate.Coordinate;
import frontend.View;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MoveAnimation {
	private Node myActor;
    private Animation myAnimation;
	    
	    
    public MoveAnimation(Node actor, Coordinate oldC, Coordinate newC) {
    	myActor = actor;
		myAnimation = makeAnimation(myActor, oldC, newC);
		// start animation
		myAnimation.play();
	}


	private Animation makeAnimation (Node agent, Coordinate oldC, Coordinate newC) {
		Line path = new Line(oldC.getX(),oldC.getY(),newC.getX(),newC.getY());
		// create an animation where the shape follows a path
		PathTransition pt = new PathTransition(Duration.millis(4000), path, agent);
		// create an animation that rotates the shape
		
		return new SequentialTransition(agent, pt);
	}

}

