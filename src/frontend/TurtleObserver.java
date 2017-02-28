package frontend;

import java.util.Observable;
import java.util.Observer;

import backend.Turtle;

public class TurtleObserver implements Observer {
	private Turtle ov = null;
	private View myView;

	public TurtleObserver(Turtle vm, View view) {
		this.ov = vm;
		myView = view;
	}

	public void update(Observable obs, Object obj) {
		if (obs == ov) {
			String temp = (String) obj;
			String[] arry = temp.split(" ");
			myView.updateVar(arry[0], arry[1]);
		}
	}

}
