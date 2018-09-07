package crossword;

/**
 * Kierunek has³a (poziomy, pionowy)
 * @author redi
 * @version %I%, %G%
 */
public enum Direction{
	HORIZ(0),
	VERT(1);
	
	int ID;
	
	Direction(int ID) {
		this.ID = ID;
	}
}

