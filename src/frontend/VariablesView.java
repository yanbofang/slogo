package frontend;

import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import frontend.API.VariablesViewAPI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class VariablesView implements VariablesViewAPI{
	private HashMap<String, String> values;
	private HashMap<String, Hyperlink> hyperlinks;
	
	private View view;
	private VBox vBox;
	private ScrollPane scrollPane;
	private ResourceBundle resource;
	
	public VariablesView(View viewIn){
		view = viewIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		values = new HashMap<String, String>();
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
		Label title = new Label(resource.getString("VariableTitle"));
		title.setTextAlignment(TextAlignment.CENTER);
		layout.getChildren().addAll(title,vBox);

	}

	@Override
	public Parent getParent() {
		return scrollPane;
	}

	@Override
	public void updateVar(String a, String b) {
		values.put(a, b);
		Hyperlink temp;
		if(hyperlinks.containsKey(a)){
			temp = hyperlinks.get(a);
		}
		else{
			temp = makeHyperlink();
		}
		temp.setText(a + " = " + b);
		hyperlinks.put(a, temp);
	}
	
	private Hyperlink makeHyperlink(){
		Hyperlink ret = new Hyperlink();
		ret.setId("variable");
		ret.setOnMouseClicked(e -> clickedVariable(ret));
		vBox.getChildren().add(ret);
		return ret;
	}
	
	private void clickedVariable(Hyperlink source){
		source.setVisited(false);
		String[] variableArr = source.getText().split(" = ");
		
		TextInputDialog dialog = new TextInputDialog(variableArr[1]);
		dialog.setTitle("Edit Variable");
		dialog.setContentText("Please enter a new value: ");
		String newValue = getResult(dialog, variableArr[1]);
		updateVar(variableArr[0], newValue);
		view.changeVariable(variableArr[0], newValue);
	}
	
	private String getResult(TextInputDialog dialog, String original){
		Optional<String> output = dialog.showAndWait();
		if(output.isPresent()){
			try{
				Double.parseDouble(output.get());
			}
			catch(NumberFormatException nfe){
				dialog.setHeaderText("Invalid value!");
				return getResult(dialog, original);
			}
		}
		else{
			return original;
		}
		return output.get();
	}
}
