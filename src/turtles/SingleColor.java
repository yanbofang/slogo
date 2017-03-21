package turtles;

/**
 * Creates a specific color according to the index in ColorPallette
 * @author Gordon
 *
 */
public class SingleColor {
	private double index;
	private String color;
	
	/**
	 * @param d - index of color in ColorPalette
	 * @param s - String of color name
	 */
	public SingleColor(double d, String s){
		index = d;
		color = s;
	}
	
	/**
	 * @return index of color according to ColorPalette
	 */
	public double getIndex(){
		return index;
	}
	
	/**
	 * @return String name of color
	 */
	public String getColor(){
		return color;
	}
}
