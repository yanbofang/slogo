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
}
