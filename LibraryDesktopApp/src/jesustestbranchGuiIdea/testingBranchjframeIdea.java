package jesustestbranchGuiIdea;

//WindowBuilder imports
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
//Program imports
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Arrays;
import java.util.Iterator;

class Book {
	/*
	 * Things to include in Table String Authors; int OriginalPublicationYear; int
	 * Isbn; int BookID; String Title; Double AverageRating;
	 */
	LinkedList<Integer> bookId = new LinkedList<Integer>();
	LinkedList<Integer> originalPublicationYear = new LinkedList<Integer>();
	LinkedList<String> Isbn = new LinkedList<String>();
	LinkedList<String> Title = new LinkedList<String>();
	LinkedList<Double> AverageRating = new LinkedList<Double>();
	LinkedList<String> Author = new LinkedList<String>();
}

public class testingBranchjframeIdea extends JFrame {

	private static Book BookObjMethod = new Book();
	private DefaultTableModel model;
	private int pageShowing = 0;
	private boolean Ascending = true;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblBookContainer;
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	public class Timer {

		private long startTime = 0;

		private long endTime = 0;

		public void start() {

			this.startTime = System.currentTimeMillis();

		}

		public void end() {

			this.endTime = System.currentTimeMillis();

		}

		public long getStartTime() {

			return this.startTime;

		}

		public long getEndTime() {

			return this.endTime;

		}

		public long getTotalTime() {

			return this.endTime - this.startTime;

		}

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testingBranchjframeIdea frame = new testingBranchjframeIdea();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// Code here
		// Reading
		// File________________________________________________________________________________________________________________
		String Path = "src/books.csv";
		String line = "";

		BufferedReader Reader;
		
		try {
			Reader = new BufferedReader(new FileReader(Path));
			//Reads file and stores the content in a linkedList that contains String arrays
			LinkedList<String[]> books = new LinkedList<String[]>();

			while ((line = Reader.readLine()) != null) {
				//Uses the .split() method to separate the data
				books.add(line.split(","));

			}
			//close reader to avoid unexpected errors
			Reader.close();

			boolean skipTheFirstIteration = true;

			// Iterating through data collected
			for (String[] array : books) {

				// This if statement allows skips the first iteration of the loop
				if (skipTheFirstIteration) {

					skipTheFirstIteration = false;
					continue;
				}
				BookObjMethod.bookId.add(Integer.parseInt(array[0]));
				BookObjMethod.Title.add(array[10]);
				BookObjMethod.Isbn.add(array[5]);
				BookObjMethod.Author.add(array[7]);
				BookObjMethod.originalPublicationYear.add(Integer.parseInt(array[8]));
				BookObjMethod.AverageRating.add(Double.parseDouble(array[12]));

			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//Costume Methods
//SelectionSort for integers_________________________________________________________________________________________________________
	/**
	 * @author downey
	 *
	 *         /** Swaps the elements at indexes i and j.
	 * @param dbl1
	 * @param str2
	 * @param str1
	 * @param justSwappingInt
	 * @param str3
	 */
	
	//Swaps all the elements, ones it determines the integer that needs to which places
	public static void swapElementsInt(LinkedList<Integer> LLIntergerSort, int i, int j,
			LinkedList<Integer> justSwappingInt, LinkedList<String> str1, LinkedList<String> str2,
			LinkedList<String> str3, LinkedList<Double> dbl1) {
		int temp = LLIntergerSort.get(i);
		LLIntergerSort.set(i, LLIntergerSort.get(j));
		LLIntergerSort.set(j, temp);

		// swamping the rest of inputed data
		int temp1 = justSwappingInt.get(i);
		justSwappingInt.set(i, justSwappingInt.get(j));
		justSwappingInt.set(j, temp1);

		String temp2 = str1.get(i);
		str1.set(i, str1.get(j));
		str1.set(j, temp2);

		String temp3 = str2.get(i);
		str2.set(i, str2.get(j));
		str2.set(j, temp3);

		String temp4 = str3.get(i);
		str3.set(i, str3.get(j));
		str3.set(j, temp4);

		Double temp5 = dbl1.get(i);
		dbl1.set(i, dbl1.get(j));
		dbl1.set(j, temp5);

	}

	
	//Finds the index of the lowest value between indices low and high (inclusive).
	public static int indexLowestInt(LinkedList<Integer> LLIntegerSort, int start) {
		int lowIndex = start;
		for (int i = start; i < LLIntegerSort.size(); i++) {
			if (LLIntegerSort.get(i) < LLIntegerSort.get(lowIndex)) {
				lowIndex = i;
			}
		}
		return lowIndex;
	}

	//Sorts the first integer variable passed
	public static void selectionSortInt(LinkedList<Integer> LLIntegerSort, LinkedList<Integer> JustSwappingInt,
			LinkedList<String> str1, LinkedList<String> str2, LinkedList<String> str3, LinkedList<Double> Dbl1) {
		for (int i = 0; i < LLIntegerSort.size(); i++) {
			int j = indexLowestInt(LLIntegerSort, i);
			swapElementsInt(LLIntegerSort, i, j, JustSwappingInt, str1, str2, str3, Dbl1);

		}

	}

//Selection Sort for strings
	//Swaps all the elements, ones it determines the string that needs to which places
	public static void swapElementsString(LinkedList<String> str1, LinkedList<Integer> LLIntergerSort, int i, int j,
			LinkedList<Integer> justSwappingInt, LinkedList<String> str2, LinkedList<String> str3,
			LinkedList<Double> dbl1) {
		int temp = LLIntergerSort.get(i);
		LLIntergerSort.set(i, LLIntergerSort.get(j));
		LLIntergerSort.set(j, temp);

		// swamping the rest of inputed data
		int temp1 = justSwappingInt.get(i);
		justSwappingInt.set(i, justSwappingInt.get(j));
		justSwappingInt.set(j, temp1);

		String temp2 = str1.get(i);
		str1.set(i, str1.get(j));
		str1.set(j, temp2);

		String temp3 = str2.get(i);
		str2.set(i, str2.get(j));
		str2.set(j, temp3);

		String temp4 = str3.get(i);
		str3.set(i, str3.get(j));
		str3.set(j, temp4);

		Double temp5 = dbl1.get(i);
		dbl1.set(i, dbl1.get(j));
		dbl1.set(j, temp5);
	}
	


	//Finds the index of the lowest string alphabetically
	public static int indexLowestString(LinkedList<String> str1, int start) {
		int lowIndex = start;
		for (int i = start; i < str1.size(); i++) {
			if (str1.get(i).compareTo(str1.get(lowIndex)) < 0) {
				lowIndex = i;
			}
		}
		return lowIndex;
	}

	//Sorts the first string variable passed
	public static void selectionSortString(LinkedList<String> str1, LinkedList<Integer> LLIntegerSort,
			LinkedList<Integer> JustSwappingInt, LinkedList<String> str2, LinkedList<String> str3,
			LinkedList<Double> Dbl1) {
		for (int i = 0; i < LLIntegerSort.size(); i++) {
			int j = indexLowestString(str1, i);
			swapElementsString(str1, LLIntegerSort, i, j, JustSwappingInt, str2, str3, Dbl1);
		}

	}
	

// Selection sort double
	
	//Swaps all the elements, ones it determines the double that needs to which places
	public static void swapElementsDouble(LinkedList<Double> dbl1, int i, int j, LinkedList<Integer> justSwappingInt1,
			LinkedList<Integer> justSwappingInt2, LinkedList<String> str1, LinkedList<String> str2,
			LinkedList<String> str3) {
		double temp = dbl1.get(i);
		dbl1.set(i, dbl1.get(j));
		dbl1.set(j, temp);

		// Swapping the rest of the input data
		int temp1 = justSwappingInt1.get(i);
		justSwappingInt1.set(i, justSwappingInt1.get(j));
		justSwappingInt1.set(j, temp1);

		String temp2 = str1.get(i);
		str1.set(i, str1.get(j));
		str1.set(j, temp2);

		String temp3 = str2.get(i);
		str2.set(i, str2.get(j));
		str2.set(j, temp3);

		String temp4 = str3.get(i);
		str3.set(i, str3.get(j));
		str3.set(j, temp4);

		int temp5 = justSwappingInt2.get(i);
		justSwappingInt2.set(i, justSwappingInt2.get(j));
		justSwappingInt2.set(j, temp5);
	}

	//Searches for the the lowest double 
	public static int indexLowestDouble(LinkedList<Double> dbl1, int start) {
		int lowIndex = start;
		for (int i = start; i < dbl1.size(); i++) {
			if (dbl1.get(i) < dbl1.get(lowIndex)) {
				lowIndex = i;
			}
		}
		return lowIndex;
	}
	//Selection sort using the Double variable passed
	public static void selectionSortDouble(LinkedList<Double> dbl1, LinkedList<Integer> justSwappingInt1,
			LinkedList<Integer> justSwappingInt2, LinkedList<String> str1, LinkedList<String> str2,
			LinkedList<String> str3) {
		for (int i = 0; i < dbl1.size(); i++) {
			int j = indexLowestDouble(dbl1, i);
			swapElementsDouble(dbl1, i, j, justSwappingInt1, justSwappingInt2, str1, str2, str3);

		}
	}


	// Takes the passed linked list of integers and an int value, then it searches
	// if the int value is in the LinkedList
	public static int returnPositionInt(LinkedList<Integer> IntLinkList, int intValue) {

		// Using the Iterator Class to search for passed LinkList using int value
		Iterator<Integer> itr = IntLinkList.iterator();
		int i = 0;
		while (itr.hasNext()) {
			Integer currentValue = itr.next();
			if (currentValue.equals(intValue)) {
				return i;
			}
			i++;
		}
		// Return -1 if not found
		return -1;
	}

	// Just in case we want to search for ISBN
	public static int returnPositionStr(LinkedList<String> IntLinkList, String intValue) {

		// Using the Iterator Class
		Iterator<String> itr = IntLinkList.iterator();
		int i = 0;
		while (itr.hasNext()) {
			String currentValue = itr.next();
			if (currentValue.equals(intValue)) {
				return i;
			}
			i++;
		}
		// Return -1 if not found
		return -1;
	}

	/**
	 * Create the frame.
	 */
	public testingBranchjframeIdea() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		model = new DefaultTableModel(new Object[][] { { "ID", "Title", "ISBN", "Year", "Author", "Rating" },
		//Initial rows here
		}, new String[] { "New column", "New column", "New column", "New column", "New column", "New column" });
		//Add values ones the table is initialized
		for (int i = 0; i < 10; i++) {

			Object[] rowData = { BookObjMethod.bookId.get(i), BookObjMethod.Title.get(i), BookObjMethod.Isbn.get(i),
					BookObjMethod.originalPublicationYear.get(i), BookObjMethod.Author.get(i),
					BookObjMethod.AverageRating.get(i) };
			model.addRow(rowData);
		}

		tblBookContainer = new JTable();
		tblBookContainer.setModel(model);

		tblBookContainer.setBounds(88, 240, 514, 176);
		contentPane.add(tblBookContainer);

		JButton btnByRating = new JButton("By Rating");
		btnByRating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Set your ascending tracker to true because when sorting it outputs in ascending
				Ascending = true;
				//Call the modify selection sort for doubles
				selectionSortDouble(BookObjMethod.AverageRating, BookObjMethod.originalPublicationYear,
						BookObjMethod.bookId, BookObjMethod.Title, BookObjMethod.Isbn, BookObjMethod.Author);
				//Reset Table
				model.setRowCount(1);
				pageShowing = 0;
				//Show corresponding data according to the pageShowing tracker
				for (int i = 0; i < 10; i++) {

					Object[] rowData = { BookObjMethod.bookId.get(i), BookObjMethod.Title.get(i),
							BookObjMethod.Isbn.get(i), BookObjMethod.originalPublicationYear.get(i),
							BookObjMethod.Author.get(i), BookObjMethod.AverageRating.get(i) };
					model.addRow(rowData);
				}

			}
		});
		btnByRating.setBounds(494, 66, 122, 21);
		contentPane.add(btnByRating);

