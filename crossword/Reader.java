package crossword;

import java.util.LinkedList;

/**
 * Interfejs reprezentuj¹cy funkcje s³u¿ace do wczytywania krzy¿ówek z pliku.
 * @author redi
 * @version %I%, %G%
 * @see crossword.Crossword
 */
public interface Reader {
	
	LinkedList <Crossword> crosswords = new LinkedList<Crossword>();

	/**
	 * Wczytuje wszystkie krzy¿ówki w danym katalogu.
	 */
	public void getAllCws();

}
