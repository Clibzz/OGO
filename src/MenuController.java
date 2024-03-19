import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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

	private final Frame parent; // het frame, alleen gebruikt als ouder voor de Dialogs
	private final Presentation presentation; // Er worden commando's gegeven aan de presentatie

	public MenuController(Frame frame, Presentation pres) {
		this.parent = frame;
		this.presentation = pres;
		buildFileMenu();
		buildViewMenu();
		buildHelpMenu();
	}

	private MenuItem createMenuItem(MenuItem menuItem, Runnable method) {
		menuItem.addActionListener(e -> method.run());
		return menuItem;
	}

	private void saveFile() {
		Accessor xmlAccessor = new XMLAccessor();
		try {
			xmlAccessor.saveFile(presentation, MenuLabels.SAVEFILE);
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(parent, MenuLabels.IOEX + exc,
					MenuLabels.SAVEERR, JOptionPane.ERROR_MESSAGE);
		}
	}

	private void openPresentation() {
		presentation.clear();
		Accessor xmlAccessor = new XMLAccessor();
		try {
			xmlAccessor.loadFile(presentation, MenuLabels.TESTFILE);
			presentation.setSlideNumber(0);
			parent.repaint();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(parent, MenuLabels.IOEX + ex, MenuLabels.LOADERR, JOptionPane.ERROR_MESSAGE);
		}
	}

	private void newPresentation() {
		presentation.clear();
		parent.repaint();
	}

	private void buildFileMenu() {
		Menu fileMenu = new Menu(MenuLabels.FILE);
		fileMenu.add(createMenuItem(mkMenuItem(MenuLabels.OPEN), this::openPresentation));
		fileMenu.add(createMenuItem(mkMenuItem(MenuLabels.NEW), this::newPresentation));
		fileMenu.add(createMenuItem(mkMenuItem(MenuLabels.SAVE), this::saveFile));
		fileMenu.add(createMenuItem(mkMenuItem(MenuLabels.EXIT), () -> presentation.exit(0)));
		add(fileMenu);
	}

	private int getSlideNumberFromInput() {
		String slideNumber = JOptionPane.showInputDialog(MenuLabels.PAGENR);
		return Integer.parseInt(slideNumber);
	}

	private void buildViewMenu() {
		Menu viewMenu = new Menu(MenuLabels.VIEW);
		viewMenu.add(createMenuItem(mkMenuItem(MenuLabels.NEXT), presentation::nextSlide));
		viewMenu.add(createMenuItem(mkMenuItem(MenuLabels.PREV), presentation::prevSlide));
		viewMenu.add(createMenuItem(mkMenuItem(MenuLabels.GOTO), () -> presentation.setSlideNumber(getSlideNumberFromInput() - 1)));
		add(viewMenu);
	}

	private void buildHelpMenu() {
		Menu helpMenu = new Menu(MenuLabels.HELP);
		helpMenu.add(createMenuItem(mkMenuItem(MenuLabels.ABOUT), () -> AboutBox.show(parent)));
		setHelpMenu(helpMenu);
	}

	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, name.equals("Next") ? new MenuShortcut(name.charAt(0), true) : new MenuShortcut(name.charAt(0)));
	}
}
