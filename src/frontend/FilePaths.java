package frontend;

import java.util.List;
import java.util.Map;

/**
 * Serializable object to save list and map of current workspaces
 * @author Gordon
 *
 */
public class FilePaths implements java.io.Serializable{
	List<String> fileName;
	Map<String,String> paths;
}
