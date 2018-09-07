package crossword;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import crossword.dictionary.*;
/**
 * Klasa reprezentuj�ca dan� krzy��wke.
 * @author redi
 * @version %I%, %G%
 * @see crossword.CwEntry
 */
public class Crossword {

	private LinkedList <CwEntry> entries;
	private Board b;
	private InteliCwDB cwdb;
	private final long ID;
	
	/**
	 * Pobierz ID krzy��wki.
	 * @return ID krzy��wki
	 */
	public long getID() {
		return ID;
	}
	
	/**
	 * Pobierz wszystkie podpowiedzi dla danej krzy��wki.
	 * @return lista z wszystkimi podpowiedziami krzy��wki
	 */
	public LinkedList <String> getClues(){
		
		LinkedList<String> clues = new LinkedList<String>();
		
		for(int i=0; i<entries.size(); i++){
						
			clues.add(entries.get(i).getClue());
			
		}
		return clues;
	}

	/**
	 * Konstruktor wczytuj�cy krzy��wk� z pliku.
	 * @param width szeroko�� krzy��wki
	 * @param height wysoko�� krzy��wki
	 * @param ID nazwa pliku, z kt�rego zostanie odczytana krzy��wka
	 * @param directory folder, w kt�rym znajduje sie plik krzy��wki
	 * @throws FileNotFoundException wyj�tek, gdy nie znaleziono pliku
	 */
	Crossword(int width, int height, long ID, String directory) throws FileNotFoundException{
				
		Strategy s = new BasicStrategy();
		entries = new LinkedList<CwEntry>();
		b = new Board(width, height);
		this.ID = ID;
		
		File CwDBfile = new File(directory + "/" +  ID + ".crossword");

    	Scanner in = new Scanner(CwDBfile, "UTF-8");

    	
    	while(in.hasNextLine()){

    		String currentLine = in.nextLine();
    		
    		int x = Integer.parseInt(currentLine.substring(0, 1));
    		int y = Integer.parseInt(currentLine.substring(2, 3));
    		
    		String directionInString = (currentLine.substring(4, 9));
    		directionInString = directionInString.trim();
    		Direction dir = Direction.valueOf(directionInString);
    		
    		String word = currentLine.substring(9);
    		word = word.trim();
    		
    		String clue = in.nextLine();
    		
    		CwEntry entry = new CwEntry(word, clue);
    		entry.setDir(dir);
    		entry.setX(x);
    		entry.setY(y);
    		
    		entries.add(entry);
    		s.updateBoard(b, entry);
    		
    	}
    	
    	in.close();
		
	}
	
	/**
	 * Konstruktor tworz�cy now� krzy��wk�.
	 * @param width szeroko�� krzy��wki
	 * @param height wysoko�� krzy��wki
	 * @param cwdb baza danych hase� krzy��wki
	 * @param ID ID krzy��wki
	 */
	Crossword(int width, int height, InteliCwDB cwdb, long ID){
		
		setCwDB(cwdb);
		entries = new LinkedList<CwEntry>();
		b = new Board(width, height);
		this.ID = ID;
		
	}	
	
	/**
	 * Sprawdza czy lista entries posiada jakie� elementy.
	 * @return true je�eli nie posiada, false je�eli posiada
	 */
	public boolean isCrosswordEmpty(){
		
		if(entries.isEmpty())
			return true;
		else
			return false;
		
	}
	
	/**
	 * Zwraca read-only iterator do przegladania listy entries.
	 * @return read-only iterator
	 */
	public Iterator<CwEntry> getROEntryIter(){
		return Collections.unmodifiableList(entries).iterator();
	}
	
	/**
	 * Zwraca tablice krzy��wki.
	 * @return tablica krzy��wki
	 */
	public Board getBoardCopy(){
		return b;
	}
	
	/**
	 * Zwraca aktualna baz� danych hase� krzy��wki.
	 * @return aktualna baza danych hase� krzy��wki
	 */
	public InteliCwDB getCwDB(){
		return cwdb;
	}

	/**
	 * Zmienia baza danych hase� krzy��wki.
	 * @param cwdb nowa baza danych
	 */
	public void setCwDB(InteliCwDB cwdb){
		this.cwdb = cwdb;
	}
	
	/**
	 * Sprawdza czy krzy��wka zawiera podane has�o.
	 * @param word has�o do sprawdzenia
	 * @return true je�eli zawiera, false je�eli nie zawiera
	 */
	public boolean contains(String word){
		
		if(entries.isEmpty())
			throw new NullPointerException();
		
		for(CwEntry s : entries){
			if(word.compareTo(s.getWord()) == 0)
				return true;
		}
		return false;
	}
	
	/**
	 * Dodaje nowe has�o cwe do listy hase� i uaktualnia tablice.
	 * @param cwe nowe has�o do dodania
	 * @param s strategia za pomoc�, kt�rej aktualizowana jest tablica
	 */
	public final void addCwEntry(CwEntry cwe, Strategy s){
		
		entries.add(cwe);
		s.updateBoard(b,cwe);
	}
	
	/**
	 * Generuje now� krzy��wk�.
	 * @param s strategia za pomoc�, kt�rej generowana jest krzy��wka
	 * @throws Exception wyrzuca wyj�tek gdy generowanie si� nie uda�o
	 */
	public final void generate(Strategy s) throws Exception{
		CwEntry e = null;
		while((e = s.findEntry(this)) != null){
			addCwEntry(e,s);
		}
	}
	
}
