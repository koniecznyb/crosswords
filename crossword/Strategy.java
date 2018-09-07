package crossword;


/**
 * Abstrakcyjna klasa strategii generowania krzy��wki.
 * @author redi
 * @version %I%, %G%
 * @see crossword.CwEntry
 * @see crossword.Board
 */

public abstract class Strategy {
	
	/**
	 * Funkcja znajduje s�owo o losowej d�ugo�ci
	 * @param cw krzy��wka z baz� danych hase�
	 * @throws Exception 
	 */
	public abstract CwEntry findEntry(Crossword cw) throws Exception;
	
	/**
	 * Funkcja uaktualnia tablice krzy��wki o podane has�o.
	 * @param b tablica przechowywuj�ca konkretne pola krzy��wki
	 * @param e Has�o (i podpowied�), kt�re zostanie zaaktualizowane na tablicy
	 */
	public abstract void updateBoard(Board b, CwEntry e);
}
