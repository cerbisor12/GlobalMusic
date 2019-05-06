import javax.swing.*;
import java.io.File;
import java.sql.*;

public class Connect
{
    private static Connection c = null;
    public static Connection connect() throws SQLException, ClassNotFoundException
    {
        if (c==null){
            //Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/musicfestivalbookings?serverTimezone=UTC","root","" );
        }
        return c;
    }

    public static ResultSet selectStm(String query) throws SQLException,ClassNotFoundException{
        Connection c = connect();
        ResultSet rs = null;
        Statement s = c.createStatement();
        rs = s.executeQuery(query);
        return rs;
    }

    public static void updateData(String query) throws SQLException,ClassNotFoundException
    {
        Connection c = connect();
        Statement s = c.createStatement();
        s.executeUpdate(query);
        s.close();
    }

    //Add image path to database, read it back and add to label!!!!!!!
    public static void main(String[] args)
    {
        String path = null;
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        String filepath = file.getAbsolutePath().toString().replaceAll("\\\\","\\\\\\\\");
        try {
            Connect.updateData("INSERT INTO tbl_event(EventRefNo,Image) VALUES('test7','" + filepath + "');");
            ResultSet rs = Connect.selectStm("SELECT Image FROM tbl_event WHERE EventRefNo = 'test7';");
            rs.next();
            path = rs.getString("Image");
        }
        catch(Exception e){e.getStackTrace();}
        System.out.println(path);
        System.out.println(filepath);
        JFrame frame = new JFrame();
        JLabel lbl = new JLabel("Image");
        lbl.setIcon(new ImageIcon(path));
        frame.add(lbl);
        frame.setVisible(true);
    }

}

