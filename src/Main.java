import java.awt.*;
import java.util.regex.Pattern;

public class Main {

    final static String ARTIST_IMAGE_DIR = "Images/Artist_Images/";
    final static String EVENT_IMAGE_DIR = "Images/Event_Images/";
    final static String CARD_ICON_DIR = "Images/CardIcons/";

    public static String GetCreditCardType(String CreditCardNumber)
    {
        Pattern regVisa = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
        Pattern regMaster = Pattern.compile("^5[1-5][0-9]{14}$");
        Pattern regExpress = Pattern.compile("^3[47][0-9]{13}$");
        Pattern regDiners = Pattern.compile("^3(?:0[0-5]|[68][0-9])[0-9]{11}$");
        Pattern regDiscover = Pattern.compile("^6(?:011|5[0-9]{2})[0-9]{12}$");
        Pattern regJCB= Pattern.compile("^(?:2131|1800|35\\d{3})\\d{11}$");


        if(regVisa.matcher(CreditCardNumber).matches()){
            return "VISA";}
        else if (regMaster.matcher(CreditCardNumber).matches()){
            return "MASTER";}
        else  if (regExpress.matcher(CreditCardNumber).matches()){
            return "AEXPRESS";}
        else if (regDiners.matcher(CreditCardNumber).matches()){
            return "DINERS";}
        else if (regDiscover.matcher(CreditCardNumber).matches()){
            return "DISCOVERS";}
        else   if (regJCB.matcher(CreditCardNumber).matches()){
            return "JCB";}
        else{
            return "invalid";}
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginView window = new LoginView();
                    //window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(GetCreditCardType("5355220262760022"));
    }
}
