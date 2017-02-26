package commands;

public interface Command {

	void add(Object... args);

	String getInstruction();

	Object getArguments(int k);

	boolean isFinished();

	Double getValue();

	void executeCommand();

}
