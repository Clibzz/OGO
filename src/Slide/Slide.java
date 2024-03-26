package Slide;

import Style.*;
import Items.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

/** <p>Een slide. Deze klasse heeft tekenfunctionaliteit.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide {
	private final static int WIDTH = 1200;
	private final static int HEIGHT = 800;
	protected String title;
	protected Vector<SlideItem> items;

	public Slide() {
		this.items = new Vector<>();
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}

	/**
	 * Add a SlideItem to the slide
	 * @param item A SlideItem
	 */
	public void append(SlideItem item) {
		this.items.addElement(item);
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	/**
	 * Create a TextItem and add it to the slide
	 * @param level The slide number
	 * @param message The message that has to be appended
	 */
	public void append(int level, String message) {
		append(new TextItem(level, message));
	}

	public Vector<SlideItem> getSlideItems() {
		return this.items;
	}

	public int getSize() {
		return this.items.size();
	}

	/**
	 * Draw the slide
	 * @param g The graphics context
	 * @param area The area of the slide
	 * @param view The image observer for tracking changes
	 */
	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);
	    int y = area.y;
	    SlideItem slideItem = new TextItem(0, getTitle());
	    Style style = StyleFactory.getStyle(slideItem.getLevel());
	    slideItem.draw(area.x, y, scale, g, style, view);
	    y += slideItem.getBoundingBox(g, view, scale, style).height;
	    for (int number = 0; number < getSize(); number++) {
	      slideItem = getSlideItems().elementAt(number);
	      style = StyleFactory.getStyle(slideItem.getLevel());
	      slideItem.draw(area.x, y, scale, g, style, view);
	      y += slideItem.getBoundingBox(g, view, scale, style).height;
	    }
	  }

	/**
	 * Get the scale to draw the slide
	 * @param area The area of the slide
	 * @return The scale
	 */
	private float getScale(Rectangle area) {
		return Math.min(((float)area.width) / ((float)WIDTH), ((float)area.height) / ((float)HEIGHT));
	}
}
