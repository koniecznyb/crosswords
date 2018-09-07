package crossword;

import java.util.LinkedList;

import crossword.dictionary.Entry;
import crossword.Direction;

/**
 * Podstawowa strategia prostej krzy¿ówki.
 * @author redi
 * @version %I%, %G%
 * @see crossword.Board
 * @see crossword.CwEntry
 */
public class BasicStrategy extends Strategy {
	
	private final int MAXIMUM_ITERATIONS = 5000; 
	private char [] mainWordToChar;
	private static int horizontalWordsCounter;
	private LinkedList<String> clues;
	
	/**
	 *  Konstruktor definiuj¹cy zadeklarowane zmienne.
	 */
	public BasicStrategy() {
		clues = new LinkedList<String>();
		horizontalWordsCounter = 0;
	}
	
	/**
	 * @inheritDoc
	 */
	@Override
	public CwEntry findEntry(Crossword cw) throws Exception {
		
		if(cw.getCwDB() == null)
			throw new Exception("Select a crossword database file first!");
		
		cw.getCwDB().getRandom(12);
		
		if(horizontalWordsCounter == cw.getBoardCopy().getHeight()-1)
			return null;
		
		CwEntry cwentry = new CwEntry("empty", "empty");
		// losowanie g³ownego s³owa
		if(cw.isCrosswordEmpty()){
			
			Entry entry = cw.getCwDB().getRandom(cw.getBoardCopy().getHeight()-1);
			cwentry = new CwEntry(entry.getWord(), entry.getClue());
			cwentry.setDir(Direction.VERT);
			cwentry.setX(1);
			cwentry.setY(1);
			
			mainWordToChar = cwentry.getWord().toCharArray();
			
			return cwentry;
		}
		
		else if(!cw.isCrosswordEmpty()){
			
			String pattern = new String();
			pattern = "[" + mainWordToChar[horizontalWordsCounter] + "].*";
			Entry entry;
			
			int i = 0;
			do{
				if(i == MAXIMUM_ITERATIONS )
					throw new Exception("Too many iterations!");
				entry = cw.getCwDB().getRandom(pattern);
				i++;
				
			}
			while( entry.getWord().length() >= cw.getBoardCopy().getWidth());

				
			cwentry = new CwEntry(entry.getWord(), entry.getClue());
			cwentry.setDir(Direction.HORIZ);
			cwentry.setX(horizontalWordsCounter+1);
			cwentry.setY(1);
			
			horizontalWordsCounter++;
			
		}
				
		return cwentry;
			
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void updateBoard(Board b, CwEntry e) {
		
		 

		if( e.getDir() == Direction.VERT){
			
			
			char [] wordToChars = e.getWord().toCharArray();
			
			int x = e.getX();
			int y = e.getY();
			
			for(int i=0; i<e.getWord().length(); i++){
				
				BoardCell boardcell = new BoardCell();
				boardcell.setContent(wordToChars[i]);
				b.setCell(x, y, boardcell);
				x++;
			}
			
		}
		else if(e.getDir() == Direction.HORIZ){
			
			char [] wordToChars = e.getWord().toCharArray();
			
			int x = e.getX();
			int y = e.getY();
			
			for(int i=0; i<e.getWord().length(); i++){
				
				BoardCell boardcell = new BoardCell();
				boardcell.setContent(wordToChars[i]);
				b.setCell(x, y, boardcell);
				y++;
				
			}			
		}
		
		clues.add(e.getClue());
		
	}
	
}
