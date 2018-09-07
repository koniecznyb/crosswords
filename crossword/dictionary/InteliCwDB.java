package crossword.dictionary;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * Inteligentna baza danych, kt�ra potrafi wyszukiwa� s�owa o okre�lonych warunkach.
 * @author redi
 * @version %I%, %G%
 * @see Entry
 */
public class InteliCwDB extends CwDB {
	
	private Random generator;

	/**
	 * Konstruktor wywo�uj�cy konstuktor klasy CwDB.
	 * @param filename
	 * @throws FileNotFoundException
	 * @see {@link CwDB#CwDB(String)}
	 */
	public InteliCwDB(String filename) throws FileNotFoundException {
		super(filename);
	}
	
	/**
	 * Znajd� wszystkie has�a spe�niaj�cy dany wzorzec.
	 * @param pattern wzorzec
	 * @return lista hase� spe�niaj�cych dany wzorzec
	 */
	public LinkedList <Entry> findALL( String pattern){
		
		LinkedList<Entry> resultList = new LinkedList<Entry>();
		
		for(Entry s : dict){
			
			if(s.getWord().matches(pattern))
				resultList.add(s);
		}
		
		return resultList;
	}
	
	/**
	 * Znajd� losowe s�owo.
	 * @return znalezione s�owo
	 */
	public Entry getRandom(){
		
		generator = new Random();
		
		int index = generator.nextInt(dict.size());
		
		return dict.get(index);
	}
	
	/**
	 * Znajd� losowe s�owo o zadanej d�ugo�ci (minimalnie 2 znaki, maksymalnie 12 znak�w).
	 * @param length d�ugo�� s�owa
	 * @return znalezione s�owo
	 */
	public Entry getRandom(int length){
		
		if(length < 2 && length > 12)
			
			throw new ArrayIndexOutOfBoundsException("Generated word too long!");
		
		generator = new Random();
		Entry e;
		
		do{
			int index = generator.nextInt(dict.size());
			e = dict.get(index);
			
		}while( e.getWord().length() != length );
		
		return e;
	}
	
	/**
	 * Znajdz s�owo o zadanym wzorcu.
	 * @param pattern wzorzec
	 * @return znalezione s�owo
	 */
	public Entry getRandom( String pattern){
		
		generator = new Random();
		
		LinkedList <Entry> foundOnes = findALL(pattern);
		if( foundOnes.isEmpty() )
			return new Entry("empty", "empty");
			// throw exception
		
		int index = generator.nextInt(foundOnes.size());
		return foundOnes.get(index);	
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void add(String word, String clue) {
		super.add(word, clue);
		Collections.sort(dict);
	}
}
