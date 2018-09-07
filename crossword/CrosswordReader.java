package crossword;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * Klasa reprezentuj�ca funkcje s�u�ace do wczytywania krzy��wek z pliku.
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
	 * Zwraca aktualna list� krzy��wek.
	 * @return LinkedList krzy��wek
	 */
	public LinkedList<Crossword> getCrosswordlist() {
		return crosswordlist;
	}

	/**
	 * Zmienia katalog, z kt�rego czytane s� krzy��wki.
	 * @param directory nowy katalog
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/**
	 * Konstruktor z wybranym katalogiem
	 * @param dir katalog, z kt�rego czytane s� krzy��wki
	 */
	public CrosswordReader(String dir) {
		directory = dir;
		crosswordlist = new LinkedList<Crossword>();
	}

}
