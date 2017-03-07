package frontend;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import frontend.API.SubcomponentAPI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class VisualOptionsView implements SubcomponentAPI {

	private ResourceBundle resource;
	private View view;
	private VariablesView vView;
	private MethodsView mView;
	private PalleteView pView;
	private HBox visualViews;

	public VisualOptionsView(View viewIn){
		view = viewIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		visualViews = new HBox();
		setVariables();
	}  

	private void setVariables() {
		mView = new MethodsView(view);
		visualViews.getChildren().add(mView.getParent());
		vView = new VariablesView(view);
		visualViews.getChildren().add(vView.getParent());
		pView = new PalleteView(view);
		visualViews.getChildren().add(pView.getParent());
	}


	@Override
	public Parent getParent() {
		return visualViews;
	}
}

