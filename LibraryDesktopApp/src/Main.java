
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


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
    
    
    public void loadAndSearchBooks(String filePath, String userInput) {
    	libraryAppMainFrame appMainFrame = new libraryAppMainFrame();
    	DefaultTableModel table = appMainFrame.model;
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
			
			// Searching for book based on user inputed book ID
			Collections.sort(Books);
			String searchBookId = String.valueOf(userInput);
			int searchResult = BookBinarySearch.binarySearchById(Books, searchBookId);
			Book foundBook = Books.get(searchResult);
			
			if (searchResult != -1) {
				
				table.setRowCount(1);
				
				Object[] rowData = {
					foundBook.getBookId(),
					foundBook.getBookIsbn(),
					foundBook.getBookPubYear(),
					foundBook.getBookAuthor(),
					foundBook.getBookRating(),
				};
				table.addRow(rowData);
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
			

		} catch (FileNotFoundException e1) {

			e1.printStackTrace();

		} catch (IOException e1) {

			e1.printStackTrace();

		}
    	
    }

}

// Searches for a book by it's ID
class BookBinarySearch {
	
	public static int binarySearchById(ArrayList<Book> books, String bookId) {
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
    	libraryAppMainFrame appMainFrame = new libraryAppMainFrame();
    	DefaultTableModel table = appMainFrame.model;
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
            table.setRowCount(1);
			
			Object[] rowData = {
				book.getBookTitle(),
				book.getBookRating(),
			};
			table.addRow(rowData);
            
            //System.out.println(book.getBookId() + " Book title: " + book.getBookTitle() + ", Rating: " + book.getBookRating());
        }
        
    }
    
    public void bookYearSort(ArrayList<Book> books, int topCount, boolean ascending) {
    	libraryAppMainFrame appMainFrame = new libraryAppMainFrame();
    	DefaultTableModel table = appMainFrame.model;
        // Sort the books by rating
    	if (ascending) {
    		books.sort((book1, book2) -> Double.compare(Double.parseDouble(book2.getBookPubYear()), Double.parseDouble(book1.getBookPubYear())));
    	}
    	else {
    		books.sort((book1, book2) -> Double.compare(Double.parseDouble(book1.getBookPubYear()), Double.parseDouble(book2.getBookPubYear())));
    		
    	}
        

        // Makes sure it doesn't exceed the size of the arraylist
        int numBooks = Math.min(topCount, books.size());
        
        // Searches to find top books by rating
        for (int i = 0; i < numBooks; i++) {
            Book book = books.get(i);
            table.setRowCount(1);
			
			Object[] rowData = {
				book.getBookPubYear(),
			};
			table.addRow(rowData);
            
            //System.out.println(book.getBookId() + " Book title: " + book.getBookTitle() + ", Rating: " + book.getBookRating());
        }
        
    }
    
    public void bookAuthorSort(ArrayList<Book> books, int topCount, boolean ascending) {
    	libraryAppMainFrame appMainFrame = new libraryAppMainFrame();
    	DefaultTableModel table = appMainFrame.model;
        // Sort the books by rating
    	if (ascending) {
    		books.sort(Comparator.comparing(Book::getBookAuthor));
    	}
    	else {
    		books.sort(Comparator.comparing(Book::getBookAuthor).reversed());    		
    	}
        

        // Makes sure it doesn't exceed the size of the arraylist
        int numBooks = Math.min(topCount, books.size());
        
        // Searches to find top books by rating
        for (int i = 0; i < numBooks; i++) {
            Book book = books.get(i);
            table.setRowCount(1);
			
			Object[] rowData = {
				book.getBookAuthor(),
			};
			table.addRow(rowData);
            
            //System.out.println(book.getBookId() + " Book title: " + book.getBookTitle() + ", Rating: " + book.getBookRating());
        }
        
    }

}


public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					libraryAppMainFrame frame = new libraryAppMainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}




 