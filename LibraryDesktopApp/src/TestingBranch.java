
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	ArrayList<Integer> bookId = new ArrayList<Integer>();
	ArrayList<Integer> originalPublicationYear = new ArrayList<Integer>();
	ArrayList<Integer> Isbn = new ArrayList<Integer>();
	ArrayList<String> Title = new ArrayList<String>();
	ArrayList<Double> AverageRating = new ArrayList<Double>();
	


public void readCSV() {
	
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
						//Unable to parse some input, as some contains letters and other 
						//problem starts after index 7
						//BookObj.Isbn.add(Integer.parseInt(array[5]));
						//BookObj.originalPublicationYear.add(Integer.parseInt(array[8]));
						//BookObj.AverageRating.add(Double.parseDouble(array[12]));
						
						//Printing fields for testing
						String firstElement = array[3];
						System.out.println("First element of the array: " + firstElement);
						}
					
									
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
		}
		
}


public class TestingBranch {

	public static void main(String[] args) {
	
		
		Book BookObjMain = new Book();
		
		BookObjMain.readCSV();
//________________________________________________________________________________________________________________
					
					
					
					
					
					
					
					
			
					
					
//Printing output_____________________________________________________________________________________________					
					//change array index to test the retrieval of each field
					
					
				
				//System.out.println(BookObj.bookId);

			
			
	}

}
