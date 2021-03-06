import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Event class for creating event objects.
 * Writes new events to the database and provides methods for retrieving/updating data
 *
 */
public class Event {
	private String name = "";

    /**
	 * Class' constructor
	 * @param name, event name
	 * @param price, event price 
	 * @param organizerId, organizer's id
	 * @param venueId, venue's id
	 * @param date, date of event
	 * @param image, event image
	 * @param duration, event length by days
	 * Query for inserting data straight to the database included.
	 */
	public Event(String name, float price, int organizerId, int venueId, String date, String image, int duration) {

		this.name = name;

        String query = "INSERT INTO tbl_event VALUES(DEFAULT,'" + this.name + "'," + price + "," + organizerId + "," + venueId + ",'" + date + "','"
				+ image +"'," + duration + ");";
		try {
            Connect.updateData(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	/**
	 * General constructor for retrieving/updating database data without creating a new record
	 */
	public Event(){ }
	/**
	 * Method for returning events based on organizer's id by querying the database.
	 * @param ID organizer's id
	 * @return eventsList arrayList 
	 */
	static ArrayList<String> getFutureEventsOrganizer(int ID){
		String query = "SELECT Name FROM tbl_event WHERE OrganizerID = " + ID + " AND DateOfEvent > NOW();";
		ArrayList<String> futureEventsList = new ArrayList<>();
		try {
            ResultSet results = Connect.selectStm(query);
            while (results.next()) {
                String event = results.getString("Name");
                futureEventsList.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();}

        return futureEventsList;
	}

	/**
	 * Method for returning an event's details based on the event's name by querying the database.
	 * @param eventID ID of event
	 * @return arrayList eventDetailsList 
	 */
	public ArrayList<String> eventDetailsList(int eventID) {
		String query = "SELECT E.*, V.Name VName FROM tbl_event E, tbl_venue V WHERE E.EventID = " + eventID +
				" AND V.VenueID = E.VenueID;";
		ArrayList<String> details = new ArrayList<>();
		try {
			ResultSet rs = Connect.selectStm(query);

			while (rs.next()) {
				details.add(String.valueOf(rs.getInt("EventID")));
				details.add(rs.getString("Name"));
				details.add(String.valueOf(rs.getFloat("Price")));
				details.add(rs.getString("VName"));
				details.add(rs.getString("DateOFEvent"));
				details.add(rs.getString("Image"));
				details.add(String.valueOf(rs.getInt("Duration")));

			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}

        return details;
	}


    /**
	 * Method for returning an event's id based on its name, by querying database.
	 * @param name of Event
	 * @return integer ID, id of event.
	 */
	public int getEventId(String name) {
		String query = "SELECT EventID FROM tbl_event WHERE Name ='" + name + "';";
		int ID = 0;
		try {
            ResultSet rs = Connect.selectStm(query);
            rs.last();
            ID = rs.getInt("EventID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ID;
	}

	/**
	 * Method for updating the event details by querying database.
	 * @param eventID id of event
	 * @param eventName name of event
	 * @param price price per day
	 * @param venueID venue's id
	 * @param date date of event
	 * @param image image string
	 * @param duration in days(integer)
	 */
    public void updateEventDetails(int eventID, String eventName, float price, int venueID, String date, String image, int duration){

        String query = "UPDATE tbl_event SET Name='"+ eventName +"', Price = "+ price +", VenueID = "+venueID+
                ", DateOfEvent = '"+date+"', Image = '"+image+"', Duration = "+duration+" " +
                "WHERE EventID = " + eventID + ";";
        try {
            Connect.updateData(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for canceling an event based on it's ID by querying the database.
	 * First deletes all band-event entries for this event and then the event record
     * @param eventID 
     */
    public void deleteEvent(int eventID){
        String query = "DELETE FROM tbl_event_band WHERE EventID  = " + eventID + ";";
	    String query1 = "DELETE FROM tbl_event WHERE EventID = "+ eventID +";";

        try {
            Connect.updateData(query);
            Connect.updateData(query1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
