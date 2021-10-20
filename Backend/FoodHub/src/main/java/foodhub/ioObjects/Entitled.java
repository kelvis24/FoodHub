package foodhub.ioObjects;

import java.util.Iterator;
import java.util.List;

public interface Entitled {

	public String getTitle();
	
	public static Entitled findByTitle(List<? extends Entitled> list, String title) {
		Iterator<? extends Entitled> it = list.iterator();
		while (it.hasNext()) {
			Entitled e = it.next();
			if (e.getTitle().equals(title))
				return e;
		}
		return null;
	}
	
}
