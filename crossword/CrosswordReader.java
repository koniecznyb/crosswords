package crossword;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * Klasa reprezentuj¹ca funkcje s³u¿ace do wczytywania krzy¿ówek z pliku.
 * @author redi
 * @version %I%, %G%
 * @see crossword.Crossword
 */
public class CrosswordReader implements Reader {

	private String directory;
	private LinkedList <Crossword> crosswordlist;
	
	/**
	 * @inheritDoc
	 */
	@Override
	public void getAllCws() {

		try{
			File folder = new File(directory);
			File[] listOfFiles = folder.listFiles();
			
			for(int i=0; i<listOfFiles.length; i++){
				if (listOfFiles[i].isFile()) {
					if(listOfFiles[i].getName().substring(0, 10).equals(".crossword")){
						try {
							long fileID = Long.parseLong(listOfFiles[i].getName().substring(0, 10));
							Crossword krzyzowka = new Crossword(20,30, fileID, directory);
							crosswordlist.add(krzyzowka);
						} catch (FileNotFoundException e) {
	//						 TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
	
				}
			}
		}
		catch(Exception ex)
		{
			return;
		}		
	}
	
	/**
	 * Zwraca aktualna listê krzy¿ówek.
	 * @return LinkedList krzy¿ówek
	 */
	public LinkedList<Crossword> getCrosswordlist() {
		return crosswordlist;
	}

	/**
	 * Zmienia katalog, z którego czytane s¹ krzy¿ówki.
	 * @param directory nowy katalog
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/**
	 * Konstruktor z wybranym katalogiem
	 * @param dir katalog, z którego czytane s¹ krzy¿ówki
	 */
	public CrosswordReader(String dir) {
		directory = dir;
		crosswordlist = new LinkedList<Crossword>();
	}

}
