package backend;

public class Variable implements java.io.Serializable {

	private String myName;
	private Double myValue;

	public Variable(String variable, Double value) {
		myName = variable;
		myValue = value;
	}

	public String getVariableName() {
		return myName;
	}

	public Double getValue() {
		return myValue;
	}

	public void setValue(Double value) {
		myValue = value;
	}
}
