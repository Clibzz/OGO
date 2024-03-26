package Slide;

import Style.Style;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/** <p>De abstracte klasse voor een item op een Slide<p>
 * <p>Alle SlideItems hebben tekenfunctionaliteit.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public abstract class SlideItem {
	private final int level;

	public SlideItem(int lev) {
		this.level = lev;
	}

	public int getLevel() {
		return this.level;
	}

	public abstract Rectangle getBoundingBox(Graphics g, 
			ImageObserver observer, float scale, Style style);

	/**
	 * Draw the item
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param scale The scaling factor that's applied to the object
	 * @param g The graphics context
	 * @param style The style that's applied to the object
	 * @param observer  The image observer that tracks changes
	 */
	public abstract void draw(int x, int y, float scale, 
			Graphics g, Style style, ImageObserver observer);
}
