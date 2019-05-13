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
	private String email;
	private String phoneNo;
	/**
	 * Class' constructor with query for inserting data into the database included.
	 * @param name name of agent
	 * @param phoneNo agent's phone number
	 * @param email agent's email
	 */
	public Agent(String name, String phoneNo, String email) {
		this.name = name;
		
		this.phoneNo = phoneNo;
		if (phoneNo == null) {
			this.phoneNo = "";}
			
		this.email = email;
		if (email == null){
			this.email = "";}

		String query = "INSERT INTO tbl_agent(AgentID,Name,PhoneNo,Email)" + 
				"VALUES(DEFAULT,'" + this.name + "','" + this.phoneNo + "','" + this.email + "');";
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
	static ArrayList<String> getAgentsList() {
		String query = "SELECT Name FROM `tbl_agent`;";
		ArrayList<String> agentsList = new ArrayList<String>();
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
	 * Getters and setters for all the fields
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Static method for getting an agent's Id based on its name.
	 * @param name Name of Agent
	 * @return Returns an integer matching agent's ID.
	 */
	static int getAgentId(String name) {
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
