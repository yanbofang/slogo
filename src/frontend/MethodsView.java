package frontend;

import java.util.HashMap;
import java.util.ResourceBundle;

import frontend.API.MethodsViewAPI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class MethodsView implements MethodsViewAPI{
	private HashMap<String, String> methods;
	private HashMap<String, Hyperlink> hyperlinks;
	
	private View view;
	private VBox vBox;
	private ScrollPane scrollPane;
	private ResourceBundle resource;
	
	public MethodsView(View viewIn){
		view = viewIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		methods = new HashMap<String, String>();
		hyperlinks = new HashMap<String, Hyperlink>();
		
		scrollPane = new ScrollPane();
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		VBox layout = new VBox();
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setPadding(new Insets(10, 10, 0, 20));
		layout.setSpacing(20);
		scrollPane.setContent(layout);
		scrollPane.setFitToWidth(true);
		
		vBox = new VBox();
		Label title = new Label("User Defined Methods\n(click to run)");
		title.setTextAlignment(TextAlignment.CENTER);
		layout.getChildren().addAll(title,vBox);
	}
	
	@Override
	public Parent getParent() {
		return scrollPane;
	}

	@Override
	public void updateUMethods(String a, String b) {
		methods.put(a, b);
		if(!hyperlinks.containsKey(a)){
			Hyperlink temp = new Hyperlink(a);
			temp.setId("uMethod");
			temp.setOnMouseClicked(e -> clickedMethod(temp));
			vBox.getChildren().add(temp);
			hyperlinks.put(a, temp);
		}
	}
	
	private void clickedMethod(Hyperlink source){
		String method = source.getText();
		view.useUMethod(method, methods.get(method));
	}
}
