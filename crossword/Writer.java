package crossword;

import java.io.IOException;

/**
 * Interfejs reprezentuj¹cy funkcje s³u¿ace do zapisywanie krzy¿ówek do pliku.
 * @author redi
 * @version %I%, %G%
 * @see crossword.Crossword
 */
public interface Writer {
	
	/**
	 * Zapisuje okreœlon¹ krzy¿ówkê do pliku.
	 * @param cw krzy¿ówka do zapisania
	 * @return ID zapisanej krzy¿ówki (czas wywo³ania funkcji w milisekundach)
	 * @throws IOException wyj¹tek, gdy nie uda³o sie zapisaæ pliku
	 */
	public long Write(Crossword cw) throws IOException;
	
	/**
	 * Czas wywo³ania funkcji w milisekundach, równy ID zapisanej krzy¿ówki i nazwie pliku z ni¹.
	 * @return ID uzyskany czas
	 */
	public long getUniqueID();

}
