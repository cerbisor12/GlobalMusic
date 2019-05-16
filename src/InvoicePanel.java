import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Child Class of JPanel, takes customer details and bookings as data and creates an invoice
 * Provides methods for saving a screenshot of the panel as an image
 */

public class InvoicePanel extends JPanel {

    //font styles
    final Font LABEL_FONT = new Font("Calibri", Font.BOLD, 14);
    final Font VALUE_FONT = new Font("Calibri", Font.PLAIN, 14);

    private JLabel invNoValue, invDateValue, compName, compAddress, compAddress2, representiveName, totalAmount;
    private int invoiceID, customerID;
    private String email, imageName;

    /**
     * Class' constructor, creates the panel and sets data
     * @param bookings multidimensional array, including all monthly bookings of one customer
     * @param customerDetails array including all customer's details
     * @param counter used for controlling the invoice number
     */
    public InvoicePanel(List<List<String>> bookings, List<String> customerDetails,int counter) {
        initialize(); //create invoice template
        double amount = addBookingContainer(bookings); //add bookings to invoice and return the total amount
        this.invoiceID = getLatestInvID()+counter+1; //calculate the invoice's number, based on the DB
        this.customerID = setDetails(amount, customerDetails); //set the generic customer data and set the customer ID
        this.email = customerDetails.get(7); //set the customer's email

    }

    /**
     *
     * @return String - customer's email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     *
     * @return String - customer's full name (title, first name, last name)
     */
    public String getFullName(){
        return representiveName.getText();
    }

    /**
     *
     * @return String - the saved Invoice's filename
     */
    public String getInvoiceFileName(){
        return this.imageName;
    }

    /**
     * Create the invoice template with all fields and images
     */
    private void initialize() {
        setLayout(null);
        setSize(800, 700);
        setPreferredSize(new Dimension(800, 700));

        JLabel invoiceTitle = new JLabel("INVOICE");
        invoiceTitle.setBounds(10, 10, 200, 40);
        invoiceTitle.setFont(new Font("Calibri", Font.BOLD, 38));
        add(invoiceTitle);

        JLabel ourCompanyName = new JLabel("Global Music Booking Agency Ltd.");
        ourCompanyName.setBounds(10, 50, 260, 20);
        ourCompanyName.setFont(new Font("Calibri", Font.BOLD, 18));
        add(ourCompanyName);

        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(Main.IMAGE_DIR + "Logo.jpg"));
        lblLogo.setBounds(580, 10, 200, 100);
        add(lblLogo);

        JLabel invoiceNo = new JLabel("Invoice #");
        invoiceNo.setFont(LABEL_FONT);
        invoiceNo.setBounds(580, 115, 100, 20);
        add(invoiceNo);

        invNoValue = new JLabel("invoiceNo");
        invNoValue.setFont(VALUE_FONT);
        invNoValue.setBounds(680, 115, 100, 20);
        add(invNoValue);

        JLabel invoiceDate = new JLabel("Invoice Date");
        invoiceDate.setFont(LABEL_FONT);
        invoiceDate.setBounds(580, 135, 100, 20);
        add(invoiceDate);

        invDateValue = new JLabel("invoiceDate");
        invDateValue.setFont(VALUE_FONT);
        invDateValue.setBounds(680, 135, 100, 20);
        add(invDateValue);

        JLabel to = new JLabel("To:");
        to.setFont(LABEL_FONT);
        to.setBounds(10, 85, 20, 20);
        add(to);

        compName = new JLabel("CompanyName");
        compName.setFont(VALUE_FONT);
        compName.setBounds(14, 105, 100, 20);
        add(compName);

        compAddress = new JLabel("Address1");
        compAddress.setFont(VALUE_FONT);
        compAddress.setBounds(14, 125, 200, 20);
        add(compAddress);

        compAddress2 = new JLabel("Town , Postcode");
        compAddress2.setFont(VALUE_FONT);
        compAddress2.setBounds(14, 145, 200, 20);
        add(compAddress2);

        JLabel attentionOf = new JLabel("Attention Of:");
        attentionOf.setFont(LABEL_FONT);
        attentionOf.setBounds(10, 165, 200, 20);
        add(attentionOf);

        representiveName = new JLabel("Title, Lname, FName");
        representiveName.setFont(VALUE_FONT);
        representiveName.setBounds(14, 185, 200, 20);
        add(representiveName);

        JLabel tickets = new JLabel("Tickets #");
        tickets.setFont(new Font("Calibri", Font.BOLD, 18));
        tickets.setBounds(10, 210, 100, 20);
        add(tickets);

        JLabel bookingNo = new JLabel("Booking #");
        bookingNo.setFont(new Font("Calibri", Font.BOLD, 18));
        bookingNo.setBounds(110, 210, 200, 20);
        add(bookingNo);

        JLabel eventName = new JLabel("Event");
        eventName.setFont(new Font("Calibri", Font.BOLD, 18));
        eventName.setBounds(310, 210, 150, 20);
        add(eventName);

        JLabel amount = new JLabel("Amount");
        amount.setFont(new Font("Calibri", Font.BOLD, 18));
        amount.setBounds(460, 210, 100, 20);
        add(amount);

        JLabel total = new JLabel("Total: ");
        total.setFont(new Font("Calibri",Font.BOLD,24));
        total.setBounds(310,480,100,30);
        add(total);

