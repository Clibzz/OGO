package Style;

import java.awt.Color;
import java.awt.Font;

/** <p>Style staat voor Indent, Color, Font and Leading.</p>
 * <p>De koppeling tussen style-nummer en item-level is nu direct:
 * in Slide wordt de style opgehaald voor een item
 * met als style-nummer het item-level.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Style {
	private static final String FONTNAME = "Helvetica";
	private final int indent;
	private final Color color;
	private final Font font;
	private final int fontSize;
	private final int leading;

	public Style(int indent, Color color, int points, int leading) {
		this.indent = indent;
		this.color = color;
		this.font = new Font(FONTNAME, Font.BOLD, fontSize=points);
		this.leading = leading;
	}

	public int getIndent() {
		return this.indent;
	}

	public Color getColor() {
		return this.color;
	}

	public int getLeading() {
		return this.leading;
	}

	public Font getFont(float scale) {
		return this.font.deriveFont(this.fontSize * scale);
	}

	public String toString() {
		return "["+ this.indent + "," + this.color + "; " + this.fontSize + " on " + this.leading +"]";
	}
}
