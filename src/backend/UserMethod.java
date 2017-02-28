package backend;
import commands.Command;
import java.util.List;

public class UserMethod {

	private String myMethodName;
	private List<Command> myListOfCommands;
	
	public UserMethod(String name, List<Command> lst) {
		// TODO Auto-generated constructor stub
		myMethodName = name;
		myListOfCommands = lst;
	}
	
	public String getMethodName(){
		return myMethodName;
	}
	
	public List<Command> getListOfCommands(){
		return myListOfCommands;
	}

}
