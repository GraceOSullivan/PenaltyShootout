package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Game extends JFrame {

    public static void main(String args[]){
        Game game = new Game();
        game.setVisible(true);

        //----------------Window Listener----------------------
        game.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                JOptionPane.showMessageDialog(null, "Closing Window", "Exit", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } // end windowClosing
        }// end anonymous inner class definition
        ); //end of addWindowListener method argument                       // XDrive/JohnW/OOP2019/SampleCode2/ClosingWindow2.java

        Stadium anfield, oldTraf, santiago;
        Team liverpool, manu, real;
        Player  milner, salah, firmino, mane, origi,
                martial, rashford, pogba, james,  lingard,
                ramos, benzema, hazard, kroos, bale;
        Keeper alisson, degea, courtois;

        //----------------Create Stadiums------------------------
        anfield = new Stadium("Anfield", 54074);
        oldTraf = new Stadium("Old Trafford", 76000);
        santiago = new Stadium("Santiago Bernabéu Stadium", 81044);

        //-----------------------Create Players and Keepers--------------------------------
        milner = new Player("James Milner", "Midfield", 34, 29, new GregorianCalendar(1986, Calendar.JANUARY, 4));
        salah = new Player("Mohamed Salah", "Forward", 16, 13, new GregorianCalendar(1992, Calendar.JUNE, 15));
        firmino = new Player("Roerto Firmino", "Forward", 9, 5, new GregorianCalendar(1991, Calendar.OCTOBER, 2));
        mane = new Player("Sadio Mané", "Forward", 6, 2, new GregorianCalendar(1992, Calendar.APRIL, 10));
        origi = new Player("Divock Origi", "Forward", 8, 4, new GregorianCalendar(1995, Calendar.APRIL, 18));
        alisson = new Keeper("Alisson Becker",  9, 3, new GregorianCalendar(1992, Calendar.OCTOBER, 2));

        martial = new Player("Anthony Martial", "Forward", 12, 9, new GregorianCalendar(1995, Calendar.DECEMBER, 5));
        rashford = new Player("Marcus Rashford", "Forward", 10, 8, new GregorianCalendar(1997, Calendar.OCTOBER, 31));
        pogba = new Player("Paul Pogba", "Midfield", 18, 13, new GregorianCalendar(1993, Calendar.MARCH, 15));
        james = new Player("Daniel James", "Midfield", 1, 1, new GregorianCalendar(1997, Calendar.NOVEMBER, 10));
        lingard = new Player("Jesse Lingard", "Midfield", 2, 1, new GregorianCalendar(1992, Calendar.DECEMBER, 15));
        degea = new Keeper("David DeGea",  10, 4, new GregorianCalendar(1990, Calendar.NOVEMBER, 7));

        ramos = new Player("Sergio Ramos", "Defender", 25, 22, new GregorianCalendar(1986, Calendar.MARCH, 30));
        benzema = new Player("Karim Benzema", "Forward", 16, 14, new GregorianCalendar(1987, Calendar.DECEMBER, 19));
        hazard = new Player("Eden Hazard", "Forward", 55, 48, new GregorianCalendar(1991, Calendar.JANUARY, 7));
        kroos = new Player("Toni Kroos", "Midfield", 4, 4, new GregorianCalendar(1990, Calendar.JANUARY, 4));
        bale = new Player("Gareth Bale", "Forward", 9, 7, new GregorianCalendar(1989, Calendar.JULY, 16));
        courtois = new Keeper("Thibaut Courtois",  7, 3, new GregorianCalendar(1992, Calendar.MAY, 11));

        //-------------------Create Teams------------------------
        Player[] liverpoolPlayers;
        liverpoolPlayers = new Player[]{milner, salah, firmino, mane, origi};
        liverpool = new Team("Liverpool", liverpoolPlayers, alisson);

        Player[] manuPlayers;
        manuPlayers = new Player[]{martial, rashford, pogba, james, lingard};
        manu = new Team("Man United", manuPlayers, degea);

        Player[] realPlayers;
        realPlayers = new Player[]{ramos, benzema, hazard, kroos, bale};
        real = new Team("Real Madrid", realPlayers, courtois);

        //-----------------------Selecting Options------------------------
        String[] stadiumOptions = {"Anfield", "Old Trafford", "Santiago Bernabéu Stadium"};
        String[] teamOptions = {"Liverpool", "Man United", "Real Madrid"};
        Stadium chosenStadium = null;                         //to store chosen options
        Team chosenTeam1 = null, chosenTeam2 = null;

      //   -----------------------------Selecting Stadium ------------------------------------
        JComboBox stadium = new JComboBox(stadiumOptions);
        stadium.setSelectedIndex(-1);
        JOptionPane.showMessageDialog(null, stadium, "Please choose a Stadium", JOptionPane.QUESTION_MESSAGE);

        if(stadium.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Shootout taking place in Anfield!");
            chosenStadium = anfield;
        }

        else if(stadium.getSelectedIndex() == 1) {
            JOptionPane.showMessageDialog(null, "Shootout taking place in Old Trafford!");
            chosenStadium = oldTraf;
        }

        else if(stadium.getSelectedIndex() == 2) {
            JOptionPane.showMessageDialog(null, "Shootout taking place in Santiago Bernabéu Stadium!");
            chosenStadium = santiago;
        }

        else if(stadium.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(null, "You must choose a Stadium!", "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, stadium, "Please choose a Stadium", JOptionPane.QUESTION_MESSAGE);
        }

        //   -----------------------------Selecting Team 1 ------------------------------------
        JComboBox team1 = new JComboBox(teamOptions);
        team1.setSelectedIndex(-1);
        JOptionPane.showMessageDialog(null, team1, "Please choose Team 1", JOptionPane.QUESTION_MESSAGE);

        if(team1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Team 1 is Liverpool!");
            chosenTeam1 = liverpool;
        }

        else if(team1.getSelectedIndex() == 1) {
            JOptionPane.showMessageDialog(null, "Team 1 is Man United!");
            chosenTeam1 = manu;
        }

        else if(team1.getSelectedIndex() == 2) {
            JOptionPane.showMessageDialog(null, "Team 1 is Real Madrid!");
            chosenTeam1 = real;
        }

        else if(team1.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(null, "You must choose a Team!", "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, team1, "Please choose Team 1", JOptionPane.QUESTION_MESSAGE);
        }

        //   -----------------------------Selecting Team 2 ------------------------------------
        JComboBox team2 = new JComboBox(teamOptions);
        team2.setSelectedIndex(-1);
        JOptionPane.showMessageDialog(null, team2, "Please choose Team 2", JOptionPane.QUESTION_MESSAGE);

        if(team2.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Team 2 is Liverpool!");
            chosenTeam2 = liverpool;
        }

        else if(team2.getSelectedIndex() == 1) {
            JOptionPane.showMessageDialog(null, "Team 2 is Man United!");
            chosenTeam2 = manu;
        }

        else if(team2.getSelectedIndex() == 2) {
            JOptionPane.showMessageDialog(null, "Team 2 is Real Madrid!");
            chosenTeam2 = real;
        }

        else if(team2.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(null, "You must choose a Team!", "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, team2, "Please choose Team 1", JOptionPane.QUESTION_MESSAGE);
        }

        JTextArea intro = new JTextArea("Welcome everyone to " + chosenStadium + " for the Penalty Shootout of the decade!!" +
                "We have a full attendance of " + chosenStadium.getCapacity() + " here at today's shootout is between " + chosenTeam1 + " and " +
                chosenTeam2 + ". Both teams have had a great season to date but it all comes down to this shootout." +
                "Will it be " + chosenTeam1 +"'s win or " + chosenTeam2 + "'s win? Stay tuned to find out! This should " +
                "be a cracking shootout.");

        intro.setVisible(true);

        //Choose where to shoot
        //algorithm to score/miss
        //score changes


    }

    public Game(){
        //Customize the window
        setTitle("Penalty Shootout");
        setSize(1000, 600);
        setLocation(100,50);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Customize the Content Pane
        Container pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        //Create and add Labels
        pane.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel nameLabel = new JLabel("Penalty Shootout");
        nameLabel.setFont(new Font("Courier New", Font.BOLD, 42));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(nameLabel);

        pane.add(Box.createRigidArea(new Dimension(0, 20)));    //space between components   //http://www.fredosaurus.com

        ImageIcon image = new ImageIcon("Resources/gameGoal.jpg");   //https://stackoverflow.com
        JLabel imgLabel = new JLabel(image);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(imgLabel);

        pane.add(Box.createRigidArea(new Dimension(0, 20)));

        //Team1
        JLabel team1label = new JLabel("Team 1");
        team1label.setFont(new Font("Courier New", Font.BOLD, 24));
        team1label.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(team1label);

        pane.add(Box.createRigidArea(new Dimension(0, 40)));

        //Team2
        JLabel team2label = new JLabel("Team 2");
        team1label.setFont(new Font("Courier New", Font.BOLD, 24));
        team1label.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(team2label);

    }

}
