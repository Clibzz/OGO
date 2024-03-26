package Menu;

import Accessors.Accessor;
import Accessors.XMLAccessor;
import Presentation.Presentation;
import Util.AboutBox;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import static Menu.MenuLabels.*;

/** <p>De controller voor het menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {
	/**
	 * The frame, only used as parent for the Dialogs
	 */
	private final Frame parent;
	/**
	 * The presentation
	 */
	private final Presentation presentation;

	public MenuController(Frame frame, Presentation pres) {
		this.parent = frame;
		this.presentation = pres;
		buildFileMenu();
		buildViewMenu();
		buildHelpMenu();
	}

	/**
	 * Method to create a menu item including its functionalities
	 * @param menuItem The menu item
	 * @param method The functionalities the menu item should have
	 * @return The menu item
	 */
	private MenuItem createMenuItem(MenuItem menuItem, Runnable method) {
		menuItem.addActionListener(e -> method.run());
		return menuItem;
	}

	/**
	 * Method to create a shortcut for menu items
	 * @param name The name of the menu item
	 * @return The menu item
	 */
	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, name.equals("Next") ? new MenuShortcut(name.charAt(0), true) : new MenuShortcut(name.charAt(0)));
	}

	/**
	 * Method to save a presentation file
	 */
	private void saveFile() {
		Accessor xmlAccessor = new XMLAccessor();
		try {
			xmlAccessor.saveFile(presentation, SAVEFILE);
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(parent, IOEX + exc,
					SAVEERR, JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Method to ope a presentation, in this case the test presentation
	 */
	private void openPresentation() {
		presentation.clear();
		Accessor xmlAccessor = new XMLAccessor();
		try {
			xmlAccessor.loadFile(presentation, TESTFILE);
			presentation.setSlideNumber(0);
			parent.repaint();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(parent, IOEX + ex, LOADERR, JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Method to create a new presentation
	 */
	private void newPresentation() {
		presentation.clear();
		parent.repaint();
	}

	/**
	 * Build the file menu with all its items
	 */
	private void buildFileMenu() {
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(createMenuItem(mkMenuItem(OPEN), this::openPresentation));
		fileMenu.add(createMenuItem(mkMenuItem(NEW), this::newPresentation));
		fileMenu.add(createMenuItem(mkMenuItem(SAVE), this::saveFile));
		fileMenu.add(createMenuItem(mkMenuItem(EXIT), () -> presentation.exit(0)));
		add(fileMenu);
	}

	/**
	 * Get the slide number the user has entered
	 * @return The slide number as integer
	 */
	private int getSlideNumberFromInput() {
		String slideNumber = JOptionPane.showInputDialog(PAGENR);
		return Integer.parseInt(slideNumber);
	}

	/**
	 * Method to build the view menu with all its items
	 */
	private void buildViewMenu() {
		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(createMenuItem(mkMenuItem(NEXT), presentation::nextSlide));
		viewMenu.add(createMenuItem(mkMenuItem(PREV), presentation::prevSlide));
		viewMenu.add(createMenuItem(mkMenuItem(GOTO), () -> presentation.setSlideNumber(getSlideNumberFromInput() - 1)));
		add(viewMenu);
	}

	/**
	 * Method to build the help menu with all its items
	 */
	private void buildHelpMenu() {
		Menu helpMenu = new Menu(HELP);
		helpMenu.add(createMenuItem(mkMenuItem(ABOUT), () -> AboutBox.show(parent)));
		setHelpMenu(helpMenu);
	}
}
