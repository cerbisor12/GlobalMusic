import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is developed for the Concert Organizer in order
 * to handle the venues of the concerts.
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
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
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
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
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
	 * This method sets the address of the venue.
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * This method returns the address of the venue.
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * This method sets the capacity of the venue.
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * This method returns the capacity of the venue.
	 * @return
	 */
	public int getCapacity() {
		return capacity;
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
        } catch (ClassNotFoundException f) {
            System.out.println(f.getMessage());
        }
        return ID;
	}
	

}
