import java.util.ArrayList;

/**
 * <p>Presentation houdt de slides in de presentatie bij.</p>
 * <p>Er is slechts een instantie van deze klasse aanwezig.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation {
	private String showTitle;
	private ArrayList<Slide> showList;
	private int currentSlideNumber = 0;
	private SlideViewerComponent slideViewComponent;

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

	/**
	 * Get the current slide number
	 * @return The current slide number
	 */
	public int getSlideNumber() {
		return this.currentSlideNumber;
	}

	/**
	 * Set the current slide number
	 * @param number The number of the slide that has to be set
	 */
	public void setSlideNumber(int number) {
		if (number <= (this.showList.size() - 1) && number >= 0) {
			this.currentSlideNumber = number;
			if (this.slideViewComponent != null) {
				this.slideViewComponent.update(this, getSlide(this.currentSlideNumber));
			}
		}
	}

	/**
	 * Navigate to the previous slide
	 */
	public void prevSlide() {
		if (this.currentSlideNumber > 0) {
			setSlideNumber(this.currentSlideNumber - 1);
	    }
	}

	/**
	 * Navigate to the next slide
	 */
	public void nextSlide() {
		if (this.currentSlideNumber < this.showList.size()) {
			setSlideNumber(this.currentSlideNumber + 1);
		}
	}

	/**
	 * Delete the presentation
	 */
	public void clear() {
		this.showList = new ArrayList<>();
		setSlideNumber(-1);
	}

	/**
	 * Add a slide to the presentation
	 * @param slide A slide
	 */
	public void append(Slide slide) {
		this.showList.add(slide);
	}

	/**
	 * Get a slide with a specific number
	 * @param number The slide number
	 * @return The slide with the given number
	 */
	public Slide getSlide(int number) {
		if (number < 0 || number >= getSize()){
			return null;
	    }

		return this.showList.get(number);
	}

	/**
	 * Get all the slides of a presentation
	 * @return A list with all the slides of  presentation
	 */
	public ArrayList<Slide> getSlides() {
		return this.showList;
	}

	/**
	 * Close the application
	 * @param n The exit code
	 */
	public void exit(int n) {
		System.exit(n);
	}
}
