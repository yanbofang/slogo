package frontend;

import java.util.List;
import java.util.Map;

import backend.UserMethod;
import backend.Variable;

public class WorkSpace implements java.io.Serializable {
	private String language;
	private double background;
	private List<String> views;
	private Map<Double, String> colorPalette;
	private Map<Double, String> imagePalette;
	private List<Double> turtles;
	private Map<String, Variable> variables;
	private Map<String, UserMethod> userMethods;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public double getBackground() {
		return background;
	}

	public void setBackground(double background) {
		this.background = background;
	}

	public List<String> getViews() {
		return views;
	}

	public void setViews(List<String> views) {
		this.views = views;
	}

	public Map<Double, String> getColorPalette() {
		return colorPalette;
	}

	public void setColorPalette(Map<Double, String> colorPalette) {
		this.colorPalette = colorPalette;
	}

	public Map<Double, String> getImagePalette() {
		return imagePalette;
	}

	public void setImagePalette(Map<Double, String> imagePalette) {
		this.imagePalette = imagePalette;
	}

	public List<Double> getTurtles() {
		return turtles;
	}

	public void setTurtles(List<Double> turtles) {
		this.turtles = turtles;
	}

	public Map<String, Variable> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Variable> variables) {
		this.variables = variables;
	}

	public Map<String, UserMethod> getUserMethods() {
		return userMethods;
	}

	public void setUserMethods(Map<String, UserMethod> userMethods) {
		this.userMethods = userMethods;
	}
}