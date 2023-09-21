
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

}

// Searches for a book by it's ID
class BookBinarySearch {
	public static Timer timer = new Timer();
	
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
	
	public Timer getTimer() {
		return timer;
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




 