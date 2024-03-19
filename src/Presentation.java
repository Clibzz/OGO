import java.util.ArrayList;

/**
 * <p>Presentation houdt de slides in de presentatie bij.</p>
 * <p>Er is slechts ��n instantie van deze klasse aanwezig.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation {
	private String showTitle; // de titel van de presentatie
	private ArrayList<Slide> showList = null; // een ArrayList met de Slides
	private int currentSlideNumber = 0; // het slidenummer van de huidige Slide
	private SlideViewerComponent slideViewComponent; // de viewcomponent voor de Slides

	public Presentation() {
		this.slideViewComponent = null;
		clear();
	}

	public Presentation(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
		clear();
	}

	public int getSize() {
		return this.showList.size();
	}

	public String getTitle() {
		return this.showTitle;
	}

	public void setTitle(String nt) {
		this.showTitle = nt;
	}

	public void setShowView(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
	}

	public int getSlideNumber() {
		return this.currentSlideNumber;
	}

	public void setSlideNumber(int number) {
		if (number <= (this.showList.size() - 1) && number >= 0) {
			this.currentSlideNumber = number;
			if (this.slideViewComponent != null) {
				this.slideViewComponent.update(this, getSlide(this.currentSlideNumber));
			}
		}
	}

	// ga naar de vorige slide tenzij je aan het begin van de presentatie bent
	public void prevSlide() {
		if (this.currentSlideNumber > 0) {
			setSlideNumber(this.currentSlideNumber - 1);
	    }
	}

	// Ga naar de volgende slide tenzij je aan het einde van de presentatie bent.
	public void nextSlide() {
		if (this.currentSlideNumber < this.showList.size()) {
			setSlideNumber(this.currentSlideNumber + 1);
		}
	}

	// Verwijder de presentatie, om klaar te zijn voor de volgende
	public void clear() {
		this.showList = new ArrayList<>();
		setSlideNumber(-1);
	}

	// Voeg een slide toe aan de presentatie
	public void append(Slide slide) {
		this.showList.add(slide);
	}

	// Geef een slide met een bepaald slidenummer
	public Slide getSlide(int number) {
		if (number < 0 || number >= getSize()){
			return null;
	    }

		return this.showList.get(number);
	}

	public ArrayList<Slide> getSlides() {
		return this.showList;
	}

	public void exit(int n) {
		System.exit(n);
	}
}
