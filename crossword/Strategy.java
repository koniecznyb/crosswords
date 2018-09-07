package crossword;


/**
 * Abstrakcyjna klasa strategii generowania krzy¿ówki.
 * @author redi
 * @version %I%, %G%
 * @see crossword.CwEntry
 * @see crossword.Board
 */

public abstract class Strategy {
	
	/**
	 * Funkcja znajduje s³owo o losowej d³ugoœci
	 * @param cw krzy¿ówka z baz¹ danych hase³
	 * @throws Exception 
	 */
	public abstract CwEntry findEntry(Crossword cw) throws Exception;
	
	/**
	 * Funkcja uaktualnia tablice krzy¿ówki o podane has³o.
	 * @param b tablica przechowywuj¹ca konkretne pola krzy¿ówki
	 * @param e Has³o (i podpowiedŸ), które zostanie zaaktualizowane na tablicy
	 */
	public abstract void updateBoard(Board b, CwEntry e);
}
