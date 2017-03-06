package frontend;

import java.util.List;
import java.util.Map;

public class WorkSpace implements java.io.Serializable{
	public String language;
	public int background;
	public List<String> views;
	public Map<String,String> files;
}
