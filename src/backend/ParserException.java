package backend;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ParserException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	/**
	 * If there is an error, throw the alert box
	 * @param error - message to be displayed in the error box
	 * @param e - exception caught
	 */
	public ParserException(String error, Throwable e) {
		//super(e);
		Alert a = new Alert(AlertType.ERROR);
        a.setContentText(String.format("ERROR reading file: %s", error));
        a.show();
	}
}


