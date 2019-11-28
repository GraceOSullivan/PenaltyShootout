package classes;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Driver extends StartPage {
    public static void main(String[] args) {
        StartPage start = new StartPage();
        start.setVisible(true);

        //----------------Window Listener----------------------
        start.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                JOptionPane.showMessageDialog(null, "Closing Window", "Exit", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } // end windowClosing
        }// end anonymous inner class definition
        ); //end of addWindowListener method argument                       // XDrive/JohnW/OOP2019/SampleCode2/ClosingWindow2.java
    }//end main()
}