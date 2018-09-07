package crossword;

import crossword.dictionary.Entry;
import crossword.Direction;

/**
 * Klasa reprezentuj¹ca has³o wraz z podpowiedzi¹.
 * Od {@crossword.dictionary.Entry} ró¿ni¹ca siê tym, ¿e posiada zmienne x i y, bed¹ce wspó³rzêdnymi danego has³a,
 * oraz zmienn¹ typu Enum Direction reprezentuj¹c¹ kierunek has³a (Vertical, Horizontal)
 * @author redi
 * @version %I%, %G%
 * @see crossword.Direction
 * @see crossword.dictionary.Entry
 */
public class CwEntry extends Entry {
	
	private int x;
	private int y;
	private Direction d;
	
	/**
	 * Pobierz wspó³rzêdn¹ x.
	 * @return wspó³rzêdna x
	 */
	public int getX() {
		return x;
	}


	/**
	 * Pobierz wspó³rzêdn¹ y.
	 * @return wspó³rzêdna y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Pobierz kierunek has³a.
	 * @return kierunek has³a (poziomy, pionowy)
	 * @see crossword.Direction
	 */
	public Direction getDir() {
		return d;
	}
	
	/**
	 * Zmien kierunek has³a.
	 * @param dir nowy kierunek
	 * @see crossword.Direction
	 */
	public void setDir(Direction dir){
		this.d = dir;
	}


	/**
	 * Domyœlny konstruktor.
	 * @param word has³o
	 * @param clue podpowiedŸ
	 */
	public CwEntry(String word, String clue) {
		super(word, clue);
		// TODO Auto-generated constructor stub
	}


	/**
	 * Ustaw wspó³rzêdna x.
	 * @param x nowa wspó³rzêdna
	 */
	public void setX(int x) {
		this.x = x;
	}


	/**
	 * Ustaw wspó³rzêdna y.
	 * @param y nowa wspó³rzêdna
	 */
	public void setY(int y) {
		this.y = y;
	}

}
