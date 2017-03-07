package frontend;

import java.util.List;
import java.util.Map;

public class WorkSpace implements java.io.Serializable{
	public String language;
	public int background;
	public List<String> views;
	public Map<String, Double> colorPalette;
	public Map<String, Double> imagePalette;
}