import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Agent {
	

	private String name;
	private String email;
	private String phoneNo;
	
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
