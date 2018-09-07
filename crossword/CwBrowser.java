package crossword;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import crossword.dictionary.InteliCwDB;

/**
 * Klasa implementuj¹ca manager krzy¿ówek, umozliwiaj¹cy wczytywanie, zapisywanie i generowanie nowych krzy¿ówek.
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
	 * Domyœlny konstruktor.
	 * @param dir katalog do zapisywania, wczytywania krzy¿ówek
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
	 * Otworz krzy¿ówkê o podanym ID.
	 * @param ID nazwa pliku krzy¿ówki
	 * @return otwarta krzy¿ówka
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
	 * Pobierz wszystkie krzy¿ówki z katalogu.
	 * @return lista pobranych krzy¿ówek
	 * @see crossword.Reader#getAllCws()
	 */
	public LinkedList<Crossword> getAllCrosswords(){
		
		reader.getAllCws();
		
		return reader.getCrosswordlist();	
	}
	
	/**
	 * Zapisz podan¹ krzy¿ówkê do katalogu.
	 * @param cwd krzy¿ówka do zapisania
	 * @return ID zapisanej krzy¿ówki(nazwa pliku)
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
	 * Generuj krzy¿ówkê o podanych wartoœciach.
	 * @param width maksymalna szerokoœæ has³a poziomego
	 * @param height d³ugoœæ has³a pionowego
	 * @param db plik z baza danych hase³
	 * @return wygenerowana krzy¿ówka
	 * @throws Exception gdy generowanie nie powiod³o siê
	 * @see crossword.BasicStrategy
	 */
	public Crossword generateCrossword(int width, int height, InteliCwDB db) throws Exception{
	
		Strategy strategy = new BasicStrategy();
		Crossword crossword = new Crossword(width, height, db, -1l);
		crossword.generate(strategy);
			
		return crossword;
	}
	
}
