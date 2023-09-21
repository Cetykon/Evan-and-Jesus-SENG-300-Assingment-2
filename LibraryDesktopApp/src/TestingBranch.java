
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;

class Book {
	/*
	 * String Authors; int OriginalPublicationYear; int Isbn; int BookID; String
	 * Title; Double AverageRating;
	 */
	LinkedList<Integer> bookId = new LinkedList<Integer>();
	LinkedList<Integer> originalPublicationYear = new LinkedList<Integer>();
	LinkedList<String> Isbn = new LinkedList<String>();
	LinkedList<String> Title = new LinkedList<String>();
	LinkedList<Double> AverageRating = new LinkedList<Double>();
	LinkedList<String> Author = new LinkedList<String>();

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

			while ((line = Reader.readLine()) != null) {

				books.add(line.split(","));

			}

			Reader.close();

			// Display all fields
			/*
			 * for (String[] array : books) {
			 * 
			 * for (String element : array) { System.out.print(element + "   "); }
			 * System.out.println(); // Print a newline after each array }
			 */
//Storing fields into respective arraylist________________________________________________________________________________
			// Creating book Object
			Book BookObjMethod = new Book();

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

				// Printing fields for testing
				// String firstElement = array[8];
				// System.out.println(firstElement);
			}

//_Printing output for testing_________________________________________________________________________________________

			// sorting by publication year Ascending
			selectionSortInt(BookObjMethod.originalPublicationYear, BookObjMethod.bookId, BookObjMethod.Title,
					BookObjMethod.Isbn, BookObjMethod.Author, BookObjMethod.AverageRating);

			System.out.println(BookObjMethod.originalPublicationYear);
			System.out.println(BookObjMethod.bookId);
			System.out.println(BookObjMethod.Title);
			System.out.println(BookObjMethod.Isbn);
			System.out.println(BookObjMethod.Author);
			System.out.println(BookObjMethod.AverageRating);

			// Sorting by Author Ascending
			selectionSortString(BookObjMethod.Author, BookObjMethod.bookId, BookObjMethod.originalPublicationYear,
					BookObjMethod.Isbn, BookObjMethod.Title, BookObjMethod.AverageRating);

			System.out.println(BookObjMethod.Author);
			System.out.println(BookObjMethod.originalPublicationYear);
			System.out.println(BookObjMethod.bookId);
			System.out.println(BookObjMethod.Title);
			System.out.println(BookObjMethod.Isbn);
			System.out.println(BookObjMethod.AverageRating);

			// Searching for book ID
			int bookPositionIndex = returnPositionInt(BookObjMethod.bookId, 55);
			if (bookPositionIndex != -1) {
				System.out.println(bookPositionIndex);
			} else {
				System.out.println("Couldn't Find the book! (ID NOT FOUND)");
			}

//________________________________________________________________________________________________________					

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

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

		// Author Swap
		// Isbn
		// BookID
		// Title
		// AverageRating

	}

	/**
	 * Finds the index of the lowest value between indices low and high (inclusive).
	 */
	public static int indexLowestInt(LinkedList<Integer> LLIntegerSort, int start) {
		int lowIndex = start;
		for (int i = start; i < LLIntegerSort.size(); i++) {
			if (LLIntegerSort.get(i) < LLIntegerSort.get(lowIndex)) {
				lowIndex = i;
			}
		}
		return lowIndex;
	}

	public static void selectionSortInt(LinkedList<Integer> LLIntegerSort, LinkedList<Integer> JustSwappingInt,
			LinkedList<String> str1, LinkedList<String> str2, LinkedList<String> str3, LinkedList<Double> Dbl1) {
		for (int i = 0; i < LLIntegerSort.size(); i++) {
			int j = indexLowestInt(LLIntegerSort, i);
			swapElementsInt(LLIntegerSort, i, j, JustSwappingInt, str1, str2, str3, Dbl1);

		}

	}

//Selection Sort for strings

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

		// Author Swap
		// Isbn
		// BookID
		// Title
		// AverageRating

	}

	public static void swapElementsDouble(LinkedList<Double> dbl1, int i, int j, LinkedList<Integer> justSwappingInt,
			LinkedList<String> str1, LinkedList<String> str2, LinkedList<String> str3) {
		double temp = dbl1.get(i);
		dbl1.set(i, dbl1.get(j));
		dbl1.set(j, temp);

		// Swapping the rest of the input data
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
	}

	public static int indexLowestDouble(LinkedList<Double> dbl1, int start) {
		int lowIndex = start;
		for (int i = start; i < dbl1.size(); i++) {
			if (dbl1.get(i) < dbl1.get(lowIndex)) {
				lowIndex = i;
			}
		}
		return lowIndex;
	}

	public static void selectionSortDouble(LinkedList<Double> dbl1, LinkedList<Integer> justSwappingInt,
			LinkedList<String> str1, LinkedList<String> str2, LinkedList<String> str3) {
		for (int i = 0; i < dbl1.size(); i++) {
			int j = indexLowestDouble(dbl1, i);
			swapElementsDouble(dbl1, i, j, justSwappingInt, str1, str2, str3);

			// Author Swap
			// ISBN
			// BookID
			// Title
			// AverageRating
		}
	}

	/**
	 * Finds the index of the lowest value (alphabetically) between indices low and
	 * high (inclusive) in a string array.
	 */
	public static int indexLowestString(LinkedList<String> str1, int start) {
		int lowIndex = start;
		for (int i = start; i < str1.size(); i++) {
			if (str1.get(i).compareTo(str1.get(lowIndex)) < 0) {
				lowIndex = i;
			}
		}
		return lowIndex;
	}

	/**
	 * Sorts the cards (in place) using selection sort.
	 * 
	 * @return
	 */
	public static void selectionSortString(LinkedList<String> str1, LinkedList<Integer> LLIntegerSort,
			LinkedList<Integer> JustSwappingInt, LinkedList<String> str2, LinkedList<String> str3,
			LinkedList<Double> Dbl1) {
		for (int i = 0; i < LLIntegerSort.size(); i++) {
			int j = indexLowestString(str1, i);
			swapElementsString(str1, LLIntegerSort, i, j, JustSwappingInt, str2, str3, Dbl1);
			// Author Swap
			// Isbn
			// BookID
			// Title
			// AverageRating

		}

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

//Takes the passed linked list of integers and an int value, then it searches if the int value is in the LinkedList
	public static int returnPositionInt(LinkedList<Integer> IntLinkList, int intValue) {

		// Using the Iterator Class
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

}
