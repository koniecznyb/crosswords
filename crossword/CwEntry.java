package crossword;

import crossword.dictionary.Entry;
import crossword.Direction;

/**
 * Klasa reprezentuj�ca has�o wraz z podpowiedzi�.
 * Od {@crossword.dictionary.Entry} r�ni�ca si� tym, �e posiada zmienne x i y, bed�ce wsp�rz�dnymi danego has�a,
 * oraz zmienn� typu Enum Direction reprezentuj�c� kierunek has�a (Vertical, Horizontal)
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
	 * Pobierz wsp�rz�dn� x.
	 * @return wsp�rz�dna x
	 */
	public int getX() {
		return x;
	}


	/**
	 * Pobierz wsp�rz�dn� y.
	 * @return wsp�rz�dna y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Pobierz kierunek has�a.
	 * @return kierunek has�a (poziomy, pionowy)
	 * @see crossword.Direction
	 */
	public Direction getDir() {
		return d;
	}
	
	/**
	 * Zmien kierunek has�a.
	 * @param dir nowy kierunek
	 * @see crossword.Direction
	 */
	public void setDir(Direction dir){
		this.d = dir;
	}


	/**
	 * Domy�lny konstruktor.
	 * @param word has�o
	 * @param clue podpowied�
	 */
	public CwEntry(String word, String clue) {
		super(word, clue);
		// TODO Auto-generated constructor stub
	}


	/**
	 * Ustaw wsp�rz�dna x.
	 * @param x nowa wsp�rz�dna
	 */
	public void setX(int x) {
		this.x = x;
	}


	/**
	 * Ustaw wsp�rz�dna y.
	 * @param y nowa wsp�rz�dna
	 */
	public void setY(int y) {
		this.y = y;
	}

}
