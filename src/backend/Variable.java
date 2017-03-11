package backend;

public class Variable implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7189003635086889178L;
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
