import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class CreditCardIconsPanel extends JPanel{

    private JLabel visaIcon, masterIcon, amexIcon, dinersIcon, discoverIcon, jcbIcon;

    public CreditCardIconsPanel(int posX, int posY){
        this.setBounds(posX,posY,180,20);
        this.setOpaque(false);

        this.setLayout(null);

        visaIcon = new JLabel();
        visaIcon.setBounds(0,0,28, 20);
        visaIcon.setSize(new Dimension(28,20));
        ImageIcon img = new ImageIcon(RegisterView.class.getResource(Main.CARD_ICON_DIR+"VisaIcon.jpg"));
        Image image = img.getImage().getScaledInstance(visaIcon.getWidth(),visaIcon.getHeight(),Image.SCALE_SMOOTH);
        visaIcon.setIcon(new ImageIcon(image));
        this.add(visaIcon);

        masterIcon = new JLabel();
        masterIcon.setBounds(30,0,28, 20);
        masterIcon.setSize(new Dimension(28, 20));
        img = new ImageIcon(RegisterView.class.getResource(Main.CARD_ICON_DIR+"MasterCardIcon.jpg"));
        image = img.getImage().getScaledInstance(masterIcon.getWidth(),masterIcon.getHeight(),Image.SCALE_SMOOTH);
        masterIcon.setIcon(new ImageIcon(image));
        this.add(masterIcon);

        jcbIcon = new JLabel();
        jcbIcon.setBounds(60,0,28, 20);
        jcbIcon.setSize(new Dimension(28, 20));
        img = new ImageIcon(RegisterView.class.getResource(Main.CARD_ICON_DIR+"JCBIcon.jpg"));
        image = img.getImage().getScaledInstance(jcbIcon.getWidth(),jcbIcon.getHeight(),Image.SCALE_SMOOTH);
        jcbIcon.setIcon(new ImageIcon(image));
        this.add(jcbIcon);

        discoverIcon = new JLabel();
        discoverIcon.setBounds(90,0,28, 20);
        discoverIcon.setSize(new Dimension(28, 20));
        img = new ImageIcon(RegisterView.class.getResource(Main.CARD_ICON_DIR+"DiscoversIcon.jpg"));
        image = img.getImage().getScaledInstance(discoverIcon.getWidth(),discoverIcon.getHeight(),Image.SCALE_SMOOTH);
        discoverIcon.setIcon(new ImageIcon(image));
        this.add(discoverIcon);

        dinersIcon = new JLabel();
        dinersIcon.setBounds(120,0,28, 20);
        dinersIcon.setSize(new Dimension(28, 20));
        img = new ImageIcon(RegisterView.class.getResource(Main.CARD_ICON_DIR+"DinersIcon.jpg"));
        image = img.getImage().getScaledInstance(dinersIcon.getWidth(),dinersIcon.getHeight(),Image.SCALE_SMOOTH);
        dinersIcon.setIcon(new ImageIcon(image));
        this.add(dinersIcon);

        amexIcon = new JLabel();
        amexIcon.setBounds(150,0,28, 20);
        amexIcon.setSize(new Dimension(28, 20));
        img = new ImageIcon(RegisterView.class.getResource(Main.CARD_ICON_DIR+"AmexIcon.jpg"));
        image = img.getImage().getScaledInstance(amexIcon.getWidth(),amexIcon.getHeight(),Image.SCALE_SMOOTH);
        amexIcon.setIcon(new ImageIcon(image));
        this.add(amexIcon);
    }


    public boolean repaint(String CardNo){
        Pattern regVisa = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
        Pattern regMaster = Pattern.compile("^5[1-5][0-9]{14}$");
        Pattern regExpress = Pattern.compile("^3[47]\\d{13,14}$");
        Pattern regDiners = Pattern.compile("^3(?:0[0-5]|[68][0-9])[0-9]{11}$");
        Pattern regDiscover = Pattern.compile("^6(?:011|5[0-9]{2})[0-9]{12}$");
        Pattern regJCB= Pattern.compile("^(?:2131|1800|35\\d{3})\\d{11}$");

        Map<Pattern, JLabel> typeOfCard = new HashMap<Pattern, JLabel>();
        typeOfCard.put(regVisa, visaIcon);
        typeOfCard.put(regMaster,masterIcon);
        typeOfCard.put(regExpress,amexIcon);
        typeOfCard.put(regDiners,dinersIcon);
        typeOfCard.put(regDiscover,discoverIcon);
        typeOfCard.put(regJCB,discoverIcon);

        boolean validNo = false;
        for (Pattern key : typeOfCard.keySet()){
            if(key.matcher(CardNo).matches()){
                this.removeAll();
                this.repaint();
                this.add(typeOfCard.get(key));
                validNo = true;
            }
        }
        return validNo;
        }
    }
