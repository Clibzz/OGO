package Items;

import Slide.SlideItem;
import Style.Style;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;


/** <p>De klasse voor een Bitmap item</p>
 * <p>Bitmap items hebben de verantwoordelijkheid om zichzelf te tekenen.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class BitmapItem extends SlideItem
{
  private BufferedImage bufferedImage;
  private final String imageName;
  
  protected static final String FILE = "Bestand ";
  protected static final String NOTFOUND = " Niet gevonden";

	public BitmapItem(int level, String name) {
		super(level);
		this.imageName = name;
		try {
			this.bufferedImage = ImageIO.read(new File(this.imageName));
		}
		catch (IOException e) {
			System.err.println(FILE + this.imageName + NOTFOUND) ;
		}
	}

	public String getName() {
		return this.imageName;
	}

	/**
	 * Get the bounding box of an image
	 * @param g The graphics context
	 * @param observer The image observer that tracks changes
	 * @param scale The scaling factor that's applied to the object
	 * @param myStyle The style that is applied to the object
	 * @return A rectangle which represents the bounding box
	 */
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
		return new Rectangle((int) (myStyle.getIndent() * scale), 0,
				(int) (this.bufferedImage.getWidth(observer) * scale),
				((int) (myStyle.getLeading() * scale)) +
				(int) (this.bufferedImage.getHeight(observer) * scale));
	}

	/**
	 * Draw the image
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param scale The scaling factor that's applied to the object
	 * @param g The graphics context
	 * @param myStyle The style that is applied to the object
	 * @param observer The image observer that tracks changes
	 */
	public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
		int width = x + (int) (myStyle.getIndent() * scale);
		int height = y + (int) (myStyle.getLeading() * scale);
		g.drawImage(this.bufferedImage, width, height,(int) (this.bufferedImage.getWidth(observer)*scale),
                (int) (this.bufferedImage.getHeight(observer)*scale), observer);
	}

	public String toString() {
		return "BitmapItem[" + getLevel() + "," + this.imageName + "]";
	}
}
