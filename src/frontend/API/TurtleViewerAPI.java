package frontend.API;

import coordinate.Coordinate;

/**
 * API for turtle area subcomponent
 * The graphical area that contains the turtle
 * @author Gordon
 *
 */
public interface TurtleViewerAPI extends SubcomponentAPI{
	/**
	 * Get the size of the subcomponent as the bottom-rightmost point
	 * @return
	 * Coordinate containing the bottom-rightmost point
	 */
	public Coordinate getBounds();
	
	/**
	 * Change the background color of the turtle area
	 * @param a
	 * 6 hexidecimal string of color (ex: 3c3c3c)
	 */
	public void setBackgroundColor(String a);
}
