package frontend;

import java.util.Observer;

import backend.VariableManager;
import frontend.API.ViewAPI;

import java.util.Observable;

public class VariableManagerObserver implements Observer {
	private VariableManager ov = null;
	private ViewAPI myView;

	public VariableManagerObserver(VariableManager vm, ViewAPI view) {
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
