package backend;

public class Variable {

	private String myName;
	private Double myValue;
	
	public Variable(String variable, Double value) {
		// TODO Auto-generated constructor stub
		myName = variable;
		myValue = value;
	}
	
	public String getVariableName(){
		return myName;
	}

	public Double getValue(){
		return myValue;
	}
	
	public void setValue(Double value){
		myValue = value;
	}
}
