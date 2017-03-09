package controller;

import javafx.scene.image.Image;

public interface ControllerAPI {
	void handleInput(String input);

	void runCommand();

	void updateVariable(String var, String value);

	void changeLanguage(String newLanguage) throws Exception;

	void changeImage(Image a);
}
