import java.sql.*;

/**
 *
 * Class for establishing connection to the DB through JDBC
 * @author aran0ia
 *
 */
public class Connect
{
    private static Connection c = null;

    /**
     *
     * Checks if a connection already exists, and creates one if not
     * @return c
     * @throws SQLException
     */
    private static Connection connect() throws SQLException {
        if (c==null){
            //Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/musicfestivalbookings?serverTimezone=UTC","root","" );
        }
        return c;
    }

    /**
     * General method for executing SQL Select Statements and return their result set
     * @param query
     * @return ResultSet
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ResultSet selectStm(String query) throws SQLException,ClassNotFoundException{
        Connection c = connect();
        ResultSet rs;
        Statement s = c.createStatement();
        rs = s.executeQuery(query);
        return rs;
    }

    /**
     * General method for SQL statements that update/alter the data (no Results)
     * @param query
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void updateData(String query) throws SQLException,ClassNotFoundException
    {
        Connection c = connect();
        Statement s = c.createStatement();
        s.executeUpdate(query);
        s.close();
    }


}

