package commands;
import java.lang.reflect.Method;

public class MathCommand extends Command{
	
	private Double myValue;
	
	public MathCommand(String instruction, String[] args) {
		super(instruction, args);
		myValue = calculateValue();
	}

	@Override
	public Double getValue() {
		return this.myValue;
	}
	
	@Override
	public void executeCommand() {
		return;
	}
	
	
	private Double calculateValue() {
		Double value = 0.0;
		if(myInstruction == "sum"){
			sum();
		}else if (myInstruction == "difference"){
			difference();
		}else if (myInstruction == "product"){
			product();
		}else if (myInstruction == "quotient"){
			quotient();
		}else if (myInstruction == "remainder"){
			remainder();
		}else if (myInstruction == "minus"){
			minus();
		}
		return value;
	}

	
	
	private Double sum(){
		return (Double) myArguments.get(0) + (Double) myArguments.get(1);
	}
	
	private Double difference(){
		return (Double) myArguments.get(0) - (Double) myArguments.get(1);
	}
	
	private Double product(){
		return (Double) myArguments.get(0) * (Double) myArguments.get(1);
	}
	
	private Double quotient(){
		return (Double) myArguments.get(0) / (Double) myArguments.get(1);
	}
	
	private Double remainder(){
		return (Double) myArguments.get(0) % (Double) myArguments.get(1);
	}
	
	private Double minus(){
		return -1.0 *(Double) myArguments.get(0);
	}

}
