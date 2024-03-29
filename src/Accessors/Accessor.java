package Accessors;

import Presentation.*;

import java.io.IOException;

/**
 * <p>Een Accessor maakt het mogelijk om gegevens voor een presentatie
 * te lezen of te schrijven.</p>
 * <p>Niet-abstracte subklassen moeten de load en de save methode implementeren.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public abstract class Accessor {

	public static Accessor getDemoAccessor() {
		return new DemoPresentation();
	}

	public Accessor() {
	}

	/**
	 * Load a presentation file
	 * @param p The presentation
	 * @param fn The file name
	 * @throws IOException Throws when a file can not be loaded
	 */
	abstract public void loadFile(Presentation p, String fn) throws IOException;

	/**
	 * Save a presentation file
	 * @param p The presentation
	 * @param fn The file name
	 * @throws IOException Throws when a file can not be saved
	 */
	abstract public void saveFile(Presentation p, String fn) throws IOException;

}
