package crossword;

import java.io.IOException;

/**
 * Interfejs reprezentuj�cy funkcje s�u�ace do zapisywanie krzy��wek do pliku.
 * @author redi
 * @version %I%, %G%
 * @see crossword.Crossword
 */
public interface Writer {
	
	/**
	 * Zapisuje okre�lon� krzy��wk� do pliku.
	 * @param cw krzy��wka do zapisania
	 * @return ID zapisanej krzy��wki (czas wywo�ania funkcji w milisekundach)
	 * @throws IOException wyj�tek, gdy nie uda�o sie zapisa� pliku
	 */
	public long Write(Crossword cw) throws IOException;
	
	/**
	 * Czas wywo�ania funkcji w milisekundach, r�wny ID zapisanej krzy��wki i nazwie pliku z ni�.
	 * @return ID uzyskany czas
	 */
	public long getUniqueID();

}
