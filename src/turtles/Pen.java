package turtles;

/**
 * Keeps track of all states of the pen for a specific turtle
 * @author Henry
 *
 */
public class Pen {
	boolean penDown;
	Double penSize;
	Double penColor;
	
	/**
	 * Sets default to show the pen, use pen size of 1, and have a black pen color
	 */
	public Pen() {
		penDown = true;
		penSize = 1.0;
		penColor = 0.0;
	}
	
	/**
	 * @return true (pen is down and currently showing) or false (pen is up and not showing)
	 */
	public boolean showPen() {
		return penDown;
	}
	
	/**
	 * Sets pen to show (true) or not to show (false)
	 * @param b - true/false
	 */
	public void setPen(boolean b) {
		penDown = b;
	}
	
	/**
	 * @return - Pen size
	 */
	public double getSize(){
		return penSize;
	}
	
	/**
	 * @param d - size for pen to be set to
	 */
	public void setSize(double d){
		penSize = d;
	}
	
	/**
	 * @return current Pen Color in its index according to ColorPalette
	 */
	public double getColor(){
		return penColor;
	}
	
	/**
	 * @param d - index in ColorPalette for the pen to be set to
	 */ 
	public void setColor(double d){
		penColor = d;
	}
	
}
