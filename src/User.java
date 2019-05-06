import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class User {
    private int userID, cardCVV;
    private long cardNo;
    private String title, fName, lName, address1, address2, town, postcode, password;
    static String username; 
    private String email, phoneNo, orgName, webAddress, orgEmail, paymentMethod, userType;

    public User() {
    }

    public User(long cardNo, int cardCVV, String title, String fName, String lName, String address1,
                String address2, String town, String postcode, String username, String password, String email,
                String phoneNo, String orgName, String webAddress, String orgEmail, String paymentMethod) {
        this.cardNo = cardNo;
        this.cardCVV = cardCVV;
        this.title = title;
        this.fName = fName;
        this.lName = lName;
        this.address1 = address1;
        this.address2 = address2;
        if (address2 == null) {
        	this.address2 = "";
        }
        this.town = town;
        this.postcode = postcode;
        User.username = username;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        if (phoneNo == null) {
        	this.phoneNo = "";
        }
        this.orgName = orgName;
        this.webAddress = webAddress;
        this.orgEmail = orgEmail;
        this.paymentMethod = paymentMethod;
        this.userType = "customer";
        if (RegisterView.checkType == false) {
        	this.orgName = "";
        	this.orgEmail = "";
        	this.paymentMethod = "On Booking";
        	this.webAddress = "";
        }
        if (RegisterView.checkType == true)
        	this.userType = "organization";
    }

    public int getID() {
        return userID;
    }

    public void setID(int userID) {
        this.userID = userID;
    }
    
    public void setUsername(String username) {
    	User.username = username;
    }

//    public String getUsername() {
//        return username;
//    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void insertCustomerData() {
        String query = "";
            query = "INSERT INTO tbl_user(UserID,Title,Fname,LNAme,Address1,Address2,Town,PostCode,Username,Pass,Email" +
                    ",PhoneNo,CardNo,CVVCode,Type,OrganizationName,WebAddress,OrgEmail,PaymentMethod) VALUES(DEFAULT,'"
                    + this.title + "','" + this.fName + "','" + this.lName + "','" + this.address1 + "','" + this.address2 +
                    "','" + this.town + "','" + this.postcode + "','" + User.username + "','" + this.password + "','" + this.email + "','" +
                    this.phoneNo + "'," + this.cardNo + "," + this.cardCVV + ",'" + this.userType + "','" + this.orgName + "','" +
                    this.webAddress + "','" + this.orgEmail + "','" + this.paymentMethod + "');";
        

        try {
            Connect.updateData(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
        }
    }

    public boolean checkFieldInDB(String field, String attribute) {
        String query = "SELECT * FROM tbl_user WHERE " + field + "='" + attribute + "';";
        try {
            ResultSet results = Connect.selectStm(query);
            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
        }
        return false;
    }

    public boolean loginCheck() {
        String query = "SELECT * FROM tbl_user WHERE Username='" + User.username + "' AND Pass='" + this.password + "';";
        try {
            ResultSet results = Connect.selectStm(query);
            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
        }
        return false;
    }

    public static void deleteUser(int userID) {
        String query = "DELETE FROM tbl_user WHERE ID='" + userID + "';";
        try {
            Connect.updateData(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
        }
    }
    
    static void updateDetails(String username,String title, String firstName, String lastName, String addressOne, String addressTwo, String town, String postcode,
    		String email, String phoneNumber, long cardNumber, int cvv, String orgName, String orgEmail, String webAddress, String paymentMethod) {
    	
    	String query = "UPDATE tbl_user SET Title='"+ title +"', FName = '"+ firstName +"', LName = '"+lastName+"', Address1 = '"+addressOne+"', Address2 = '"+addressTwo+""
    			+ "', Town = '"+town+"', PostCode = '"+postcode+"', Email = '"+email+"', PhoneNo = '"+phoneNumber+"', CardNo = '"+cardNumber+"', CVVCode = '"+cvv+""
    					+ "', OrganizationName = '"+orgName+"', OrgEmail = '"+orgEmail+"', WebAddress = '"+webAddress+"', PaymentMethod = '"+paymentMethod+"'"
    							+ "WHERE Username ='" + username + "';";
    	try {
			Connect.updateData(query);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static String[] userList() {
        String query = "SELECT Username FROM tbl_user;";
        ArrayList<String> users = new ArrayList<>();
        try {
            ResultSet results = Connect.selectStm(query);
            while (results.next()) {
                String user = results.getString("Username");
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException k) {
            System.out.println(k.getMessage());
        }
        Object[] objDetails = users.toArray();
    	String[] finalUsers = Arrays.copyOf(objDetails,objDetails.length,String[].class);
        return finalUsers;
    }

    public static String getData(String username, String field) {
        String query = "SELECT "+ field+" FROM tbl_user WHERE Username='" + username + "';";
        String data = "";
        try {
            ResultSet rs = Connect.selectStm(query);
            rs.next();
            data = rs.getString(field);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException f) {
            System.out.println(f.getMessage());
        }
        return data;
    }

    
    static void updatePass(String newPass, String username) {
    	String query = "UPDATE tbl_user SET Pass='" + newPass + "' WHERE Username='" + username + "'"; 

    	try {
			Connect.updateData(query);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static ArrayList<String> detailsList(String username) {
    	String query = "SELECT * FROM `tbl_user` WHERE Username = '" + username + "';";
    	ArrayList<String> details = new ArrayList<String>();
    	try {
			ResultSet rs = Connect.selectStm(query);
			
	    	while (rs.next()) {
	    		details.add(rs.getString("Title"));
	    		details.add(rs.getString("FName"));
	    		details.add(rs.getString("LName"));
	    		details.add(rs.getString("Address1"));
	    		details.add(rs.getString("Address2"));
	    		details.add(rs.getString("Town"));
	    		details.add(rs.getString("PostCode"));
	    		details.add(rs.getString("Email"));
	    		details.add(rs.getString("PhoneNo"));
	    		long cardNo = rs.getLong("CardNo");
	    		details.add(String.valueOf(cardNo));
	    		int cvv = rs.getInt("CVVCode");
	    		details.add(String.valueOf(cvv));
	    		details.add(rs.getString("OrganizationName"));
	    		details.add(rs.getString("OrgEmail"));
	    		details.add(rs.getString("PaymentMethod"));
	    		details.add(rs.getString("WebAddress"));
	    		
	    	}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException f){
			f.printStackTrace();
		}
    	
    	return details;
    }
    
    static int getUserId(String username) {
    	String query = "SELECT * FROM tbl_user WHERE Username = '" + username + "';";
    	int ID = 0;
    	try {
    		ResultSet rs = Connect.selectStm(query);
        	rs.next();
        	ID = rs.getInt("UserID");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException f) {
            System.out.println(f.getMessage());
        }
    	return ID;
    }

    public static void main(String[] args) {
    	int ID = User.getUserId("cerbisor");
        System.out.println(ID);
    }
}
