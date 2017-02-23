package commands;

import java.lang.reflect.Method;

public class MathCommand extends AbstractCommand {

	private Double myValue;

	public MathCommand(String instruction) {
		super(instruction);

	}

	@Override
	public Double getValue() {
		myValue = calculateValue();
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
			value = (Double) method.invoke(this, null);
			return value;
		} catch (Exception e) {
			return null;
		}

		// if (myInstruction.equals("sum")) {
		// value = sum();
		// } else if (myInstruction == "difference") {
		// value = difference();
		// } else if (myInstruction == "product") {
		// value = product();
		// } else if (myInstruction == "quotient") {
		// value = quotient();
		// } else if (myInstruction == "remainder") {
		// value = remainder();
		// } else if (myInstruction == "minus") {
		// value = minus();
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
