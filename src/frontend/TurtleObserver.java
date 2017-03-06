package frontend;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import backend.Turtle;
import coordinate.Coordinate;
import frontend.API.ViewAPI;

public class TurtleObserver implements Observer {
	private Turtle ov = null;
	private ViewAPI myView;

	public TurtleObserver(Turtle vm, ViewAPI view) {
		this.ov = vm;
		myView = view;
	}

	public void update(Observable obs, Object obj) {
		if (obs == ov) {
			if (obj instanceof ArrayList<?>) {
				ArrayList<Coordinate> temp = (ArrayList<Coordinate>) obj;
				myView.updateTurtle(temp.get(0), temp.get(1));
			}
			else if(obj instanceof boolean[]){
				boolean[] temp = (boolean[]) obj;
				if(temp[0]){
					myView.clearLines();
					ov.setClear(false);
				}
				myView.setPen(temp[1]);
			}
		}
	}

}
