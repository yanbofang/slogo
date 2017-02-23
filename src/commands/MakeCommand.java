package commands;

public class MakeCommand extends Command {

	private String myVariable;
	private Double myValue;
	
	public MakeCommand(String instruction) {
		super(instruction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getNumberExpressions() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Double getValue() {
		// TODO Auto-generated method stub
		myValue = (Double) myArguments.get(1);
		return myValue;
	}

	@Override
	public void executeCommand() {
		// TODO Auto-generated method stub
		
	}
	
	

}
