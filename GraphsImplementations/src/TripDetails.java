/***
 * 
 * @author ritzm
 * This class is the blue print to store the data which is loaded from green_tripdata.csv
 */
public class TripDetails {
	
	// Pick up location Id indicates where the trip starts from
	private String pickUpLocationId;
	// Drop off location Id indicates where the trip ends
	private String dropOffLocationId;
	// Fare indicates the fare amount for the trip
	private int fare;
	
	//Constructor for the Trip details
	public TripDetails(String pickUpLocationId, String dropOffLocationId, int fare) {
		super();
		this.pickUpLocationId = pickUpLocationId;
		this.dropOffLocationId = dropOffLocationId;
		this.fare = fare;
	}
	
	// Fetch the pick up location Id
	public String getPickUpLocationId() {
		return pickUpLocationId;
	}
	
	// Set the pick up location Id
	public void setPickUpLocationId(String pickUpLocationId) {
		this.pickUpLocationId = pickUpLocationId;
	}
	
	// Fetch the drop off location Id
	public String getDropOffLocationId() {
		return dropOffLocationId;
	}
	
	// Set the drop off location Id
	public void setDropOffLocationId(String dropOffLocationId) {
		this.dropOffLocationId = dropOffLocationId;
	}
	
	// Fetch the fare	
	public int getFare() {
		return fare;
	}
	
	// Set the fare
	public void setFare(int fare) {
		this.fare = fare;
	}
	
	

}
