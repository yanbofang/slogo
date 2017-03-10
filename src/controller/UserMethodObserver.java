package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import backend.UserMethodManager;
import frontend.API.ExternalViewAPI;

public class UserMethodObserver implements Observer {

	private UserMethodManager ov = null;
	private ExternalViewAPI myView;

	public UserMethodObserver(UserMethodManager um, ExternalViewAPI view) {
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
