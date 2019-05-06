import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Venue {
	
	private String name = "";
	private String address = "";
	private int capacity;
	
	
	
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
	
	
	public Venue() {
	}	
	
	static ArrayList<String> getVenueList() {
		String query = "SELECT Name FROM `tbl_venue`;";
		ArrayList<String> venuesList = new ArrayList<String>();
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
	
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public String getAddress() {
		return address;
	}
	
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	public int getCapacity() {
		return capacity;
	}
	
	
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