        totalAmount = new JLabel("");
        totalAmount.setFont(new Font("Calibri",Font.PLAIN,24));
        totalAmount.setBounds(460,480,100,30);
        add(totalAmount);

        JLabel lblThankyou = new JLabel("");
        lblThankyou.setBounds(550, 420, 100, 100);
        ImageIcon img = new ImageIcon((Main.IMAGE_DIR+"thankyou.png"));
        Image image = img.getImage().getScaledInstance(lblThankyou.getWidth(), lblThankyou.getHeight(),Image.SCALE_SMOOTH);
        lblThankyou.setIcon(new ImageIcon(image));
        add(lblThankyou);

        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setBounds(650,420,10,100);
        add(separator);

        JLabel paymentDetails = new JLabel("Payment Details");
        paymentDetails.setFont(LABEL_FONT);
        paymentDetails.setBounds(660,420,100,20);
        add(paymentDetails);

        JLabel ourCompany = new JLabel(ourCompanyName.getText());
        ourCompany.setFont(VALUE_FONT);
        ourCompany.setBounds(660,440,200,20);
        add(ourCompany);

        JLabel accountNo = new JLabel("Account No: 12345678");
        accountNo.setFont(VALUE_FONT);
        accountNo.setBounds(660,460,200,20);
        add(accountNo);

        JLabel sortCode = new JLabel("Sort Code: 00-00-00");
        sortCode.setFont(VALUE_FONT);
        sortCode.setBounds(660,480,200,20);
        add(sortCode);


    }


    /**
     * Saves the panel as an image to the projects directory,
     * adds a new record to the database
     * and updates the bookings included to the invoice to paid
     */
    public void saveInvoice(InvoicePanel this) {
        BufferedImage img = getScreenShot();
        imageName = "Inv_"+invoiceID+".jpg";
        String query = "UPDATE tbl_booking SET Paid = 1 WHERE CustomerID = "+customerID+";";
        String query2 = "INSERT INTO tbl_invoice VALUES("+invoiceID+", "+customerID+", '"+LocalDate.now()+"');";
        try {
            // write the image as a PNG
            ImageIO.write(img, "jpg", new File(Main.INVOICES_DIR +imageName));
            //update database
            Connect.updateData(query);
            Connect.updateData(query2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return BufferedImage of JPanel
     */
    private BufferedImage getScreenShot(InvoicePanel this) {

        BufferedImage image = new BufferedImage(getWidth(), getHeight(),
                BufferedImage.TYPE_INT_RGB
        );
        // call the Component's paint method, using
        // the Graphics object of the image.
        this.paint(image.getGraphics()); // alternately use .printAll(..)
        return image;
    }

    /**
     * For every booking, adds a new container with its details to the template
     * On the process, calculates and returns the total amount
     * @param bookings ,monthly, for one customer
     * @return double-total amount
     */
    private double addBookingContainer(List<List<String>> bookings) {
        double totalAmount = 0;
        int x = 10;
        int y = 230;
        for (List<String> booking : bookings) {
            Container c = new Container();
            c.setBounds(x, y, 560, 20);
            c.setLayout(null);

            JLabel ticket = new JLabel(booking.get(0));
            ticket.setFont(VALUE_FONT);
            ticket.setBounds(0, 0, 100, 20);
            c.add(ticket);

            JLabel bookingNo = new JLabel(booking.get(1));
            bookingNo.setFont(VALUE_FONT);
            bookingNo.setBounds(100, 0, 200, 20);
            c.add(bookingNo);

            JLabel event = new JLabel(booking.get(2));
            event.setFont(VALUE_FONT);
            event.setBounds(300, 0, 150, 20);
            c.add(event);

            JLabel amount = new JLabel(booking.get(3));
            amount.setFont(VALUE_FONT);
            amount.setBounds(450, 0, 100, 20);
            c.add(amount);

            totalAmount += Double.parseDouble(booking.get(3));

            y += 20;

            this.add(c);
        }
        return totalAmount;
    }


    /**
     * Using CustomerDetails, resets the labels on the template with correct data and returns the customer's ID
     * @param amount the total amount of the invoice
     * @param customerDetails all customer's info
     * @return int Customer's ID
     */
    private int setDetails(double amount, List<String> customerDetails) {
        invNoValue.setText(String.valueOf(invoiceID));//Make table in the DB for invoices, get last invNo +1
        invDateValue.setText(LocalDate.now().toString());
        compName.setText(customerDetails.get(11));
        compAddress.setText(customerDetails.get(3));
        compAddress2.setText(customerDetails.get(5) + ", " + customerDetails.get(6));
        String fullName = customerDetails.get(0) + " " + customerDetails.get(1) + " " + customerDetails.get(2);
        representiveName.setText(fullName);
        totalAmount.setText(String.valueOf(amount));

        int customerID = Integer.parseInt(customerDetails.get(15));

        return customerID;
    }

    /**
     * Queries the database for the biggest Inv No
     * @return int biggest Invoice Number in database
     */
    private int getLatestInvID(){
        String query = "SELECT InvNo FROM tbl_invoice ORDER BY InvNo DESC LIMIT 1";
        int invID = 0;
        try{
            ResultSet rs = Connect.selectStm(query);
            while(rs.next()){
            invID = rs.getInt(1);}
        }catch(SQLException e){e.printStackTrace();}
        return invID;
    }


}