package frontend;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import backend.UserMethodManager;

public class UserMethodObserver implements Observer {

	private UserMethodManager ov = null;
	private View myView;

	public UserMethodObserver(UserMethodManager um, View view) {
		this.ov = um;
		myView = view;
	}

	public void update(Observable obs, Object obj) {
		if (obs == ov) {
			String methodName = (String) obj;
			myView.updateUMethod(methodName);
		}
	}

}
