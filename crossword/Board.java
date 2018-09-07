package crossword;

import java.util.LinkedList;

/**
 * Klasa reprezentuj¹ca tablice przechowywuj¹c¹ konkretne komórki krzy¿ówki.
 * @author redi
 * @see crossword.BoardCell
 * @version %I%, %G%
 */
public class Board {
	
	private BoardCell[][] board;
	
	/**
	 * Konstruktor definiuj¹cy wszystkie miejsca tablicy znakami ' '.
	 * @param width maksymalna szerokoœæ krzy¿ówki
	 * @param height maksymalna wysokoœæ krzy¿ówki
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
	 * Pobierz szerokoœæ tablicy.
	 * @return szerokoœæ tablicy
	 */
	public int getWidth(){
		
		return board[0].length;
	}
	
	/**
	 * Pobierz wysokoœæ tablicy.
	 * @return wysokoœæ tablicy
	 */
	public int getHeight(){
		
		return board.length;
	}

	/**
	 * Pobierz komórke tablicy o wspó³rzêdnych x i y.
	 * @param height wspó³rzêdna x
	 * @param width wspó³rzêdna y
	 * @return komórka o wspó³rzêdnych x,y
	 * @throws ArrayIndexOutOfBoundsException wyj¹tek gdy indeksy poza granicami tablicy
	 */
	
	public BoardCell getCell(int height, int width) throws ArrayIndexOutOfBoundsException{
	
		return board[height][width];
	}
	
	/**
	 * Zmieñ komórke o wspó³rzêdnych x, y na c.
	 * @param height wspó³rzêdna x
	 * @param width wspó³rzêdna y
	 * @param c nowa komórka
	 */
	public void setCell(int height, int width, BoardCell c){
		
		board[height][width] = c;
		
	}
	
	/**
	 * Pobiera wszystkie komórki, które mog¹ byæ pocz¹tkiem nowego has³a.
	 * @return lista z komórkami mog¹cymi byæ pocz¹tkiem nowego has³a
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
