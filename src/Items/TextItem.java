package Items;

import Slide.*;
import Style.Style;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.ArrayList;

/** <p>Een tekst item.</p>
 * <p>Een TextItem heeft tekenfunctionaliteit.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class TextItem extends SlideItem
{
	private final String text;

	public TextItem(int level, String string) {
		super(level);
		this.text = string;
	}

	public String getText() {
		return this.text == null ? "" : this.text;
	}

	/**
	 * Creates an AttributedString object with the specified style and scale.
	 * @param style The style of the text.
	 * @param scale The scaling factor.
	 * @return An AttributedString object representing the text with the given style and scale.
	 */
	public AttributedString getAttributedString(Style style, float scale) {
		AttributedString attrStr = new AttributedString(getText());
		attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, this.text.length());
		return attrStr;
	}

	/**
	 * Calculates the bounding box of the text based on its layout, style, and scale
	 * @param g The graphics context used for rendering
	 * @param observer The image observer
	 * @param scale The scaling factor
	 * @param myStyle The style
	 * @return The bounding box rectangle
	 */
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, 
			float scale, Style myStyle) {
		List<TextLayout> layouts = getLayouts(g, myStyle, scale);
		int xsize = 0, ysize = (int) (myStyle.getLeading() * scale);
        for (TextLayout layout : layouts) {
            Rectangle2D bounds = layout.getBounds();
            if (bounds.getWidth() > xsize) {
                xsize = (int) bounds.getWidth();
            }
            if (bounds.getHeight() > 0) {
                ysize += bounds.getHeight();
            }
            ysize += layout.getLeading() + layout.getDescent();
        }
		return new Rectangle((int) (myStyle.getIndent() * scale), 0, xsize, ysize );
	}

	/**
	 * Draws the text at the specified location with the given style and scale
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param scale The scaling factor
	 * @param g The graphics context
	 * @param myStyle The style of the text
	 * @param o The image observer
	 */
	public void draw(int x, int y, float scale, Graphics g, 
			Style myStyle, ImageObserver o) {
		if (this.text == null || this.text.isEmpty()) {
			return;
		}
		List<TextLayout> layouts = getLayouts(g, myStyle, scale);
		Point pen = new Point(x + (int)(myStyle.getIndent() * scale),
				y + (int) (myStyle.getLeading() * scale));
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(myStyle.getColor());
        for (TextLayout layout : layouts) {
            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y += layout.getDescent();
        }
	}

	/**
	 * Generates and returns a list of text layouts based on the provided graphics context, style and scale
	 * @param g The graphics context
	 * @param s The style
	 * @param scale The scaling factor
	 * @return A list of text layouts
	 */
	private List<TextLayout> getLayouts(Graphics g, Style s, float scale) {
		List<TextLayout> layouts = new ArrayList<>();
		AttributedString attrStr = getAttributedString(s, scale);
    	Graphics2D g2d = (Graphics2D) g;
    	FontRenderContext frc = g2d.getFontRenderContext();
    	LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
    	float wrappingWidth = (Slide.getWidth() - s.getIndent()) * scale;
    	while (measurer.getPosition() < getText().length()) {
    		TextLayout layout = measurer.nextLayout(wrappingWidth);
    		layouts.add(layout);
    	}
    	return layouts;
	}

	public String toString() {
		return "TextItem[" + getLevel() + "," + getText() + "]";
	}
}
