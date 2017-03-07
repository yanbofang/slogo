package frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ViewObservable<T> extends Observable{
	private List<T> views;
	
	public ViewObservable(){
		views = new ArrayList<T>();
	}
	
	public ViewObservable(List<T> viewsIn){
		views = viewsIn;
	}
	
	public void add(T t){
		this.setChanged();
		views.add(t);
		notifyObservers(t);
	}
	
	public void remove(T t){
		this.setChanged();
		views.remove(t);
		notifyObservers(t);
	}
	
	public boolean contains(T t){
		return views.contains(t);
	}
	
	public List<T> getList(){
		return views;
	}
	
}