		JButton btnByYear = new JButton("By year");
		btnByYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Set your ascending tracker to true because when sorting it outputs in ascending
				Ascending = true;
				//Call the modify selection sort for integers
				selectionSortInt(BookObjMethod.originalPublicationYear, BookObjMethod.bookId, BookObjMethod.Title,
						BookObjMethod.Isbn, BookObjMethod.Author, BookObjMethod.AverageRating);
				//Reset Table
				model.setRowCount(1);
				pageShowing = 0;
				//Show corresponding data according to the pageShowing tracker
				for (int i = 0; i < 10; i++) {

					Object[] rowData = { BookObjMethod.bookId.get(i), BookObjMethod.Title.get(i),
							BookObjMethod.Isbn.get(i), BookObjMethod.originalPublicationYear.get(i),
							BookObjMethod.Author.get(i), BookObjMethod.AverageRating.get(i) };
					model.addRow(rowData);
				}
			}

		});
		btnByYear.setBounds(494, 97, 122, 21);
		contentPane.add(btnByYear);

		JButton btnByAuthor = new JButton("By Author");
		btnByAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Set your ascending tracker to true because when sorting it outputs in ascending
				Ascending = true;
				//Call the modify selection sort for strings
				selectionSortString(BookObjMethod.Author, BookObjMethod.bookId, BookObjMethod.originalPublicationYear,
						BookObjMethod.Isbn, BookObjMethod.Title, BookObjMethod.AverageRating);
				//Reset Table content
				model.setRowCount(1);
				pageShowing = 0;
				//Show corresponding data according to the pageShowing tracker
				for (int i = 0; i < 10; i++) {

					Object[] rowData = { BookObjMethod.bookId.get(i), BookObjMethod.Title.get(i),
							BookObjMethod.Isbn.get(i), BookObjMethod.originalPublicationYear.get(i),
							BookObjMethod.Author.get(i), BookObjMethod.AverageRating.get(i) };
					model.addRow(rowData);
				}
			}
		});
		btnByAuthor.setBounds(494, 128, 122, 21);
		contentPane.add(btnByAuthor);

		JLabel lblNewLabel = new JLabel("Sort:");
		lblNewLabel.setBounds(457, 35, 90, 21);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(75, 96, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Search For Book Using ID:");
		lblNewLabel_1.setBounds(49, 58, 204, 37);
		contentPane.add(lblNewLabel_1);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Timer time = new Timer();
				
				try {
					//Get inputed book ID
					int inputedInt = Integer.parseInt(textField.getText());
					//start timer for search in a LinkList
					time.start();
					//call the searching method
					int bookPositionIndex = returnPositionInt(BookObjMethod.bookId, inputedInt);
					//end timer
					time.end();
					//Print out timer results
					System.out.println(time.getTotalTime());
					
					//If book was found print out book details
					if (bookPositionIndex != -1) {

						model.setRowCount(1);

						
						Object[] rowData = { BookObjMethod.bookId.get(bookPositionIndex),
								BookObjMethod.Isbn.get(bookPositionIndex),
								BookObjMethod.originalPublicationYear.get(bookPositionIndex),
								BookObjMethod.Author.get(bookPositionIndex),
								BookObjMethod.AverageRating.get(bookPositionIndex) };
						model.addRow(rowData);

					} else {
						System.out.println("Couldn't Find the book! (ID NOT FOUND)");
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Inputed Search Value invalid (Number only) try again!", "Alert", JOptionPane.INFORMATION_MESSAGE);
					textField.setText("");
					e1.printStackTrace();
					
				}
				
			}
		});
		btnSearch.setBounds(75, 125, 95, 21);
		contentPane.add(btnSearch);

		JButton btnAscending = new JButton("Ascending");
		btnAscending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If is not already in ascending order put put table in ascending order using stacks
				if (Ascending == false) {

					Ascending = true;

					Stack<Integer> stacksBookId = new Stack<Integer>();
					Stack<Integer> stacksYear = new Stack<Integer>();
					Stack<String> stacksTitle = new Stack<String>();
					Stack<String> stacksIsbn = new Stack<String>();
					Stack<String> stacksAuthor = new Stack<String>();
					Stack<Double> stacksRating = new Stack<Double>();
					for (int i = 0; i < BookObjMethod.bookId.size(); i++) {
						stacksBookId.push(BookObjMethod.bookId.get(i));
						stacksYear.push(BookObjMethod.originalPublicationYear.get(i));
						stacksTitle.push(BookObjMethod.Title.get(i));
						stacksIsbn.push(BookObjMethod.Isbn.get(i));
						stacksAuthor.push(BookObjMethod.Author.get(i));
						stacksRating.push(BookObjMethod.AverageRating.get(i));

					}

					BookObjMethod.bookId.clear();
					BookObjMethod.Title.clear();
					BookObjMethod.Isbn.clear();
					BookObjMethod.originalPublicationYear.clear();
					BookObjMethod.Author.clear();
					BookObjMethod.AverageRating.clear();

					while (!stacksBookId.isEmpty()) {

						BookObjMethod.bookId.add(stacksBookId.pop());
						BookObjMethod.Title.add(stacksTitle.pop());
						BookObjMethod.Isbn.add(stacksIsbn.pop());
						BookObjMethod.originalPublicationYear.add(stacksYear.pop());
						BookObjMethod.Author.add(stacksAuthor.pop());
						BookObjMethod.AverageRating.add(stacksRating.pop());

					}
					//Reset page count
					pageShowing = 0;
					//Reset table
					model.setRowCount(1);
					//Use the page count to display the data that belongs to that table
					for (int i = pageShowing * 10; i < (pageShowing + 1) * 10; i++) {

						Object[] rowData = { BookObjMethod.bookId.get(i), BookObjMethod.Title.get(i),
								BookObjMethod.Isbn.get(i), BookObjMethod.originalPublicationYear.get(i),
								BookObjMethod.Author.get(i), BookObjMethod.AverageRating.get(i) };
						model.addRow(rowData);

					}
				}
			}
		});
		btnAscending.setBounds(423, 159, 122, 21);
		contentPane.add(btnAscending);

		JButton btnDescending = new JButton("Descending");
		btnDescending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If is in ascending order put put table in descending order using stacks
				if (Ascending == true) {

					Ascending = false;

					Stack<Integer> stacksBookId = new Stack<Integer>();
					Stack<Integer> stacksYear = new Stack<Integer>();
					Stack<String> stacksTitle = new Stack<String>();
					Stack<String> stacksIsbn = new Stack<String>();
					Stack<String> stacksAuthor = new Stack<String>();
					Stack<Double> stacksRating = new Stack<Double>();
					for (int i = 0; i < BookObjMethod.bookId.size(); i++) {
						stacksBookId.push(BookObjMethod.bookId.get(i));
						stacksYear.push(BookObjMethod.originalPublicationYear.get(i));
						stacksTitle.push(BookObjMethod.Title.get(i));
						stacksIsbn.push(BookObjMethod.Isbn.get(i));
						stacksAuthor.push(BookObjMethod.Author.get(i));
						stacksRating.push(BookObjMethod.AverageRating.get(i));

					}

					BookObjMethod.bookId.clear();
					BookObjMethod.Title.clear();
					BookObjMethod.Isbn.clear();
					BookObjMethod.originalPublicationYear.clear();
					BookObjMethod.Author.clear();
					BookObjMethod.AverageRating.clear();

					while (!stacksBookId.isEmpty()) {

						BookObjMethod.bookId.add(stacksBookId.pop());
						BookObjMethod.Title.add(stacksTitle.pop());
						BookObjMethod.Isbn.add(stacksIsbn.pop());
						BookObjMethod.originalPublicationYear.add(stacksYear.pop());
						BookObjMethod.Author.add(stacksAuthor.pop());
						BookObjMethod.AverageRating.add(stacksRating.pop());

					}
					//Reset page count
					pageShowing = 0;
					//Reset table
					model.setRowCount(1);
					//Use the page count to display the data that belongs to that table
					for (int i = pageShowing * 10; i < (pageShowing + 1) * 10; i++) {

						Object[] rowData = { BookObjMethod.bookId.get(i), BookObjMethod.Title.get(i),
								BookObjMethod.Isbn.get(i), BookObjMethod.originalPublicationYear.get(i),
								BookObjMethod.Author.get(i), BookObjMethod.AverageRating.get(i) };
						model.addRow(rowData);

					}
				}

			}
		});
		btnDescending.setBounds(563, 159, 106, 21);
		contentPane.add(btnDescending);

		JButton btnPageLeft = new JButton("<");
		btnPageLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If page is showing the most left page do nothing
				if (pageShowing == 0) {

				} 
				//else subtract one from page showing and use that to display the info
				else {
					
					pageShowing--;
					model.setRowCount(1);
					for (int i = pageShowing * 10; i < (pageShowing + 1) * 10; i++) {

						Object[] rowData = { BookObjMethod.bookId.get(i), BookObjMethod.Title.get(i),
								BookObjMethod.Isbn.get(i), BookObjMethod.originalPublicationYear.get(i),
								BookObjMethod.Author.get(i), BookObjMethod.AverageRating.get(i) };
						model.addRow(rowData);

					}

				}

			}
		});
		btnPageLeft.setBounds(497, 426, 50, 21);
		contentPane.add(btnPageLeft);

		JButton btnPageRight = new JButton(">");
		btnPageRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Add one to page showing to keep track of page
				pageShowing++;
				//Reset table
				model.setRowCount(1);
				//Use the pageShowing tracker to pull the corresponding information
				for (int i = pageShowing * 10; i < (pageShowing + 1) * 10; i++) {

					Object[] rowData = { BookObjMethod.bookId.get(i), BookObjMethod.Title.get(i),
							BookObjMethod.Isbn.get(i), BookObjMethod.originalPublicationYear.get(i),
							BookObjMethod.Author.get(i), BookObjMethod.AverageRating.get(i) };
					model.addRow(rowData);

				}
			}
		});
		btnPageRight.setBounds(552, 427, 50, 21);
		contentPane.add(btnPageRight);
	}
}
