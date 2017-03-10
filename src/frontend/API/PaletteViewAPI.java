package frontend.API;

import javafx.scene.Parent;
import javafx.scene.image.Image;


/**
 * API for the palette subcomponent
 * The graphical area that contains the color and image palettes 
 * and lets the user switch between existing options
 * @author Faith
 */
public interface PaletteViewAPI extends SubcomponentAPI{
		
	
	/**
	 * Adds string and index pair to the turtlePalette
	 * @param turtle
	 * string for image of turtle
	 * @param index
	 * user assigned index for the new turtle 
	 */
	public void updateTurtlePalette(String turtle, double index);
	

	/**
	 * Adds color and index pair to the colorPalette
	 * @param color
	 * string for new color to be added
	 * @param index
	 * user assigned index for the new color 
	 */
	public void updateColorPalette(String color, double index);

	/**
	 * Returns an Image based on the value associated with d in turtlePalette
	 * @param d
	 * an index that may or may not be in turtlePalette
	 */
	public Image getImageOf(double d);
}
