import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class for creating Agent object.
 * @author x64
 *
 */
public class Agent {
	

	private String name;

    /**
	 * Class' constructor with query for inserting data into the database included.
	 * @param name name of agent
	 * @param phoneNo agent's phone number
	 * @param email agent's email
	 */
	public Agent(String name, String phoneNo, String email) {
		this.name = name;

        String phoneNo1 = phoneNo;
		if (phoneNo == null) {
			phoneNo1 = "";}

        String email1 = email;
		if (email == null){
			email1 = "";}

		String query = "INSERT INTO tbl_agent(AgentID,Name,PhoneNo,Email)" + 
				"VALUES(DEFAULT,'" + this.name + "','" + phoneNo1 + "','" + email1 + "');";
		try {
            Connect.updateData(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
        }
	}
	
	public Agent() {
	}	
	
	/**
	 * Static method for retrieving all agents.
	 * @return Returns an arrayList filled with strings representing the names of all agents existing in the database.
	 */
	public ArrayList<String> getAgentsList() {
		String query = "SELECT Name FROM `tbl_agent`;";
		ArrayList<String> agentsList = new ArrayList<>();
		try {
            ResultSet results = Connect.selectStm(query);
            while (results.next()) {
                String agent = results.getString("Name");
                agentsList.add(agent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
        }
        return agentsList;
	}


	/**
	 * Static method for getting an agent's Id based on its name.
	 * @param name Name of Agent
	 * @return Returns an integer matching agent's ID.
	 */
	public int getAgentId(String name) {
		String query = "SELECT AgentID FROM tbl_agent WHERE Name ='" + name + "';";
		int ID = 0;
		try {
            ResultSet rs = Connect.selectStm(query);
            rs.next();
            ID = Integer.parseInt(rs.getString("AgentID"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException f) {
            System.out.println(f.getMessage());
        }
        return ID;
	}
}
