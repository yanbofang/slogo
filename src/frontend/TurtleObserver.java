package frontend;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import backend.Turtle;
import coordinate.Coordinate;

public class TurtleObserver implements Observer {
	private Turtle ov = null;
	private View myView;

	public TurtleObserver(Turtle vm, View view) {
		this.ov = vm;
		myView = view;
	}

	public void update(Observable obs, Object obj) {
		if (obs == ov) {
			ArrayList<Coordinate> temp = (ArrayList<Coordinate>) obj;
			myView.updateTurtle(temp.get(0), temp.get(1));
		}
	}

}
