package frontend;

import java.util.List;
import java.util.Map;

import backend.UserMethod;
import backend.Variable;

public class WorkSpace implements java.io.Serializable {
	public String language;
	public int background;
	public List<String> views;
	public Map<Double, String> colorPalette;
	public Map<Double, String> imagePalette;
	public List<Double> turtles;
	public Map<String, Variable> variables;
	public Map<String, UserMethod> userMethods;
}