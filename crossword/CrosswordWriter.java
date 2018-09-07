package crossword;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;

/**
 * Klasa reprezentuj¹ca funkcje s³u¿ace do zapisywanie krzy¿ówek do pliku.
 * @author redi
 * @version %I%, %G%
 */
public class CrosswordWriter implements Writer {
	
	private String directory;
	
	/**
	 * Konstruktor z wybranym katalogiem.
	 * @param foldername katalog do którego bed¹ zapisywane krzy¿ówki
	 */
	public CrosswordWriter(String foldername) {
		
		directory = foldername;	
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public long Write(Crossword cw) throws IOException {
		
		long ID = getUniqueID();
		File file = new File(directory + "/" + ID + ".crossword");
		
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream(file), "UTF-8"));
		
		Iterator<CwEntry> itr = cw.getROEntryIter();
		while(itr.hasNext()){
			CwEntry cwentry = itr.next();
			writer.write(cwentry.getX() + " " + cwentry.getY() + " " + cwentry.getDir() + " " + cwentry.getWord());
			writer.newLine();
			writer.write(cwentry.getClue());
			writer.newLine();
		}
		
		writer.close();
		return ID;
	}

	/**
	 * Zmien katalog.
	 * @param directory nowy katalog
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public long getUniqueID() {
		
		return System.currentTimeMillis() / 1000L;
		
	}

}
