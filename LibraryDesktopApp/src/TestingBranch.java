
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Arrays;





class Book {
	/*
	String Authors;
	int OriginalPublicationYear;
	int Isbn;
	int BookID;
	String Title;
	Double AverageRating;
	 */
	LinkedList<Integer> bookId = new LinkedList<Integer>();
	LinkedList<Integer> originalPublicationYear = new LinkedList<Integer>();
	LinkedList<String> Isbn = new LinkedList<String>();
	LinkedList<String> Title = new LinkedList<String>();
	LinkedList<Double> AverageRating = new LinkedList<Double>();
	
		
}

//SelectionSort_________________________________________________________________________________________________________
class modifySelectionSort {

	/**
	 *@author downey
	 *
		/**
		 * Swaps the elements at indexes i and j.
		 */
		public static void swapElements(LinkedList<Integer> originalPublicationYear, int i, int j) {
			int temp = originalPublicationYear.get(i);
			originalPublicationYear.set(i,originalPublicationYear.get(j));
			originalPublicationYear.set(j,temp);
			
		}
		/**
		 * Finds the index of the lowest value
		 * between indices low and high (inclusive).
		 */
		public static int indexLowest(LinkedList<Integer> originalPublicationYear, int start) {
			int lowIndex = start;
			for (int i = start; i < originalPublicationYear.size(); i++) {
				if (originalPublicationYear.get(i) < originalPublicationYear.get(lowIndex)) {
					lowIndex = i;
				}
			}
			return lowIndex;
		}

		/**
		 * Sorts the cards (in place) using selection sort.
		 * @return 
		 */
		public LinkedList<Integer> selectionSort(LinkedList<Integer> originalPublicationYear) {
			for (int i = 0; i < originalPublicationYear.size(); i++) {
				int j = indexLowest(originalPublicationYear, i);
				swapElements(originalPublicationYear, i, j);
				//Author Swap
				//Isbn
				//BookID
				//Title
				//AverageRating
				
			}
			return originalPublicationYear;
		}

	
}



public class TestingBranch {

	public static void main(String[] args) {
	
//Reading File________________________________________________________________________________________________________________
	String Path = "src/books.csv";
	String line = "";
	
		BufferedReader Reader;
		
		try {
			Reader = new BufferedReader(new FileReader(Path));
			
			LinkedList<String[]> books = new LinkedList<String[]>();
			
			while((line = Reader.readLine()) != null) {
				
				books.add(line.split(","));
			
			}
			
			Reader.close();
			
			//Display all fields
				/*
			for (String[] array : books) {
					
		            for (String element : array) {
		                System.out.print(element + "   ");
		            }
		            System.out.println(); // Print a newline after each array
		        }
			*/
//Storing fields into respective arraylist________________________________________________________________________________
				//Creating book Object
			Book BookObjMethod = new Book();
			
		
			boolean skipTheFirstIteration = true;
			
			//Iterating through data collected
			for (String[] array : books) {
				
				//This if statement allows skips the first iteration of the loop 
				if (skipTheFirstIteration) {
					
					skipTheFirstIteration = false;
					continue;
				}
				
				
				BookObjMethod.bookId.add(Integer.parseInt(array[0]));
				BookObjMethod.Title.add(array[10]);
				BookObjMethod.Isbn.add(array[5]);
				BookObjMethod.originalPublicationYear.add(Integer.parseInt(array[8]));
				BookObjMethod.AverageRating.add(Double.parseDouble(array[12]));
				
				//Printing fields for testing
				//String firstElement = array[8];
				//System.out.println(firstElement);
				}
			

//________________________________________________________________________________________________________________
			modifySelectionSort publicYear = new modifySelectionSort();		
			
			BookObjMethod.originalPublicationYear = publicYear.selectionSort(BookObjMethod.originalPublicationYear);
				
	
					
			System.out.println(BookObjMethod.originalPublicationYear);
					
			
					
					
//Printing output_____________________________________________________________________________________________					
					//change array index to test the retrieval of each field
					
					
				

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
			
	}

}
