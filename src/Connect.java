import java.sql.*;

/**
 *
 * Class for establishing connection to the DB through JDBC
 *
 */
public class Connect
{
    private static Connection c = null;

    /**
     *
     * Checks if a connection already exists, and creates one if not
     * @return JDBC Connection c
     * @throws SQLException SQLException
     */
    private static Connection connect() throws SQLException {
        if (c==null){
            c = DriverManager.getConnection("jdbc:mysql://localhost/musicfestivalbookings?serverTimezone=UTC","root","" );
        }
        return c;
    }

    /**
     * General method for executing SQL Select Statements and return their result set
     * @param query any mySQL SELECT statement
     * @return ResultSet
     * @throws SQLException SQLException
     */
    public static ResultSet selectStm(String query) throws SQLException{
        Connection c = connect();
        ResultSet rs;
        Statement s = c.createStatement();
        rs = s.executeQuery(query);
        return rs;
    }

    /**
     * General method for SQL statements that update/alter the data (no Results)
     * @param query any mySQL statement(UPDATE, DELETE,... not SELECT)
     * @throws SQLException SQLException
     *
     */
    public static void updateData(String query) throws SQLException{
        Connection c = connect();
        Statement s = c.createStatement();
        s.executeUpdate(query);
        s.close();
    }


}

