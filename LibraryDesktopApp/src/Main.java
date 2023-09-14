
import java.io.BufferedReader;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;

 

 

 

class Book {

	/*

	String Authors;

	int OriginalPublicationYear;

	int Isbn;

	int BookID;

	String Title;

	Double AverageRating;

	 */

	ArrayList<String> bookId = new ArrayList<String>();

	ArrayList<String> originalPublicationYear = new ArrayList<String>();

	ArrayList<String> Isbn = new ArrayList<String>();

	ArrayList<String> Title = new ArrayList<String>();

	ArrayList<String> AverageRating = new ArrayList<String>();

	

}


 

public class Main {

 

	public static void main(String[] args) {
		

		

		

//Reading File________________________________________________________________________________________________________________

		String Path = "src/books.csv";

		String line = "";

		

			BufferedReader Reader;

			

			try {

				Reader = new BufferedReader(new FileReader(Path));

				

				ArrayList<String[]> books = new ArrayList<String[]>();

				

				while((line = Reader.readLine()) != null) {

					

					books.add(line.split(","));

				

				}

				

				//Display all fields

					/*

				for (String[] array : books) {

						

			            for (String element : array) {

			                System.out.print(element + "   ");

			            }

			            System.out.println(); // Print a newline after each array

			        }

				*/

//________________________________________________________________________________________________________________

					//Creating book Object

				Book BookObj = new Book();

				

			

				boolean skipTheFirstIteration = true;

				

				//Iterating through data collected

				for (String[] array : books) {

					

					//This if statement allows skips the first iteration of the loop

					if (skipTheFirstIteration) {

						

						skipTheFirstIteration = false;

						continue;

					}
					

					

					
/*
					BookObj.bookId.add(String.valueOf(array[0]));

					//BookObj.Title.add(array[10]);

					//Unable to parse some input, as some contains letters and other

					BookObj.Isbn.add(String.valueOf(array[5]));

					BookObj.originalPublicationYear.add(String.valueOf(array[8]));

					BookObj.AverageRating.add(String.valueOf(array[13]));
*/
					

					int bookIdNum = Integer.parseInt(array[0]);
					int bookIsbnNum = Integer.parseInt(array[5]);
					double originalPublishingYear = Double.parseDouble(array[8]);
					double averageRating = Double.parseDouble(array[13]);
					
					String bookId = Integer.toString(bookIdNum);
					String bookIsbn = Integer.toString(bookIsbnNum);
					String origPubYear = Double.toString(originalPublishingYear);
					String avgRating = Double.toString(averageRating);
					
					
					
					System.out.println(makeBookObj(bookId, bookIsbn, origPubYear, avgRating));
					
					
					//System.out.println("First element of the array: " + bookId);
					//System.out.println("Book Isbn is: " + bookIsbn);
					//System.out.println("The original publicaton year is: " + origPubYear);
					//System.out.println("The average rating of this book is: " + avgRating + " stars.");
					//System.out.println("");
					
					

					

				}
				
				
				

				//System.out.println(BookObj.bookId);

				

			} catch (FileNotFoundException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}

			

			

	}

	private static ArrayList<String> makeBookObj(String bookId, String bookIsbn, String origPubYear, String avgRating) {
		ArrayList<String> Book = new ArrayList<String>();
		
		Book.add(bookId);
		Book.add(bookIsbn);
		Book.add(origPubYear);
		Book.add(avgRating);
		
		return Book;
		
	}
}





 