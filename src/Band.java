import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Band {
	
	private String name = "";
	private String genre = "";
	private String link = "";
	private String image = "";
	private int agent;
	
	public Band(String name, String genre, String link,String image, int ID) {
		if (this.link == null)
				this.link = "";
		this.name = name;
		this.genre = genre;
		this.image = image;
		this.agent = ID;
		
		String query = "INSERT INTO tbl_band VALUES(DEFAULT,'"+ this.name + "','" + this.genre + "','" + this.image + "', '" + this.link +"', '" + this.agent + "');";
		try {
            Connect.updateData(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
        }
	}
	
	static ArrayList<String> getBands(){
		String query = "SELECT Name FROM tbl_band;";
		ArrayList<String> bandsList = new ArrayList<String>();
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
	
	static int getPerfID(String bandName) {
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
}


