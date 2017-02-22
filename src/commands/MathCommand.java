package commands;

import java.lang.reflect.Method;

public class MathCommand extends Command {

	private Double myValue;

	public MathCommand(String instruction) {
		super(instruction);
		myValue = calculateValue();
	}

	@Override
	public Double getValue() {
		return this.myValue;
	}

	@Override
	public void executeCommand() {
		this.changeToFinished();
		return;
	}

	private Double calculateValue() {
		Double value = 0.0;
		try {
			Method method = getClass().getDeclaredMethod(myInstruction, null);
			method.invoke(this, null);
			return value;
		} catch (Exception e) {
			// TODO delete print stack trace
			e.printStackTrace();
			return null;
		}
		// if (myInstruction == "sum") {
		// sum();
		// } else if (myInstruction == "difference") {
		// difference();
		// } else if (myInstruction == "product") {
		// product();
		// } else if (myInstruction == "quotient") {
		// quotient();
		// } else if (myInstruction == "remainder") {
		// remainder();
		// } else if (myInstruction == "minus") {
		// minus();
		// }
		// return value;
	}

	private Double sum() {
		return (Double) myArguments.get(0) + (Double) myArguments.get(1);
	}

	private Double difference() {
		return (Double) myArguments.get(0) - (Double) myArguments.get(1);
	}

	private Double product() {
		return (Double) myArguments.get(0) * (Double) myArguments.get(1);
	}

	private Double quotient() {
		return (Double) myArguments.get(0) / (Double) myArguments.get(1);
	}

	private Double remainder() {
		return (Double) myArguments.get(0) % (Double) myArguments.get(1);
	}

	private Double minus() {
		return -1.0 * (Double) myArguments.get(0);
	}

}
