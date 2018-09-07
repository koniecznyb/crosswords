package crossword;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import crossword.dictionary.InteliCwDB;

/**
 * Klasa implementuj�ca manager krzy��wek, umozliwiaj�cy wczytywanie, zapisywanie i generowanie nowych krzy��wek.
 * @author redi
 * @version %I%, %G%
 * @see crossword.CrosswordReader
 * @see crossword.CrosswordWriter
 */
public class CwBrowser {
	
	private String dir;
	private CrosswordReader reader;
	private CrosswordWriter writer;
	
	/**
	 * Domy�lny konstruktor.
	 * @param dir katalog do zapisywania, wczytywania krzy��wek
	 */
	public CwBrowser(String dir){
		
		this.dir = dir;
		reader = new CrosswordReader(dir);
		writer = new CrosswordWriter(dir);
		
	}

	/**
	 * Pobierz aktualny katalog.
	 * @return aktualny katalog
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * Zmien aktualny katalog.
	 * @param dir nowy katalog
	 */
	public void setDir(String dir) {
		this.dir = dir;
		
		reader.setDirectory(dir);
		writer.setDirectory(dir);
	}
	
	/**
	 * Otworz krzy��wk� o podanym ID.
	 * @param ID nazwa pliku krzy��wki
	 * @return otwarta krzy��wka
	 * @see crossword.Crossword
	 */
	public Crossword openCrosswordByID(long ID){
		
		Crossword cwd = null;
		try {
			cwd = new Crossword(20, 30, ID, dir);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cwd;
		
	}
	
	/**
	 * Pobierz wszystkie krzy��wki z katalogu.
	 * @return lista pobranych krzy��wek
	 * @see crossword.Reader#getAllCws()
	 */
	public LinkedList<Crossword> getAllCrosswords(){
		
		reader.getAllCws();
		
		return reader.getCrosswordlist();	
	}
	
	/**
	 * Zapisz podan� krzy��wk� do katalogu.
	 * @param cwd krzy��wka do zapisania
	 * @return ID zapisanej krzy��wki(nazwa pliku)
	 * @see crossword.Writer#Write(Crossword)
	 */
	public long saveCrossword(Crossword cwd){
		long ID = -1l;
		
		try {
			ID = writer.Write(cwd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ID;
		
	}
	
	/**
	 * Generuj krzy��wk� o podanych warto�ciach.
	 * @param width maksymalna szeroko�� has�a poziomego
	 * @param height d�ugo�� has�a pionowego
	 * @param db plik z baza danych hase�
	 * @return wygenerowana krzy��wka
	 * @throws Exception gdy generowanie nie powiod�o si�
	 * @see crossword.BasicStrategy
	 */
	public Crossword generateCrossword(int width, int height, InteliCwDB db) throws Exception{
	
		Strategy strategy = new BasicStrategy();
		Crossword crossword = new Crossword(width, height, db, -1l);
		crossword.generate(strategy);
			
		return crossword;
	}
	
}
