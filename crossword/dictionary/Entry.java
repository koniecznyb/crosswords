/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crossword.dictionary;

/**
 * Klasa reprezentujπca has≥o wraz z podpowiedziπ.
 * @author redi
 * @version %I%, %G%
 */
public class Entry implements Comparable<Entry> {
	
	private String word;
	private String clue;
	
	/**
	 * Ustaw domyúlne wartoúci.
	 * @param word wyraz
	 * @param clue podpowiedü
	 */
	public Entry(String word, String clue){
		this.word = word;
		this.clue = clue;
	}

	/**
	 * Pobierz wyraz.
	 * @return wyraz
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Pobierz podpowiedü.
	 * @return podpowiedü
	 */
	public String getClue() {
		return clue;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public int compareTo(Entry o) {
		if(this.word != null && o.getWord() != null){
			return word.compareToIgnoreCase(o.getWord());
		}
		return 0;
	}
	
    
}
