package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.border.LineBorder;

import crossword.Board;
import crossword.Crossword;
import crossword.CwBrowser;
import crossword.dictionary.InteliCwDB;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.awt.Point;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;

import java.awt.Font;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 * G³owne okno programu.
 * @author redi
 * @version %I%, %G%
 * @see crossword.Crossword
 * @see crossword.BasicStrategy
 * @see crossword.CrosswordReader
 * @see crossword.CrosswordWriter
 * @see crossword.Board
 */
public class MainWindow extends JFrame {
	
	private static final String DEFAULT_DIR = "krzyzowki";
	private static final String DEFAULT_CROSSWORD_DATABASE_FILE = "cwdb.txt";

	private JTextField crosswordDatabasePath;
	
	private static CwBrowser cwbrowser;
	private static String dir;
	private static InteliCwDB cwdb;
	private static Crossword crossword;
	private static JSpinner width_spinner;
	private static JSpinner height_spinner;
	private static JPanel mainBoardFrame;
	private static Grid boardGrid;
	private JTextPane cluesField;
	private static MainWindow frame;
	private static File fileToOpen;
	private JTextField crosswordPath;
	
	public static class Grid extends JPanel {
		
		/** SIZE szerokoœæ i d³ugoœæ komórki krzy¿ówki*/
		private final int SIZE = 30;
        private List<Point> fillCells;
        static int counter = 0;
        private LinkedList <String> letters;

        public Grid() {
            fillCells = new ArrayList<Point>(25);
            letters = new LinkedList<String>();
        }

        /**
         * Funkcja odpowiedzialna za wyœwietlanie prostok¹tów, buduj¹cych krzyzówkê.
         * @param g obiekt klasy Graphics wymagany do funkcji g.drawString, g.drawRectangle.
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for(int i=0; i<fillCells.size(); i++){

                int cellX = SIZE + (fillCells.get(i).x * SIZE);
                int cellY = SIZE + (fillCells.get(i).y * SIZE);
                
                if( !letters.isEmpty() && i < fillCells.size()/2 ){
                	g.drawString(letters.get(i), cellX+SIZE/2 - 3, cellY+SIZE/2+5);
                }
                g.drawRect(cellX, cellY, SIZE, SIZE);              

            }
        }

        /**
         * Wskazuje miejsce do narysowania komórki krzyzówki.
         * @param x wspó³rzêdna x tablicy board
         * @param y wspó³rzêdna y tablicy board
         */
        public void fillCell(int x, int y) {
        	
            fillCells.add(new Point(x, y));
            repaint();
        }
        
        /**
         * Wskazuje miejsce do narysowania komórki krzyzówki.
         * @param x wspó³rzêdna x tablicy board
         * @param y wspó³rzêdna y tablicy board
         * @param s znak odpowiadaj¹cy danej komórce
         */
        
        public void fillCell(int x, int y, String s) {
        	
        	letters.add(s);
            fillCells.add(new Point(x, y));
            repaint();
            
        }

    }

