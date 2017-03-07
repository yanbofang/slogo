package animation;

import coordinate.Coordinate;
import frontend.View;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RotateAnimation {
	
	private Node myActor;
    private Animation myAnimation;
	    
	    
    
    public RotateAnimation(View viewIn, Node actor, double rotation) {
    	myActor = actor;
		myAnimation = makeAnimation(myActor, rotation);
		// start animation
		myAnimation.play();
	}

	private Animation makeAnimation (Node agent, double rotation) {
		RotateTransition rt = new RotateTransition(Duration.seconds(3));
		rt.setByAngle(rotation);
		return new SequentialTransition(agent, rt);
	}

}

