package Slide;

import Presentation.*;

import javax.swing.*;
import java.awt.*;


/** <p>SlideViewerComponent is een grafische component die Slides kan laten zien.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent {
		
	private Slide slide;
	private final Font labelFont;
	private Presentation presentation;
	private final JFrame frame;
	
	private static final long serialVersionUID = 227L;
	
	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;

	public SlideViewerComponent(Presentation pres, JFrame frame) {
		setBackground(BGCOLOR); 
		this.presentation = pres;
		this.labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
		this.frame = frame;
	}

	/**
	 * Get the preferred size of the dimension
	 * @return The Dimension with the preferred size
	 */
	public Dimension getPreferredSize() {
		return new Dimension(Slide.getWidth(), Slide.getHeight());
	}

	/**
	 * Update the data of a slide
	 * @param presentation The presentation of which the slide is part of
	 * @param data The slide
	 */
	public void update(Presentation presentation, Slide data) {
		if (data == null) {
			repaint();
			return;
		}
		this.presentation = presentation;
		this.slide = data;
		repaint();
		this.frame.setTitle(presentation.getTitle());
	}

	/**
	 * Paint the SlideViewerComponent
	 * @param g the <code>Graphics</code> object to protect
	 */
	public void paintComponent(Graphics g) {
		g.setColor(BGCOLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);
		if (this.presentation.getSlideNumber() < 0 || this.slide == null) {
			return;
		}
		g.setFont(this.labelFont);
		g.setColor(COLOR);
		g.drawString("Slide " + (1 + this.presentation.getSlideNumber()) + " of " +
				this.presentation.getSize(), XPOS, YPOS);
		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
		this.slide.draw(g, area, this);
	}
}
