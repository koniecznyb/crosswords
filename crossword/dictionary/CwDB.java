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
 * Klasa reprezentuj�ca plik z baz� danych hase� do generowania krzy��wek.
 * @version %I%, %G%
 * @author redi
 * @see crossword.dictionary.Entry
 */
public class CwDB {

	protected LinkedList <Entry> dict;
    
	/**
	 * Konstruktor, wywoluje funkcje createDB, kt�ra z pliku filename przesy�a wskaz�wki i has�a do listy dict.
	 * @param filename plik z has�ami i wskaz�wkami
	 * @throws FileNotFoundException gdy nie znaleziono okre�lonego pliku
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
	 * Dodaj nowe has�o do listy dict.
	 * @param word has�o 
	 * @param clue wskaz�wka
	 */
	public void add(String word, String clue){
		
		Entry e = new Entry(word, clue);
		dict.add(e);        
    }
    
	/**
	 * Pobierz has�o "word" z listy hase�.
	 * @param word has�o do pobrania
	 * @return pobrany wpis z has�em i wskaz�wk�
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
     * Usu� podane s�owo z listy.
     * @param word s�owo do usuni�cia
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
     * Zapisz baz� danych do pliku.
     * @param filename plik do zapisania
     * @throws IOException gdy zapisywanie si� nie powiod�o
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
     * Pobierz wielko�� listy.
     * @return wielko�� listy
     */
    public int getSize(){
    	return dict.size();
    }
    
    /**
     * Tworzy liste z has�ami i wskaz�wkami, z okre�lonego pliku.
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
