package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.Arrays;
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

        //-----------------------Create Teams-------------------------------
        Team liverpool = buildLiverpoolTeam();
        Team manu = buildManuTeam();
        Team real = buildRealTeam();

        //----------------Create Stadiums------------------------
        Stadium anfield = new Stadium("Anfield", 54074);
        Stadium oldTraf = new Stadium("Old Trafford", 76000);
        Stadium santiago = new Stadium("Santiago Bernabéu Stadium", 81044);

      //   ----------------Selecting Stadium --------------------------
        Stadium[] stadiumOptions = {anfield, oldTraf, santiago};                                //Stadium array of stadium objects
        String[] stadiumNames = {anfield.getName(), oldTraf.getName(), santiago.getName()};     //String array of stadium names for JComboBox

        JComboBox stadium = new JComboBox(stadiumNames);
        stadium.setSelectedIndex(-1);                           //No placeholder before stadium chosen
        do {
            JOptionPane.showMessageDialog(null, stadium, "Please choose a Stadium", JOptionPane.QUESTION_MESSAGE);
            if (stadium.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "You must choose a Stadium!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (stadium.getSelectedIndex() < 0);
        Stadium chosenStadium = stadiumOptions[stadium.getSelectedIndex()];        //takes chosen stadium name and gets corresponding stadium object

        JOptionPane.showMessageDialog(null, "Shootout taking place in " + chosenStadium.getName());

        //   -----------------------------Selecting Team 1 ------------------------------------
        Team[] teamOptions = {liverpool, manu, real};                                           //Team array of team objects
        String[] teamNames = {liverpool.getName(), manu.getName(), real.getName()};             //String array of team names for JComboBox

        JComboBox team1 = new JComboBox(teamNames);
        team1.setSelectedIndex(-1);
        do {
            JOptionPane.showMessageDialog(null, team1, "Please choose Team 1", JOptionPane.QUESTION_MESSAGE);
            if (team1.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "You must choose a Team!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (team1.getSelectedIndex() < 0);
        Team chosenTeam1 = teamOptions[team1.getSelectedIndex()];        //takes chosen team name and gets corresponding team object

        //   -----------------------------Selecting Team 2 ------------------------------------
        JComboBox team2 = new JComboBox(teamNames);
        team2.setSelectedIndex(-1);
        do {
            JOptionPane.showMessageDialog(null, team1, "Please choose Team 2", JOptionPane.QUESTION_MESSAGE);
            if (team2.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "You must choose a Team!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (team2.getSelectedIndex() < 0);
        Team chosenTeam2 = teamOptions[team2.getSelectedIndex()];        //takes chosen team name and gets corresponding team object

        JTextArea intro = new JTextArea("Welcome everyone to " + chosenStadium + " for the Penalty Shootout of the decade!!" +
                "We have a full attendance of " + chosenStadium.getCapacity() + " here at today's shootout is between " + chosenTeam1.getName() +
                " and " + chosenTeam2.getName() + ". Both teams have had a great season to date but it all comes down to this shootout." +
                "Will it be " + chosenTeam1.getName() +"'s win or " + chosenTeam2.getName() + "'s win? Stay tuned to find out! This should " +
                "be a cracking shootout.");

        intro.setVisible(true);

        JTextArea teamInfo = new JTextArea("Here is the lineup for today's teams: \n" +
                "----------" + chosenTeam1.getName() + "----------\n" + Arrays.toString(chosenTeam1.getPlayers()) +
                "\n\n----------" + chosenTeam2.getName() + "----------" + Arrays.toString(chosenTeam2.getPlayers()));
        teamInfo.setVisible(true);


        //Choose where to shoot
        Player[] players1, players2;
        players1 = chosenTeam1.getPlayers();               //fills chosenTeam1 with array of players          //Stephanie Collins
        players2 = chosenTeam2.getPlayers();               //fills chosenTeam2 with array of players

        for(int i=0; i<players1.length; i++){
            double test = (double) players1[i].getPensScored() / (double) players1[i].getPensTaken();

            JOptionPane.showMessageDialog(null, "Up for " + chosenTeam1.getName() + " is " +
                    players1[i].getName() + "\n\nSome info to help you with your shot: \n\nPosition is " + players1[i].getPosition() +
                    "\nChance of scoring penalty is " + test * 100 +
                    "% \nLet's see how he does this evening");

            String[] shotOptions = {"Top Left", "Top Right", "Bottom Left", "Bottom Right", "Center"};
               JComboBox shot = new JComboBox(shotOptions);
               shot.setSelectedIndex(-1);
               JOptionPane.showMessageDialog(null, shot, "Choose your shot", JOptionPane.QUESTION_MESSAGE);
        }

        //algorithm to score/miss
        //score changes
    }

    private static Team buildRealTeam() {
        Player ramos = new Player("Sergio Ramos", "Defender", 25, 22, new GregorianCalendar(1986, Calendar.MARCH, 30));
        Player benzema = new Player("Karim Benzema", "Forward", 16, 14, new GregorianCalendar(1987, Calendar.DECEMBER, 19));
        Player hazard = new Player("Eden Hazard", "Forward", 55, 48, new GregorianCalendar(1991, Calendar.JANUARY, 7));
        Player kroos = new Player("Toni Kroos", "Midfield", 4, 4, new GregorianCalendar(1990, Calendar.JANUARY, 4));
        Player bale = new Player("Gareth Bale", "Forward", 9, 7, new GregorianCalendar(1989, Calendar.JULY, 16));
        Keeper courtois = new Keeper("Thibaut Courtois",  7, 3, new GregorianCalendar(1992, Calendar.MAY, 11));
        Player[] realPlayers = new Player[]{ramos, benzema, hazard, kroos, bale};
        return new Team("Real Madrid", realPlayers, courtois);
    }

    public Game(){
        //Customize the window
        setTitle("Penalty Shootout");
        setSize(650, 650);
        setLocation(300,20);
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
        //team 1 player 1 : score/miss
        //team 2 player 2 : score miss
        //.......
        pane.add(team1label);

        pane.add(Box.createRigidArea(new Dimension(0, 40)));

    }

    private static Team buildLiverpoolTeam() {
        Player milner = new Player("James Milner", "Midfield", 34, 29, new GregorianCalendar(1986, Calendar.JANUARY, 4));
        Player salah = new Player("Mohamed Salah", "Forward", 16, 13, new GregorianCalendar(1992, Calendar.JUNE, 15));
        Player firmino = new Player("Roerto Firmino", "Forward", 9, 5, new GregorianCalendar(1991, Calendar.OCTOBER, 2));
        Player mane = new Player("Sadio Mané", "Forward", 6, 2, new GregorianCalendar(1992, Calendar.APRIL, 10));
        Player origi = new Player("Divock Origi", "Forward", 8, 4, new GregorianCalendar(1995, Calendar.APRIL, 18));
        Keeper alisson = new Keeper("Alisson Becker",  9, 3, new GregorianCalendar(1992, Calendar.OCTOBER, 2));
        Player[] liverpoolPlayers = new Player[]{milner, salah, firmino, mane, origi};
        return new Team("Liverpool", liverpoolPlayers, alisson);
    }

    private static Team buildManuTeam() {
        Player martial = new Player("Anthony Martial", "Forward", 12, 9, new GregorianCalendar(1995, Calendar.DECEMBER, 5));
        Player rashford = new Player("Marcus Rashford", "Forward", 10, 8, new GregorianCalendar(1997, Calendar.OCTOBER, 31));
        Player pogba = new Player("Paul Pogba", "Midfield", 18, 13, new GregorianCalendar(1993, Calendar.MARCH, 15));
        Player james = new Player("Daniel James", "Midfield", 1, 1, new GregorianCalendar(1997, Calendar.NOVEMBER, 10));
        Player lingard = new Player("Jesse Lingard", "Midfield", 2, 1, new GregorianCalendar(1992, Calendar.DECEMBER, 15));
        Keeper degea = new Keeper("David DeGea",  10, 4, new GregorianCalendar(1990, Calendar.NOVEMBER, 7));
        Player[] manuPlayers = new Player[]{martial, rashford, pogba, james, lingard};
        return new Team("Man United", manuPlayers, degea);
    }

}
