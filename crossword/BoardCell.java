package crossword;

/**
 * Klasa reprezentuj�ca dan� kom�rke w tablicy krzy��wki.
 * @author redi
 * @version %I%, %G%
 */
public class BoardCell {
	
	private char content;
	public Restrictions boardCellRestrictions;
	
	/**
	 * Konstruktor nadaj�cy domy�lne warto�ci zadeklarowanym zmiennym.
	 * @param c zawarto�� kom�rki
	 */
	public BoardCell(char c) {
		boardCellRestrictions = new Restrictions();
		this.setContent(c);
	}
	
	/**
	 * Konstruktor nadaj�cy domy�lne warto�ci zadeklarowanym zmiennym.
	 */
	public BoardCell() {
		boardCellRestrictions = new Restrictions();
	}
		
	/**
	 * Pobierz zawarto�� zmiennej content.
	 * @return zawarto�� zmiennej content
	 */
	public char getContent() {
		return content;
	}

	/**
	 * Zmien warto�� zmiennej content.
	 * @param content nowa warto�� zmiennej content
	 */
	public void setContent(char content){
		
		this.content = content;
		
	}
	
	/**
	 * Klasa wewn�trzna opisuj�ca zdolno�� kom�rki
	 * @author redi
	 *
	 */
	class Restrictions{

		boolean HorizStart, HorizEnd, HorizInner, VertStart, VertEnd, VertInner;
		
		public Restrictions() {
			HorizStart = HorizEnd = HorizInner = VertStart = VertEnd = VertInner = true;
		}
	}
	
	//ENABLE
	
	public void enableHorizStart(){
		
		boardCellRestrictions.HorizStart = true;		
	}
	
	public void enableHorizEnd(){
		
		boardCellRestrictions.HorizEnd = true;		
	}

	public void enableHorizInner(){
		
		boardCellRestrictions.HorizInner = true;
	}
	
	public void enableVertStart(){
		
		boardCellRestrictions.VertStart = true;
	}
	
	public void enableVertEnd(){
		
		boardCellRestrictions.VertEnd = true;
	}
	
	public void enableVertInner(){
		
		boardCellRestrictions.VertInner = true;
	}
	
	// DISABLE
	
	public void disableHorizStart(){
		
		boardCellRestrictions.HorizStart = false;
	}
	
	public void disableHorizEnd(){
		
		boardCellRestrictions.HorizEnd = false;
	}

	public void disableHorizInner(){
		
		boardCellRestrictions.HorizInner = false;
	}
	
	public void disableVertStart(){
		
		boardCellRestrictions.VertStart = false;
	}
	
	public void disableVertEnd(){
		
		boardCellRestrictions.VertEnd = false;
	}
	
	public void disableVertInner(){
		
		boardCellRestrictions.VertInner = false;
	}
}
