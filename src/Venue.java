import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class for creating new Venue objects and records in the database
 * Provides methods for retrieving data from the database
 */

public class Venue {
	
	private String name = "";
	private String address = "";
	private int capacity;
	
	/**
	 * This the venue constructor with specified name, address and capacity.
	 * @param name the name of the venue
	 * @param address the address of the venue
	 * @param capacity the number of people that can attend the concert
	 */
	
	public Venue(String name, String address, int capacity) {
		this.name = name;
		this.capacity = capacity;
		this.address = address;
		

		String query = "INSERT INTO tbl_venue(VenueID,Name,Address,Capacity)" + 
				"VALUES(DEFAULT,'" + this.name + "','" + this.address + "','" + this.capacity + "');";
		try {
            Connect.updateData(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Empty constructor.
	 */
	public Venue() {
	}	
	
	/**
	 * This method returns the list of venues from the database.
	 * @return the venueList
	 */
	static ArrayList<String> getVenueList() {
		String query = "SELECT Name FROM `tbl_venue`;";
		ArrayList<String> venuesList = new ArrayList<>();
		try {
            ResultSet results = Connect.selectStm(query);
            while (results.next()) {
                String venue = results.getString("Name");
                venuesList.add(venue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venuesList;
	}
	
	
	/**
	 * This method sets the name of the venue.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method returns the name of the venue. 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method returns the venue ID from the database according to the venue name.
	 * @param name the name of the venue
	 * @return returns the venue ID
	 */
	static int getVenueId(String name) {
		String query = "SELECT VenueID FROM tbl_venue WHERE Name ='" + name + "';";
		int ID = 0;
		try {
            ResultSet rs = Connect.selectStm(query);
            rs.next();
            ID = Integer.parseInt(rs.getString("VenueID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ID;
	}
	

}
