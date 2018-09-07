/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crossword.dictionary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


/**
 * Klasa reprezentuj¹ca plik z baz¹ danych hase³ do generowania krzy¿ówek.
 * @version %I%, %G%
 * @author redi
 * @see crossword.dictionary.Entry
 */
public class CwDB {

	protected LinkedList <Entry> dict;
    
	/**
	 * Konstruktor, wywoluje funkcje createDB, która z pliku filename przesy³a wskazówki i has³a do listy dict.
	 * @param filename plik z has³ami i wskazówkami
	 * @throws FileNotFoundException gdy nie znaleziono okreœlonego pliku
	 */
	public CwDB(String filename) throws FileNotFoundException {
		dict = new LinkedList<Entry>();
		
		try {
			createDB(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * Dodaj nowe has³o do listy dict.
	 * @param word has³o 
	 * @param clue wskazówka
	 */
	public void add(String word, String clue){
		
		Entry e = new Entry(word, clue);
		dict.add(e);        
    }
    
	/**
	 * Pobierz has³o "word" z listy hase³.
	 * @param word has³o do pobrania
	 * @return pobrany wpis z has³em i wskazówk¹
	 */
    public Entry get(String word){
    	
    	
    	for(Entry s : dict){
			if(s.getWord().equals(word))
				return s;
		}
    	
    	return new Entry("empty", "empty");
    	
        //NIC NIE ZNALEZIONO
    }
    
    /**
     * Usuñ podane s³owo z listy.
     * @param word s³owo do usuniêcia
     */
    public void remove(String word){
        
    	int index = 0;
    	
    	for(Entry s : dict){
    		if(s.getWord().equals(word))
    			dict.remove(index);
    		index++;
    	}
    	
    }
    
    /**
     * Zapisz bazê danych do pliku.
     * @param filename plik do zapisania
     * @throws IOException gdy zapisywanie siê nie powiod³o
     */
    public void saveDB(String filename) throws IOException{
    	
    	BufferedWriter savedDatabaseFile = new BufferedWriter(new FileWriter(filename));
		ListIterator<Entry> iter = dict.listIterator(0);
		Entry e;
		
		while(iter.hasNext())
		{
			e = iter.next();
			
			savedDatabaseFile.write(e.getWord(), 0, e.getWord().length());
			savedDatabaseFile.newLine();
			savedDatabaseFile.write(e.getClue(), 0, e.getClue().length());
			savedDatabaseFile.newLine();
		}
		
		savedDatabaseFile.close();
	}
    	
    /**
     * Pobierz wielkoœæ listy.
     * @return wielkoœæ listy
     */
    public int getSize(){
    	return dict.size();
    }
    
    /**
     * Tworzy liste z has³ami i wskazówkami, z okreœlonego pliku.
     * @param filename plik
     * @throws FileNotFoundException gdy nie znaleziono pliku
     */
    protected void createDB(String filename) throws FileNotFoundException{
    	
    	File CwDBfile = new File(filename);
    	Scanner in = new Scanner(CwDBfile, "UTF-8");
    	
    	while( in.hasNextLine() ){
    		
    		add(in.nextLine(), in.nextLine());
    		
    	}
    	
    	in.close();
    	
    }    
}
