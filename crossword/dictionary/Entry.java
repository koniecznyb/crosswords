/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crossword.dictionary;

/**
 * Klasa reprezentuj�ca has�o wraz z podpowiedzi�.
 * @author redi
 * @version %I%, %G%
 */
public class Entry implements Comparable<Entry> {
	
	private String word;
	private String clue;
	
	/**
	 * Ustaw domy�lne warto�ci.
	 * @param word wyraz
	 * @param clue podpowied�
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
	 * Pobierz podpowied�.
	 * @return podpowied�
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
