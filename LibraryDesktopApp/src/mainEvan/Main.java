package mainEvan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;


class Book implements Comparable<Book>{
	
    private String bookId;
    private String isbn;
    private String author;
    private String title;
    private String rating;
    private String publishingYear;
    
    // makes a book object
    public Book(String bookId, String isbn, String author, String title, String rating, String publishingYear) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.rating = rating;
        this.publishingYear = publishingYear;
    }
    
    public Book() {
		// TODO Auto-generated constructor stub
	}

	// gets the book's ID
    public String getBookId() {
    	return this.bookId;
    }
    
    // gets the book's Isbn
    public String getBookIsbn() {
    	return this.isbn;
    }
    
    // gets the book's author
    public String getBookAuthor() {
    	return this.author;
    }
    
    // gets the book's title
    public String getBookTitle() {
    	return this.title;
    }
    
    // gets the book's rating
    public String getBookRating() {
    	return this.rating;
    }
    
    // gets the book's publishing year
    public String getBookPubYear() {
    	return this.publishingYear;
    }
    

    @Override
    public String toString() {
        return "Book ID: " + bookId +
               ", ISBN: " + isbn +
               ", Author: " + author +
               ", Title: " + title +
               ", Rating: " + rating +
               ", Publishing Year: " + publishingYear;
    }
    
    public int compareTo(Book otherBook) {
    	return this.getBookId().compareTo(otherBook.getBookId());
    }

}

// Searches for a book by it's ID
class BookBinarySearch {
	
