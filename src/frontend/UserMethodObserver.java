package frontend;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import backend.UserMethodManager;
import frontend.API.ViewAPI;

public class UserMethodObserver implements Observer {

	private UserMethodManager ov = null;
	private ViewAPI myView;

	public UserMethodObserver(UserMethodManager um, ViewAPI view) {
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