	/**
	 * Uruchom aplikacje.
	 */
	public static void main(String[] args) {
		
	////ERROR
		 
		// przekierowywuj b³edy do pliku log.txt
		try {
			System.setErr(new PrintStream(new BufferedOutputStream(new FileOutputStream("log.txt"))));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			 
			 
	 ////ERROR
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainWindow();
					frame.setVisible(true);
					frame.setTitle("Crossword generator");
					
					cwdb = new InteliCwDB(DEFAULT_CROSSWORD_DATABASE_FILE);
					dir = DEFAULT_DIR;

					cwbrowser = new CwBrowser(dir);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Utwórz frame.
	 */
	public MainWindow() {
		setResizable(false);
		
		
		//--------------------------------------------------------------------------------------------------------------
//		 WYBOR THEME APLIKACJI
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//--------------------------------------------------------------------------------------------------------------
		
		
		// UTWORZENIE G£OWNEGO PANELU
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel newCrosswordLabel = new JPanel();
		newCrosswordLabel.setBorder(new TitledBorder(null, "New crossword", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel crosswordDatabaseLabel = new JPanel();
		crosswordDatabaseLabel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Set crossword database", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel controlsLabel = new JPanel();
		controlsLabel.setBorder(new TitledBorder(null, "Control", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		mainBoardFrame = new JPanel();
		mainBoardFrame.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainBoardFrame.setBackground(Color.WHITE);
		
		JPanel openCrosswordLabel = new JPanel();
		openCrosswordLabel.setBorder(new TitledBorder(null, "Open crossword", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(mainBoardFrame, GroupLayout.DEFAULT_SIZE, 1154, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(newCrosswordLabel, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(crosswordDatabaseLabel, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(openCrosswordLabel, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(controlsLabel, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(openCrosswordLabel, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
						.addComponent(crosswordDatabaseLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(newCrosswordLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(controlsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(mainBoardFrame, GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		
		
		
		//--------------------------------------------------------------------------------------------------------------
		
		// PANEL CROSSWORDPATH (WCZYTYWANIE KRZY¯ÓWKI Z PLIKU)
		
		JButton crosswordPathButton = new JButton("...");
		crosswordPathButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				final JFileChooser fc = new JFileChooser();
				javax.swing.filechooser.FileFilter filter = new javax.swing.filechooser.FileFilter() {
					
					@Override
					public String getDescription() {
						
						return "Crossword files (*.crossword)";
					}
					
					@Override
					public boolean accept(File f) {
						
						if (f.isDirectory()) {
							return true;
						}
						
						String filename = f.getName();
						return filename.endsWith(".crossword");
						
					}
				};
								
				fc.setFileFilter(filter);
				fc.setAcceptAllFileFilterUsed(false);
				fc.setMultiSelectionEnabled(false);
				int returnVal = fc.showOpenDialog(MainWindow.this);
				
				if( returnVal == JFileChooser.APPROVE_OPTION){
					
					 fileToOpen = fc.getSelectedFile();
					 cwbrowser.setDir(fileToOpen.getParent());		
					 crosswordPath.setText(fileToOpen.getAbsolutePath());
				}				
			}
		});
		
		crosswordPath = new JTextField();
		crosswordPath.setEditable(false);
		openCrosswordLabel.add(crosswordPath);
		crosswordPath.setColumns(15);
		
		openCrosswordLabel.add(crosswordPathButton);
		
		JButton crosswordLoadButton = new JButton("Load");
		crosswordLoadButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					cwbrowser.setDir(fileToOpen.getParent());
					crossword = cwbrowser.openCrosswordByID( Long.parseLong( fileToOpen.getName().substring(0, 10) ) );
					
					clearBoard(boardGrid);
					generateEmptyBoard(crossword, boardGrid);
				}
				catch(Exception ex){
					
					JOptionPane.showMessageDialog(frame, "No file chosen.", "Error!", JOptionPane.ERROR_MESSAGE);
					
				}
			}
		});
		openCrosswordLabel.add(crosswordLoadButton);
		
		
		//--------------------------------------------------------------------------------------------------------------
		//PANEL GRID I CLUESFIELD ( CONTENT APLIKACJI NA KTÓRYM WYŒWIETLANIE SA PODPOWIEDZI I KRZY¯ÓWKA)		
		
		
		
		boardGrid = new Grid();
		boardGrid.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Crossword", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		cluesField = new JTextPane();
		cluesField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Clues", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cluesField.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
		cluesField.setContentType("text/html");
		cluesField.setEditable(false);
		boardGrid.setBackground(Color.WHITE);
		GroupLayout gl_mainBoardFrame = new GroupLayout(mainBoardFrame);
		gl_mainBoardFrame.setHorizontalGroup(
			gl_mainBoardFrame.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainBoardFrame.createSequentialGroup()
					.addContainerGap()
					.addComponent(boardGrid, GroupLayout.PREFERRED_SIZE, 683, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cluesField, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_mainBoardFrame.setVerticalGroup(
			gl_mainBoardFrame.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_mainBoardFrame.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mainBoardFrame.createParallelGroup(Alignment.TRAILING)
						.addComponent(cluesField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
						.addComponent(boardGrid, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
					.addContainerGap())
		);
		mainBoardFrame.setLayout(gl_mainBoardFrame);
		
		
		
		//--------------------------------------------------------------------------------------------------------------
		//GORNE-LEWE MENU ODPOWIADAJ¥CE ZA GENEROWANIE NOWEJ KRZY¯ÓWKI O PODANYCH WYMIARACH MAKSYMALNYCH
		
		JLabel width = new JLabel("width");
		newCrosswordLabel.add(width);
		
		width_spinner = new JSpinner();
		width_spinner.setModel(new SpinnerNumberModel(8, 2, 12, 1));
		Dimension d = width_spinner.getPreferredSize();
		d.width=32;
		width_spinner.setPreferredSize(d);
		newCrosswordLabel.add(width_spinner);
		
		JLabel height = new JLabel("height");
		newCrosswordLabel.add(height);
		
		height_spinner = new JSpinner();
		height_spinner.setModel(new SpinnerNumberModel(8, 2, 12, 1));
		height_spinner.setPreferredSize(d);
		newCrosswordLabel.add(height_spinner);
		
		JButton btnGen = new JButton("Gen...");
		
		btnGen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try{
					int width = Integer.parseInt(width_spinner.getValue().toString());
					int height = Integer.parseInt(height_spinner.getValue().toString());
					crossword = cwbrowser.generateCrossword(width+1, height+1, cwdb);
					
					clearBoard(boardGrid);
					generateEmptyBoard(crossword, boardGrid);
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(frame, "Too many iterations. Try again.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
					
			}
		});
		newCrosswordLabel.add(btnGen);
		
		//--------------------------------------------------------------------------------------------------------------
		//DRUGIE MENU ODPOWIADAJ¥CE ZA WYBÓR PLIKU Z HAS£AMI DO KRZY¯ÓWEK
		
		crosswordDatabasePath = new JTextField();
		crosswordDatabasePath.setEditable(false);
		crosswordDatabaseLabel.add(crosswordDatabasePath);
		crosswordDatabasePath.setColumns(15);
		
		JButton crosswordDatabaseButton = new JButton("...");
		
		crosswordDatabaseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				File newDatabaseFile;
				final JFileChooser fc = new JFileChooser();
				
				javax.swing.filechooser.FileFilter filter = new javax.swing.filechooser.FileFilter() {
					
					@Override
					public String getDescription() {
						
						return "Text files (*.txt)";
					}
					
					@Override
					public boolean accept(File f) {
						
						if (f.isDirectory()) {
							return true;
						}
						
						String filename = f.getName();
						return filename.endsWith(".txt");
						
					}
				};
				
				
				fc.setFileFilter(filter);
				fc.setAcceptAllFileFilterUsed(false);
				fc.setMultiSelectionEnabled(false);
				
				int returnVal = fc.showOpenDialog(MainWindow.this);
				
				if( returnVal == JFileChooser.APPROVE_OPTION){
					
					newDatabaseFile = fc.getSelectedFile();
					crosswordDatabasePath.setText(newDatabaseFile.getAbsolutePath());	
										
				}
	
			}
		});
		crosswordDatabaseLabel.add(crosswordDatabaseButton);
		
		JButton crosswordDatabaseLoad = new JButton("Load");
		crosswordDatabaseLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try{
					
					if(crosswordDatabasePath.getText().equals(""))
						throw new GUIErrorException("No database file found.");
					try {
						cwdb = new InteliCwDB(crosswordDatabasePath.getText());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				catch(GUIErrorException ex){
					JOptionPane.showMessageDialog(frame, "No database file found.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		crosswordDatabaseLabel.add(crosswordDatabaseLoad);
		
		//--------------------------------------------------------------------------------------------------------------
		//OSTATNIE MENU Z KONTROLKAMI UMO¯LIWAJ¥CE ZAPISYWANIE, DRUKOWANIE, ROZWI¥ZYWANIE KRZY¯ÓWKI
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try{
					if(crossword == null){
						throw new GUIErrorException("Crossword is empty!");
					}
					
					printBoard();
					printClues();
				}
				catch(Exception ex){
					
					JOptionPane.showMessageDialog(frame, "No crossword selected.", "Error!", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
		});
		controlsLabel.add(btnPrint);
		
		JButton btnSave = new JButton("Save");

		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try{
					String folder = "";
					
					
					
					if(crossword != null){
						
						final JFileChooser fc = new JFileChooser();
						fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						
						if (fc.showOpenDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) {
							  		
							String dirBeforeSave = cwbrowser.getDir();
							folder = fc.getSelectedFile().getAbsolutePath();
							cwbrowser.setDir(folder);
							long ID = cwbrowser.saveCrossword(crossword);
							cwbrowser.setDir(dirBeforeSave);
							JOptionPane.showMessageDialog(frame, "Crossword " + ID + " saved successfully!");
						}
					
					}
					else{
						throw new GUIErrorException("Crossword is empty!");
					}
				}
				catch(Exception ex){
					
					JOptionPane.showMessageDialog(frame, "No crossword selected.", "Error!", JOptionPane.ERROR_MESSAGE);
					
				}
			}
		}
		);
		
		controlsLabel.add(btnSave);
		
		JButton btnSolve = new JButton("Solve");
		btnSolve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				showBoardCompleted(crossword, boardGrid);
							
			}
		});
		controlsLabel.add(btnSolve);
		contentPane.setLayout(gl_contentPane);
				
		
	}
	
	//--------------------------------------------------------------------------------------------------------------
	//FUNKCJE GENERUJ¥CE GRAFIKÊ KRZY¯ÓWKI I PODPOWIEDZI
	
	/**
	 * Czyœci tablice z poprzedniej funkcji
	 * @param grid tablica do wyczyszczenia
	 */
	private void clearBoard(Grid grid){
		
		grid.fillCells = new LinkedList<Point>();
		grid.letters = new LinkedList<String>();
		
	}
	
	/**
	 * Generuje i wyœwietla pusty szkielet krzy¿ówki.
	 * @param cwd krzy¿ówka, która zostanie wyœwietlona
	 * @param grid pole, na którym zostanie wyœwietlona krzy¿ówka
	 */
	private void generateEmptyBoard(Crossword cwd, Grid grid) {
		
		Board b = cwd.getBoardCopy();
		
		for(int i=0; i<b.getHeight(); i++){
			for(int j=0; j<b.getWidth(); j++){
	
				if(b.getCell(i, j).getContent() != ' ')
					
					grid.fillCell(j, i);
			}
		}
		
		showClues(cwd.getClues(), cluesField);
		
	}
	
	/**
	 * Generuje i wyœwietla wype³nion¹ krzy¿ówkê.
	 * @param cwd krzy¿ówka, która zostanie wyœwietlona
	 * @param grid pole, w którym zostanie wyœwietlona krzy¿ówka
	 */
	private void showBoardCompleted(Crossword cwd, Grid grid){

		try{
			Board b = cwd.getBoardCopy();
	
			for(int i=0; i<b.getHeight(); i++){
				for(int j=0; j<b.getWidth(); j++){
		
					if(b.getCell(i, j).getContent() != ' '){
	
						grid.fillCell(j, i, Character.toString(b.getCell(i, j).getContent()) );
						
					}
				}
			}
			
			showClues(cwd.getClues(), cluesField);
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(frame, "No crossword selected.","Error!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Funkcja odpowiadaj¹ca za wyœwietlanie wskazówek do hase³.
	 * @param clues podpowiedzi, do hase³ krzy¿ówki
	 * @param textField pole, w którym zostan¹ wyœwietlone has³a
	 */
	private void showClues(LinkedList<String> clues, JTextPane textField){
		
		String text = new String("<html>");
				
		for( int i=0; i<clues.size(); i++){
			text = text.concat(i + ". " + clues.get(i) + "<br>");
		}
		
		text = text.concat("</html>");
		
		textField.setText(text);
		
	}	
	
	/**
	 * Funkcja drukuj¹ca krzy¿ówkê.
	 */
	public void printBoard(){

		  PrinterJob pj = PrinterJob.getPrinterJob();
		  pj.setJobName("Crossword");

		  pj.setPrintable (new Printable() {    
		    public int print(Graphics pg, PageFormat pf, int pageNum){
		      if (pageNum > 1){
		      return Printable.NO_SUCH_PAGE;
		      }

		      Graphics2D g2 = (Graphics2D) pg;
		      g2.translate(pf.getImageableX(), pf.getImageableY());
		      boardGrid.paint(g2);
		      return Printable.PAGE_EXISTS;
		    }
		  });
		  if (pj.printDialog() == false)
		  return;

		  try {
		        pj.print();
		  } catch (PrinterException ex) {
		        // handle exception
		  }
	}
	/**
	 * Funkcja drukuj¹ca podpowiedzi.
	 */
	public void printClues(){

		  PrinterJob pj = PrinterJob.getPrinterJob();
		  pj.setJobName("Clues");

		  pj.setPrintable (new Printable() {    
		    public int print(Graphics pg, PageFormat pf, int pageNum){
		      if (pageNum > 1){
		      return Printable.NO_SUCH_PAGE;
		      }

		      Graphics2D g2 = (Graphics2D) pg;
		      g2.translate(pf.getImageableX(), pf.getImageableY());
		      cluesField.paint(g2);
		      return Printable.PAGE_EXISTS;
		    }
		  });
		  if (pj.printDialog() == false)
		  return;

		  try {
		        pj.print();
		  } catch (PrinterException ex) {
		        // handle exception
		  }
	}
}
