
import java.io.BufferedReader;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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


public class Main {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		BookRatingSort binarySearch = new BookRatingSort();
		BookBinarySearch bookBinarySearch = new BookBinarySearch();
		

//Reading File________________________________________________________________________________________________________________

		String Path = "src/books(1).csv";

		String line = "";

		

			BufferedReader Reader;

			

			try {

				Reader = new BufferedReader(new FileReader(Path));

				

				ArrayList<String[]> books = new ArrayList<String[]>();

				

				while((line = Reader.readLine()) != null) {

					

					books.add(line.split(","));

				

				}

				

//________________________________________________________________________________________________________________

					//Creating book Object

				//Book BookObj = new Book();
				

				

			

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
		            
		            System.out.println(book);

				}
				
				// Searching for book based on user inputed book ID
				Collections.sort(Books);
				System.out.println("Enter a book ID to search: ");
				int userInput = scnr.nextInt();
				String searchBookId = String.valueOf(userInput);
				int searchResult = bookBinarySearch.binarySearchById(Books, searchBookId);
				
				if (searchResult != -1) {
					Book foundBook = Books.get(searchResult);
					System.out.println("Book found: " + foundBook);
					System.out.println("Book ID: " + foundBook.getBookId());
					System.out.println("Book ISBN: " + foundBook.getBookIsbn());
					System.out.println("Book Author: " + foundBook.getBookAuthor());
					System.out.println("Book Title: " + foundBook.getBookTitle());
					System.out.println("Book Rating: " + foundBook.getBookRating());
					System.out.println("Book Publishing Year: " + foundBook.getBookPubYear());
				}
				else {
					System.out.println("Book with ID: " + searchBookId + " cannot be found.");
				}
				
				/*
				// Testing sorting books in ascending and descending order based on rating
				System.out.println("Top 10 Books Sorted by Rating: ");
				binarySearch.bookRatingSort(Books, 10, true );
				System.out.println("");
				System.out.println("Bottom 10 Books Sorted by Rating: ");
				binarySearch.bookRatingSort(Books, 10, false);
				*/

				
				
				
			} catch (FileNotFoundException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}

	}

}





 