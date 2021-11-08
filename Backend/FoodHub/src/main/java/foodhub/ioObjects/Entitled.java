package foodhub.ioObjects;

import java.util.List;

/**
 * NOT USED.
 * An interface for easily sifting through information with respect to titles.
 * 		Useful for searching category lists for a category with a matching title.
 * 		Made obsolete when we switched to ids as the primary way of indexing entities.
 * @author 1_CW_2
 */
public interface Entitled {

	/**
	 * A getter for a title field, or equivalent
	 * @return Returns what is considered the title of the entity
	 */
	public String getTitle();
	
	/**
	 * Returns the first instance of an item with a specified title in a list, or null
	 * @param list A list of Entitled objects
	 * @param title A title used for comparison
	 * @return The first instance of an item with the specified title in a list, or null
	 */
	public static Entitled findByTitle(List<? extends Entitled> list, String title) {
		for (Entitled e : list) {if (e.getTitle().equals(title)) return e;}
		return null;
	}
	
}
