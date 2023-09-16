
import java.io.BufferedReader;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;

class Book {
	
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

}

public class Main {

	public static void main(String[] args) {
		

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

				}
				
				// Testing looking up the title of a book in the Books arraylist
				Book bookIndex = Books.get(88);
				String bookTitleofFirstBook = bookIndex.getBookTitle();
				System.out.println(bookIndex);
				System.out.println(bookTitleofFirstBook);
				
				
			} catch (FileNotFoundException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}

	}

}





 