	public int binarySearchById(ArrayList<Book> books, String bookId) {
		int left = 0;
		int right = books.size() - 1;
		
		while (left <= right) {
			int mid = left + (right - left) / 2;
			int compareResult = books.get(mid).getBookId().compareTo(bookId);
			
			if (compareResult == 0) {
				return mid;
			}
			else if (compareResult < 0) {
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		
		return -1;
	}
}

// Sorts top and bottom 10 books based on rating
class BookRatingSort {

    public void bookRatingSort(ArrayList<Book> books, int topCount, boolean ascending) {
        // Sort the books by rating
    	if (ascending) {
    		books.sort((book1, book2) -> Double.compare(Double.parseDouble(book2.getBookRating()), Double.parseDouble(book1.getBookRating())));
    	}
    	else {
    		books.sort((book1, book2) -> Double.compare(Double.parseDouble(book1.getBookRating()), Double.parseDouble(book2.getBookRating())));
    		
    	}
        

        // Makes sure it doesn't exceed the size of the arraylist
        int numBooks = Math.min(topCount, books.size());
        
        // Searches to find top books by rating
        for (int i = 0; i < numBooks; i++) {
            Book book = books.get(i);
            System.out.println("Book title: " + book.getBookTitle() + ", Rating: " + book.getBookRating());
        }
        
    }
}

class AppFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables for Home Panel
	private static JPanel startPanel;
	private static JButton toBookSearchBtn = new JButton();
	private static JLabel titleLabel = new JLabel();
	private static String userInput;
	private static JButton topTenBooksBtn = new JButton();
	private static JButton bottomTenBooksBtn = new JButton();
	
	// Variables for Book Search Panel
	private static JPanel bookSearchPanel = new JPanel();
	private static JLabel bookSearchTitleLabel = new JLabel();
	private static JButton bookSearchHomeBtn = new JButton();
	private static JTextField userInputFld = new JTextField();
	private static JLabel searchLabel = new JLabel();
	private static JButton searchBookBtn = new JButton();
	
	// Variables for Search Result Panel
	private static JPanel searchResultPanel = new JPanel();
	private static JLabel searchResultTitleLabel = new JLabel();
	private static JButton searchHomeBtn = new JButton();
	private static JTextArea searchResultTextArea = new JTextArea();
	private static JButton searchBackBtn = new JButton();
	
	// Variables for Sort Result Panel
	private static JPanel sortResultPanel = new JPanel();
	private static JLabel sortResultTitleLabel = new JLabel();
	private static JButton sortHomeBtn = new JButton();
	JTextArea sortResultTextArea = new JTextArea();
	private static JButton sortBackBtn = new JButton();
	
	AppFrame() {
		
		this.setTitle("Library App");
		getContentPane().setLayout(new BorderLayout(10, 10));
		this.getContentPane().setBackground(Color.decode("#304529"));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(500, 600));
		this.setResizable(false);
		
		
		// Home Panel
		startPanel = new JPanel();
		startPanel.setBackground(Color.decode("#304529"));
		startPanel.setBounds(0, 0, 455, 600);
		startPanel.setLayout(null);
		startPanel.setVisible(true);
		
		titleLabel.setText("Library App");
		titleLabel.setBounds(170, 30, 150, 25);
		titleLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
		titleLabel.setForeground(Color.white);
		titleLabel.setVisible(true);
		
		toBookSearchBtn = new JButton("Search for Book");
		toBookSearchBtn.setFont(new Font("Courier New", Font.PLAIN, 13));
		toBookSearchBtn.setBounds(140, 200, 182, 70);
		toBookSearchBtn.setFocusable(false);
		toBookSearchBtn.addActionListener(this);
		toBookSearchBtn.setVisible(true);
		
		topTenBooksBtn = new JButton("Top 10 Books by Rating");
		topTenBooksBtn.setFont(new Font("Courier New", Font.PLAIN, 10));
		topTenBooksBtn.setBounds(140, 275, 182, 70);
		topTenBooksBtn.setFocusable(false);
		topTenBooksBtn.addActionListener(this);
		topTenBooksBtn.setVisible(true);
		
		bottomTenBooksBtn = new JButton("Bottom 10 Books by Rating");
		bottomTenBooksBtn.setFont(new Font("Courier New", Font.PLAIN, 10));
		bottomTenBooksBtn.setBounds(140, 350, 182, 70);
		bottomTenBooksBtn.setFocusable(false);
		bottomTenBooksBtn.addActionListener(this);
		bottomTenBooksBtn.setVisible(true);
		
		startPanel.add(titleLabel);
		startPanel.add(toBookSearchBtn);
		startPanel.add(topTenBooksBtn);
		startPanel.add(bottomTenBooksBtn);
		
		
		// Book Search Panel
		bookSearchPanel = new JPanel();
		bookSearchPanel.setBackground(Color.decode("#304529"));
		bookSearchPanel.setBounds(0, 0, 455, 600);
		bookSearchPanel.setLayout(null);
		bookSearchPanel.setVisible(false);
		
		bookSearchTitleLabel.setText("Book Search");
		bookSearchTitleLabel.setBounds(170, 30, 150, 25);
		bookSearchTitleLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
		bookSearchTitleLabel.setForeground(Color.white);
		bookSearchTitleLabel.setVisible(true);
		
		searchLabel.setText("Search for Book by ID");
		searchLabel.setBounds(155, 220, 150, 25);
		searchLabel.setFont(new Font("Courier New", Font.PLAIN, 12));
		searchLabel.setForeground(Color.white);
		searchLabel.setVisible(true);
		
		bookSearchHomeBtn = new JButton("Home");
		bookSearchHomeBtn.setFont(new Font("Courier New", Font.PLAIN, 13));
		bookSearchHomeBtn.setBounds(140, 55, 180, 35);
		bookSearchHomeBtn.setFocusable(false);
		bookSearchHomeBtn.addActionListener(this);
		bookSearchHomeBtn.setVisible(true);
		
		userInputFld.setFont(new Font("Courier New", Font.PLAIN, 13));
		userInputFld.setPreferredSize(new Dimension(20, 30));
		userInputFld.setCaretColor(Color.black); // cursor color
		userInputFld.setEditable(true);
		userInputFld.setBounds(140, 250, 180, 35);
		userInputFld.setVisible(true);
		
		searchBookBtn = new JButton("Search");
		searchBookBtn.setFont(new Font("Courier New", Font.PLAIN, 13));
		searchBookBtn.setBounds(140, 300, 180, 35);
		searchBookBtn.setFocusable(false);
		searchBookBtn.addActionListener(this);
		searchBookBtn.setVisible(true);
		
		bookSearchPanel.add(bookSearchTitleLabel);
		bookSearchPanel.add(bookSearchHomeBtn);
		bookSearchPanel.add(userInputFld);
		bookSearchPanel.add(searchLabel);
		bookSearchPanel.add(searchBookBtn);
		
		// Search Result Panel
		searchResultPanel = new JPanel();
		searchResultPanel.setBackground(Color.decode("#304529"));
		searchResultPanel.setBounds(0, 0, 455, 600);
		searchResultPanel.setLayout(null);
		searchResultPanel.setVisible(false);
		
		searchResultTitleLabel.setText("Search Results");
		searchResultTitleLabel.setBounds(170, 30, 150, 25);
		searchResultTitleLabel.setFont(new Font("Courier New", Font.PLAIN, 15));
		searchResultTitleLabel.setForeground(Color.white);
		searchResultTitleLabel.setVisible(true);
		
		searchHomeBtn = new JButton("Home");
		searchHomeBtn.setFont(new Font("Courier New", Font.PLAIN, 13));
		searchHomeBtn.setBounds(140, 55, 180, 35);
		searchHomeBtn.setFocusable(false);
		searchHomeBtn.addActionListener(this);
		searchHomeBtn.setVisible(true);
		
		searchResultTextArea = new JTextArea(400, 400);
		searchResultTextArea.setFont(new Font("Courier New", Font.PLAIN, 14));
		searchResultTextArea.setEditable(false);
		JScrollPane searchResultPane = new JScrollPane(searchResultTextArea);
		searchResultPane.setBounds(30, 100, 400, 410);
		
		searchBackBtn = new JButton("Back");
		searchBackBtn.setFont(new Font("Courier New", Font.PLAIN, 13));
		searchBackBtn.setBounds(140, 515, 180, 35);
		searchBackBtn.setFocusable(false);
		searchBackBtn.addActionListener(this);
		searchBackBtn.setVisible(true);
		
		searchResultPanel.add(searchResultTitleLabel);
		searchResultPanel.add(searchHomeBtn);
		searchResultPanel.add(searchResultPane);
		searchResultPanel.add(searchBackBtn);
		
		// Sort Result Panel
		sortResultPanel = new JPanel();
		sortResultPanel.setBackground(Color.decode("#304529"));
		sortResultPanel.setBounds(0, 0, 455, 600);
		sortResultPanel.setLayout(null);
		sortResultPanel.setVisible(false);
		
		sortResultTitleLabel.setText("Search Results");
		sortResultTitleLabel.setBounds(170, 30, 150, 25);
		sortResultTitleLabel.setFont(new Font("Courier New", Font.PLAIN, 15));
		sortResultTitleLabel.setForeground(Color.white);
		sortResultTitleLabel.setVisible(true);
		
		sortHomeBtn = new JButton("Home");
		sortHomeBtn.setFont(new Font("Courier New", Font.PLAIN, 13));
		sortHomeBtn.setBounds(140, 55, 180, 35);
		sortHomeBtn.setFocusable(false);
		sortHomeBtn.addActionListener(this);
		sortHomeBtn.setVisible(true);
		
		sortResultTextArea = new JTextArea(400, 400);
		sortResultTextArea.setFont(new Font("Courier New", Font.PLAIN, 14));
		sortResultTextArea.setEditable(false);
		JScrollPane sortResultPane = new JScrollPane(sortResultTextArea);
		sortResultPane.setBounds(30, 100, 400, 410);

		sortResultPanel.add(sortResultTitleLabel);
		sortResultPanel.add(sortHomeBtn);
		sortResultPanel.add(sortResultPane);
		sortResultPanel.add(sortBackBtn);
		
		
		getContentPane().add(startPanel);
		getContentPane().add(bookSearchPanel);
		getContentPane().add(searchResultPanel);
		getContentPane().add(sortResultPanel);
		
		
	}


