package commands;

import java.lang.reflect.Method;

public class MoveCommand extends Command {

	
	public MoveCommand(String instruction) {
		super(instruction);
	}

	@Override
	public Double getValue() {
		myValue = calculateValue();
		return myValue;
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		return myValue;
	}
	
	public Double calculateValue() {
		Double value = 0.0;
		try {
			Method method = getClass().getDeclaredMethod(myInstruction, null);
			value = (Double) method.invoke(this, null);
			return value;
		} catch (Exception e) {
			return null;
		}
	}
	
	private Double forward() {
		Double movement = (Double) myArguments.get(0);
		return myValue;
	}

}
