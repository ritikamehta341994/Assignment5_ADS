import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/***
 * This class contains method to read data from csv and convert it into array list containing the trip details
 * @author ritzm
 *
 */
public class FileManager {
	
	/**
	 * This method reads the data from the crime-incidents.csv file and adds it into the crimeList
	 * @return linked list of the input csv
	 */
	
	public ArrayList<TripDetails> getTripData(){
		ArrayList<TripDetails> tripDetails = new ArrayList<>();
		try {
			  //Create object of file
		      File myObj = new File("tripdetails.csv");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        //Process all records except for the headings
		        if(!data.contains("PULocationID")) {
		        	String[] crimeData = data.split(",");
		        	//Create TripDetails object from the csv data
		        	TripDetails tripDetail = new TripDetails(crimeData[0],
		        			crimeData[1], 
		        			(int) Double.parseDouble(crimeData[2])); 
		        	tripDetails.add(tripDetail);
		        }
		        
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		return tripDetails;
	}
}
