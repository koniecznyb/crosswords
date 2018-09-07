package crossword.dictionary;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * Inteligentna baza danych, która potrafi wyszukiwaæ s³owa o okreœlonych warunkach.
 * @author redi
 * @version %I%, %G%
 * @see Entry
 */
public class InteliCwDB extends CwDB {
	
	private Random generator;

	/**
	 * Konstruktor wywo³uj¹cy konstuktor klasy CwDB.
	 * @param filename
	 * @throws FileNotFoundException
	 * @see {@link CwDB#CwDB(String)}
	 */
	public InteliCwDB(String filename) throws FileNotFoundException {
		super(filename);
	}
	
	/**
	 * ZnajdŸ wszystkie has³a spe³niaj¹cy dany wzorzec.
	 * @param pattern wzorzec
	 * @return lista hase³ spe³niaj¹cych dany wzorzec
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
	 * ZnajdŸ losowe s³owo.
	 * @return znalezione s³owo
	 */
	public Entry getRandom(){
		
		generator = new Random();
		
		int index = generator.nextInt(dict.size());
		
		return dict.get(index);
	}
	
	/**
	 * ZnajdŸ losowe s³owo o zadanej d³ugoœci (minimalnie 2 znaki, maksymalnie 12 znaków).
	 * @param length d³ugoœæ s³owa
	 * @return znalezione s³owo
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
	 * Znajdz s³owo o zadanym wzorcu.
	 * @param pattern wzorzec
	 * @return znalezione s³owo
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
