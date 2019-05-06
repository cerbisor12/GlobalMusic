import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Event {
	private int ID;
	private String name = "";
	private String date ;
	private int organizerId;
	private int venueId;
	private int duration;
	private float price;
	private String image;
	
	public Event(String name, float price, int organizerId, int venueId, String date, String image, int duration) {

		this.name = name;
		this.price = price;
		this.organizerId = organizerId;
		this.venueId = venueId;
		this.date = date;
		this.image = image;
		this.duration = duration;
		
		String query = "INSERT INTO tbl_event VALUES(DEFAULT,'" + this.name + "'," + this.price + "," + this.organizerId + "," + this.venueId + ",'" + this.date + "','"
				+ this.image+"'," + this.duration + ");";
		try {
            Connect.updateData(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
        }
		
	}	
	
	
	
	static ArrayList<String> getEventsList() {
		String query = "SELECT Name FROM `tbl_event`;";
		ArrayList<String> eventsList = new ArrayList<String>();
		try {
            ResultSet results = Connect.selectStm(query);
            while (results.next()) {
                String event = results.getString("Name");
                eventsList.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
        }
        return eventsList;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
	public int getDuration() {
		return duration;
	}
	
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	public int getPrice(int price) {
		return price;
	}
	
	static int getEventId(String name) {
		String query = "SELECT EventID FROM tbl_event WHERE Name ='" + name + "';";
		int ID = 0;
		try {
            ResultSet rs = Connect.selectStm(query);
            rs.next();
            ID = rs.getInt("EventID");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException f) {
            System.out.println(f.getMessage());
        }
        return ID;
	}

}
