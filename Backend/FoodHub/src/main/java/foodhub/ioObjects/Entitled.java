package foodhub.ioObjects;

import java.util.List;

public interface Entitled {

	public String getTitle();
	
	public static Entitled findByTitle(List<? extends Entitled> list, String title) {
		for (Entitled e : list) {if (e.getTitle().equals(title)) return e;}
		return null;
	}
	
}
