package crossword;

/**
 * Klasa reprezentuj¹ca dan¹ komórke w tablicy krzy¿ówki.
 * @author redi
 * @version %I%, %G%
 */
public class BoardCell {
	
	private char content;
	public Restrictions boardCellRestrictions;
	
	/**
	 * Konstruktor nadaj¹cy domyœlne wartoœci zadeklarowanym zmiennym.
	 * @param c zawartoœæ komórki
	 */
	public BoardCell(char c) {
		boardCellRestrictions = new Restrictions();
		this.setContent(c);
	}
	
	/**
	 * Konstruktor nadaj¹cy domyœlne wartoœci zadeklarowanym zmiennym.
	 */
	public BoardCell() {
		boardCellRestrictions = new Restrictions();
	}
		
	/**
	 * Pobierz zawartoœæ zmiennej content.
	 * @return zawartoœæ zmiennej content
	 */
	public char getContent() {
		return content;
	}

	/**
	 * Zmien wartoœæ zmiennej content.
	 * @param content nowa wartoœæ zmiennej content
	 */
	public void setContent(char content){
		
		this.content = content;
		
	}
	
	/**
	 * Klasa wewnêtrzna opisuj¹ca zdolnoœæ komórki
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
