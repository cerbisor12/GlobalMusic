import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating a new band Object.
 *
 */
public class Band {


	public Band(){}
	/**
	 * Class' constructor with query for inserting data in the database included.
	 * @param name
	 * @param genre
	 * @param link
	 * @param image
	 * @param ID
	 */
	public Band(String name, String genre, String link,String image, int ID) {
		String link1 = "";

		String query = "INSERT INTO tbl_band VALUES(DEFAULT,'"+ name + "','" + genre + "','" + image + "', '" + link1 +"', '" + ID + "');";
		try {
            Connect.updateData(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
        }
	}
	
	/**
	 * Static method to get all the bands.
	 * @return Returns an ArrayList of strings, which contains all the bands existing in the database.
	 */
	public ArrayList<String> getAllBands(){
		String query = "SELECT Name FROM tbl_band;";
		ArrayList<String> bandsList = new ArrayList<>();
		try {
            ResultSet results = Connect.selectStm(query);
            while (results.next()) {
                String agent = results.getString("Name");
                bandsList.add(agent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
        }
        return bandsList;
	}

	/**
	 * static method for retrieving the bands of a certain event/
	 * @param eventID event's ID for identifying the desired event
	 * @return ArrayList of strings which contains all the bands registered with the event.
	 */
	public ArrayList<String> getEventBands(int eventID) {
		String query = "SELECT B.Name FROM tbl_band B, tbl_event_band EB WHERE EB.EventID = " +eventID +
		" AND B.BandID = EB.BandID;";
		ArrayList<String> bandsList = new ArrayList<>();
		try {
			ResultSet results = Connect.selectStm(query);
			while (results.next()) {
				String agent = results.getString("Name");
				bandsList.add(agent);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException k) {
			System.out.println(k.getMessage());
		}
		return bandsList;
	}

	public int getPerfID(String bandName) {
		String query = "SELECT * FROM tbl_band WHERE Name = '" + bandName + "';";
    	int ID = 0;
    	try {
    		ResultSet rs = Connect.selectStm(query);
        	rs.next();
        	ID = rs.getInt("BandID");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException f) {
            System.out.println(f.getMessage());
        }
    	return ID;
	}

	public List<String[]> getBandDetails(int eventID){
		String query = "SELECT B.Name, B.Image, B.Genre, B.Link FROM tbl_band B, tbl_event_band EB WHERE EventID= "+eventID+
				" AND B.BandID = EB.BandID";
		List<String[]> bandDetails = new ArrayList<>();
		try{
			ResultSet rs = Connect.selectStm(query);

			while(rs.next()){
				String name = rs.getString("Name");
				String image = rs.getString("Image");
				String genre = rs.getString("Genre");
				String link = rs.getString("Link");
				bandDetails.add(new String[]{name,image,genre,link});
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException k) {
			System.out.println(k.getMessage());}

		return bandDetails;
	}
}


