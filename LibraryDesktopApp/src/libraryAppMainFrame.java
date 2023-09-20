import java.awt.EventQueue;

 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

 

public class libraryAppMainFrame extends JFrame {
	BookBinarySearch bookBinarySearch = new BookBinarySearch();
	BookRatingSort ratingSort = new BookRatingSort();
	public DefaultTableModel model;

 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable tblBookContainer;
	private JTextField textField;
	private int currentPage = 0;
	private int rowsPerPage = 10;
	
	public void displayPage(int page, ArrayList<Book> books) {
		DefaultTableModel model = (DefaultTableModel) tblBookContainer.getModel();
		int startRow = page * rowsPerPage;
		int endRow = Math.min((page + 1) * rowsPerPage, books.size());
		
		model.setRowCount(1);
		
		model.setColumnIdentifiers(new Object[] {"ID", "ISBN", "Year", "Author", "Rating"});
		
		for (int i = startRow; i < endRow; i++) {
			Book book = books.get(i);
			Object[] rowData = {
					book.getBookTitle(),
					book.getBookIsbn(),
					book.getBookPubYear(),
					book.getBookAuthor(),
					book.getBookRating(),
			};
			model.addRow(rowData);
		}
	}
	
	

	
	/**
	 * Create the frame.
	 */
	public libraryAppMainFrame() {
		
		String filePath = "src/books(1).csv";
    	String line = "";
    	BufferedReader reader = null;
    	
    	try {
			reader = new BufferedReader(new FileReader(filePath));
			ArrayList<String[]> books = new ArrayList<>();
			
			while ((line = reader.readLine()) != null) {
				books.add(line.split(","));
			}
			
			boolean skipTheFirstIteration = true;
			
			ArrayList<Book> Books = new ArrayList<>();
			
			//Iterating through data collected
			for (String[] array : books) {
				
				//This if statement allows skips the first iteration of the loop
				if (skipTheFirstIteration) {
					skipTheFirstIteration = false;

					continue;
				}
				
				// Gets the value at the specified index in the array
				String bookAuthor = String.valueOf(array[7]);
				String bookTitle = String.valueOf(array[10]);
				int bookIdNum = Integer.parseInt(array[0]);
				String bookIsbnNum = String.valueOf(array[5]);
				double originalPublishingYear = Double.parseDouble(array[8]);
				double averageRating = Double.parseDouble(array[12]);
				
				// Turns non-string variables to strings
				String bookId = Integer.toString(bookIdNum);
				String origPubYear = Double.toString(originalPublishingYear);
				String avgRating = Double.toString(averageRating);
				
				// Creates a book object in the Book arrayList
				Book book = new Book(bookId, bookIsbnNum, bookAuthor, bookTitle, avgRating, origPubYear);
	            Books.add(book);
	            
	            
	            //System.out.println(book);

			}
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

 

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		model = new DefaultTableModel(
				new Object[][] {
					{"ID", "ISBN", "Year", "Author", "Rating"},
				},
				new String[] {
						"New column", "New column", "New column", "New column", "New column"
				}
			);
		
		tblBookContainer = new JTable();
		tblBookContainer.setModel(model);
		
		tblBookContainer.setBounds(88, 240, 514, 176);
		contentPane.add(tblBookContainer);

		JButton btnByRating = new JButton("By Rating");
		btnByRating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.sort(Books);
		        ratingSort.bookRatingSort(Books, 99, true);
		        displayPage(currentPage, Books);

		        
		        DefaultTableModel model = (DefaultTableModel) tblBookContainer.getModel();
		        model.setColumnIdentifiers(new Object[]{"ID", "ISBN", "Year", "Author", "Rating"});
				
			}
		});
		btnByRating.setBounds(416, 33, 122, 21);
		contentPane.add(btnByRating);

		JButton btnByYear = new JButton("By year");
		btnByYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.sort(Books);
		        ratingSort.bookYearSort(Books, 99, true);
		        displayPage(currentPage, Books);

		        
		        DefaultTableModel model = (DefaultTableModel) tblBookContainer.getModel();
		        model.setColumnIdentifiers(new Object[]{"ID", "ISBN", "Year", "Author", "Rating"});
			}
		});
		btnByYear.setBounds(416, 96, 122, 21);
		contentPane.add(btnByYear);

		JButton btnByAuthor = new JButton("By Author");
		btnByAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnByAuthor.setBounds(416, 160, 122, 21);
		contentPane.add(btnByAuthor);

		JLabel lblNewLabel = new JLabel("Sort:");
		lblNewLabel.setBounds(448, 11, 90, 21);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(75, 96, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Search For Book Using ID");
		lblNewLabel_1.setBounds(65, 60, 149, 37);
		contentPane.add(lblNewLabel_1);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userInput = textField.getText();

					// Searching for book based on user inputed book ID
					Collections.sort(Books);
					String searchBookId = String.valueOf(userInput);
					int searchResult = BookBinarySearch.binarySearchById(Books, searchBookId);
					Book foundBook = Books.get(searchResult);
					
					if (searchResult != -1) {
						
						model.setRowCount(1);
						
						Object[] rowData = {
							foundBook.getBookId(),
							foundBook.getBookIsbn(),
							foundBook.getBookPubYear(),
							foundBook.getBookAuthor(),
							foundBook.getBookRating(),
						};
						model.addRow(rowData);
					}
					else {
						System.out.println("Book with ID: " + searchBookId + " cannot be found.");
					}
					
					/*
					// Testing sorting books in ascending and descending order based on rating
					
					System.out.println("Top 10 Books Sorted by Rating: ");
					sortResultTextArea.append(binarySearch.bookRatingSort(Books, 10, true ));
					System.out.println("Top 10 Books Sorted by Rating: ");
					binarySearch.bookRatingSort(Books, 10, true );
					System.out.println("");
					System.out.println("Bottom 10 Books Sorted by Rating: ");
					binarySearch.bookRatingSort(Books, 10, false);
					*/
					

				}
			
		});
				
		btnSearch.setBounds(75, 125, 95, 21);
		contentPane.add(btnSearch);

		JButton btnAscendingRating = new JButton("Ascending");
		btnAscendingRating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.sort(Books);
		        ratingSort.bookRatingSort(Books, 99, false);
		        displayPage(currentPage, Books);

		        
		        DefaultTableModel model = (DefaultTableModel) tblBookContainer.getModel();
		        model.setColumnIdentifiers(new Object[]{"ID", "ISBN", "Year", "Author", "Rating"});
			}
		});
		btnAscendingRating.setBounds(373, 65, 96, 21);
		contentPane.add(btnAscendingRating);

		JButton btnDescendingRating = new JButton("Descending");
		btnDescendingRating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.sort(Books);
		        ratingSort.bookRatingSort(Books, 99, true);
		        displayPage(currentPage, Books);

		        
		        DefaultTableModel model = (DefaultTableModel) tblBookContainer.getModel();
		        model.setColumnIdentifiers(new Object[]{"ID", "ISBN", "Year", "Author", "Rating"});
			}
		});
		btnDescendingRating.setBounds(479, 65, 90, 21);
		contentPane.add(btnDescendingRating);
		
		displayPage(currentPage, Books);
		
		JButton btnPageRight = new JButton(">");
		btnPageRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage++;
				displayPage(currentPage, Books);
			}
		});
		btnPageRight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPageRight.setBounds(573, 422, 43, 23);
		contentPane.add(btnPageRight);
		
		JButton btnPageLeft = new JButton("<");
		btnPageLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentPage > 0) {
					currentPage--;
					displayPage(currentPage, Books);
				}
			}
		});
		btnPageLeft.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPageLeft.setBounds(520, 422, 43, 23);
		contentPane.add(btnPageLeft);
		
		JButton btnDescendingAuthor = new JButton("Descending");
		btnDescendingAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.sort(Books);
		        ratingSort.bookAuthorSort(Books, 99, true);
		        displayPage(currentPage, Books);

		        
		        DefaultTableModel model = (DefaultTableModel) tblBookContainer.getModel();
		        model.setColumnIdentifiers(new Object[]{"ID", "ISBN", "Year", "Author", "Rating"});
			}
		});
		
		btnDescendingAuthor.setBounds(479, 192, 90, 21);
		contentPane.add(btnDescendingAuthor);
		
		JButton btnAscendingAuthor = new JButton("Ascending");
		btnAscendingAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.sort(Books);
		        ratingSort.bookAuthorSort(Books, 99, false);
		        displayPage(currentPage, Books);

		        
		        DefaultTableModel model = (DefaultTableModel) tblBookContainer.getModel();
		        model.setColumnIdentifiers(new Object[]{"ID", "ISBN", "Year", "Author", "Rating"});
			}
		});
		btnAscendingAuthor.setBounds(373, 192, 96, 21);
		contentPane.add(btnAscendingAuthor);
		
	
		
		
		JButton btnDescendingYear = new JButton("Descending");
		btnDescendingYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.sort(Books);
		        ratingSort.bookYearSort(Books, 99, true);
		        displayPage(currentPage, Books);

		        
		        DefaultTableModel model = (DefaultTableModel) tblBookContainer.getModel();
		        model.setColumnIdentifiers(new Object[]{"ID", "ISBN", "Year", "Author", "Rating"});
			}
		});
		btnDescendingYear.setBounds(479, 125, 90, 21);
		contentPane.add(btnDescendingYear);
		
		JButton btnAscendingYear = new JButton("Ascending");
		btnAscendingYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.sort(Books);
		        ratingSort.bookYearSort(Books, 99, false);
		        displayPage(currentPage, Books);

		        
		        DefaultTableModel model = (DefaultTableModel) tblBookContainer.getModel();
		        model.setColumnIdentifiers(new Object[]{"ID", "ISBN", "Year", "Author", "Rating"});
			}
		});
		btnAscendingYear.setBounds(373, 125, 96, 21);
		contentPane.add(btnAscendingYear);
		
		
		
		
	}catch (FileNotFoundException e1) {

		e1.printStackTrace();

	} catch (IOException e1) {

		e1.printStackTrace();

	}
}
}