	public void actionPerformed(ActionEvent e) {
		Scanner scnr = new Scanner(System.in);
		BookRatingSort binarySearch = new BookRatingSort();
		BookBinarySearch bookBinarySearch = new BookBinarySearch();
		
		if (e.getSource() == toBookSearchBtn) {
			try {
				bookSearchWindow();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == bookSearchHomeBtn) {
			try {
				screenReset();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == searchBookBtn) {
			userInput = userInputFld.getText();
			searchResultTextArea.setText("");
			try {
				bookSearchResultWindow();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			// Reading File
			String Path = "src/books(1).csv";
			String line = "";

				BufferedReader Reader;

				try {

					Reader = new BufferedReader(new FileReader(Path));
					ArrayList<String[]> books = new ArrayList<String[]>();

					while((line = Reader.readLine()) != null) {
						books.add(line.split(","));
					}
					
					
				// Creating Book Object
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
					
					// Searching for book based on user inputed book ID
					Collections.sort(Books);
					String searchBookId = String.valueOf(userInput);
					int searchResult = bookBinarySearch.binarySearchById(Books, searchBookId);
					
					if (searchResult != -1) {
						Book foundBook = Books.get(searchResult);
						searchResultTextArea.append("Book found: " + foundBook + "\n");
						searchResultTextArea.append("Book ID: " + foundBook.getBookId() + "\n");
						searchResultTextArea.append("Book ISBN: " + foundBook.getBookIsbn() + "\n");
						searchResultTextArea.append("Book Author: " + foundBook.getBookAuthor() + "\n");
						searchResultTextArea.append("Book Title: " + foundBook.getBookTitle() + "\n");
						searchResultTextArea.append("Book Rating: " + foundBook.getBookRating() + "\n");
						searchResultTextArea.append("Book Publishing Year: " + foundBook.getBookPubYear() + "\n");
						
					}
					else {
						searchResultTextArea.append("Book with ID: " + searchBookId + " cannot be found.");
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
					

				} catch (FileNotFoundException e1) {

					e1.printStackTrace();

				} catch (IOException e1) {

					e1.printStackTrace();

				}
		}
		
		if (e.getSource() == topTenBooksBtn) {
			sortResultTextArea.setText("");
			try {
				bookSortResultWindow();
				sortResultTextArea.append("Top 10 Books Sorted by Rating: ");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == searchHomeBtn) {
			try {
				screenReset();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == searchBackBtn) {
			try {
				bookSearchWindow();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == sortHomeBtn) {
			try {
				screenReset();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	
	
	public static void screenReset() throws IOException {
		startPanel.setVisible(true);
		bookSearchPanel.setVisible(false);
		searchResultPanel.setVisible(false);
		sortResultPanel.setVisible(false);
	}
	
	public static void bookSearchWindow() throws IOException {
		startPanel.setVisible(false);
		searchResultPanel.setVisible(false);
		bookSearchPanel.setVisible(true);
	}
	
	public void bookSearchResultWindow() throws IOException {
		startPanel.setVisible(false);
		bookSearchPanel.setVisible(false);
		searchResultPanel.setVisible(true);
	}
	
	public void bookSortResultWindow() throws IOException {
		startPanel.setVisible(false);
		bookSearchPanel.setVisible(false);
		searchResultPanel.setVisible(false);
		sortResultPanel.setVisible(true);
	}
}


public class Main {

	public static void main(String[] args) {
		new AppFrame();
	}

}





 