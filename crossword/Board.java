package crossword;

import java.util.LinkedList;

/**
 * Klasa reprezentuj�ca tablice przechowywuj�c� konkretne kom�rki krzy��wki.
 * @author redi
 * @see crossword.BoardCell
 * @version %I%, %G%
 */
public class Board {
	
	private BoardCell[][] board;
	
	/**
	 * Konstruktor definiuj�cy wszystkie miejsca tablicy znakami ' '.
	 * @param width maksymalna szeroko�� krzy��wki
	 * @param height maksymalna wysoko�� krzy��wki
	 */
	public Board(int width, int height){
		board = new BoardCell[height][width];
		
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				board[i][j] = new BoardCell(' ');
			}
		}
	}
	
	/**
	 * Pobierz szeroko�� tablicy.
	 * @return szeroko�� tablicy
	 */
	public int getWidth(){
		
		return board[0].length;
	}
	
	/**
	 * Pobierz wysoko�� tablicy.
	 * @return wysoko�� tablicy
	 */
	public int getHeight(){
		
		return board.length;
	}

	/**
	 * Pobierz kom�rke tablicy o wsp�rz�dnych x i y.
	 * @param height wsp�rz�dna x
	 * @param width wsp�rz�dna y
	 * @return kom�rka o wsp�rz�dnych x,y
	 * @throws ArrayIndexOutOfBoundsException wyj�tek gdy indeksy poza granicami tablicy
	 */
	
	public BoardCell getCell(int height, int width) throws ArrayIndexOutOfBoundsException{
	
		return board[height][width];
	}
	
	/**
	 * Zmie� kom�rke o wsp�rz�dnych x, y na c.
	 * @param height wsp�rz�dna x
	 * @param width wsp�rz�dna y
	 * @param c nowa kom�rka
	 */
	public void setCell(int height, int width, BoardCell c){
		
		board[height][width] = c;
		
	}
	
	/**
	 * Pobiera wszystkie kom�rki, kt�re mog� by� pocz�tkiem nowego has�a.
	 * @return lista z kom�rkami mog�cymi by� pocz�tkiem nowego has�a
	 */
	public LinkedList <BoardCell> getStartCells(){
		
		LinkedList <BoardCell> startCells = new LinkedList<BoardCell>();
		
		if(board == null){
			//throw exception
		}
		
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				if(board[i][j].boardCellRestrictions.HorizStart == true &&
						board[i][j].boardCellRestrictions.VertStart == true){
					startCells.add(board[i][j]);
				}
			}
		}		
		
		return startCells;
	}
}
