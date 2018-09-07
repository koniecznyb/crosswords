package crossword;

import java.util.LinkedList;

/**
 * Interfejs reprezentuj�cy funkcje s�u�ace do wczytywania krzy��wek z pliku.
 * @author redi
 * @version %I%, %G%
 * @see crossword.Crossword
 */
public interface Reader {
	
	LinkedList <Crossword> crosswords = new LinkedList<Crossword>();

	/**
	 * Wczytuje wszystkie krzy��wki w danym katalogu.
	 */
	public void getAllCws();

}
