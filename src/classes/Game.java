package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

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
         setVisible(true);
        JComboBox stadium = new JComboBox(stadiumNames);
        stadium.setSelectedIndex(-1);                           //No placeholder before stadium chosen
        do {
            JOptionPane.showMessageDialog(null, stadium, "Please choose a Stadium", JOptionPane.QUESTION_MESSAGE);
            if (stadium.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "You must choose a Stadium!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (stadium.getSelectedIndex() < 0);
        Stadium chosenStadium = stadiumOptions[stadium.getSelectedIndex()];        //takes chosen stadium name and gets corresponding stadium objects

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
            JOptionPane.showMessageDialog(null, team2, "Please choose Team 2", JOptionPane.QUESTION_MESSAGE);
            if (team2.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "You must choose a Team!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (team2.getSelectedIndex() < 0);
        Team chosenTeam2 = teamOptions[team2.getSelectedIndex()];        //takes chosen team name and gets corresponding team object


        //--------------------Introduction JTextArea----------------------
        JTextArea intro = new JTextArea("Welcome everyone to " + chosenStadium.getName() + " for the Penalty Shootout of the decade!!\n\n" +
                "We have a full attendance of " + chosenStadium.getCapacity() + " here at today's shootout between " + chosenTeam1.getName() +
                " and " + chosenTeam2.getName() + ".\n\nBoth teams have had a great season to date but it all comes down \nto this shootout." +
                "\n\nWill it be " + chosenTeam1.getName() +"'s win or " + chosenTeam2.getName() + "'s win? \nStay tuned to find out!", 10, 1 );
        intro.setVisible(true);
        JOptionPane.showMessageDialog(null, intro);

        //-----------------Team Lineups JTextArea--------------------
        JTextArea team1Lineup = new JTextArea("Here is the lineup for " + chosenTeam1.getName() + " today: \n" +
                chosenTeam1.toString(), 50, 1);
        team1Lineup.setVisible(true);
        JOptionPane.showMessageDialog(null, team1Lineup);

        JTextArea team2Lineup = new JTextArea("Here is the lineup for " + chosenTeam2.getName() + " today: \n" +
                chosenTeam2.toString(), 50, 1);
        team2Lineup.setVisible(true);
        JOptionPane.showMessageDialog(null, team2Lineup);

        //---------------Shooting Begins------------
        Player[] players1 = chosenTeam1.getPlayers();               //fills chosenTeam1 with array of players          //Stephanie Collins
        Player[] players2 = chosenTeam2.getPlayers();               //fills chosenTeam2 with array of players

        //JTextArea for results to be shown on content pane
        JLabel results = new JLabel("Results");
        results.setFont(new Font("Courier New", Font.BOLD, 36));
        results.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(results);
        JTextArea playerResults = new JTextArea(10, 1);
        playerResults.setVisible(true);
        pane.add(playerResults);

        for(int i=0; i<players1.length; i++){
            //------------------------Team 1 players shooting----------------------
            Player player1 = players1[i];
            double player1ConversionRate = (double) player1.getPensScored() / (double) player1.getPensTaken() * 100;             //rate based on pensTaken and pensScored
            double player1PossibilityOfScoring = calculatePlayersPossibilityOfScoring(player1, player1ConversionRate);         //possibility of scoring this penalty after position taken into consideration
            JOptionPane.showMessageDialog(null, "Up for " + chosenTeam1.getName() + " is " +
                    player1.getName() + "\n\nSome info to help you with your shot: \nPlayer's conversion rate is " + String.format("%.0f", player1ConversionRate) +
                    "% \nPosition is " + player1.getPosition() +
                    "\nChance of scoring this penalty is " + String.format("%.0f", player1PossibilityOfScoring) +
                    "% \n\nLet's see how he does this evening...");
            String[] shotOptions = {"Top Left", "Top Right", "Bottom Left", "Bottom Right", "Center"};
            JComboBox shotChoice1 = new JComboBox(shotOptions);
            shotChoice1.setSelectedIndex(-1);
            do {
                JOptionPane.showMessageDialog(null, shotChoice1, "Choose your shot", JOptionPane.QUESTION_MESSAGE);
                if (shotChoice1.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(null, "You must choose somewhere to shoot!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } while (shotChoice1.getSelectedIndex() < 0);

            //Algorithm to score/miss
            Random random1 = new Random();
            int randomNum1 = random1.nextInt(100 + 1);
            System.out.println(randomNum1);
            if(randomNum1 <= player1PossibilityOfScoring) {
                JOptionPane.showMessageDialog(null, "SCORE!!!!!", "He did it!", JOptionPane.PLAIN_MESSAGE);
                playerResults.append(player1.getName() + ": SCORE\n");
            }
            else if (randomNum1 > player1PossibilityOfScoring){
                JOptionPane.showMessageDialog(null, "MISS!!", "Not this time!", JOptionPane.PLAIN_MESSAGE);
                playerResults.append(player1.getName() + ": MISS\n");
            }

            //------------------------Team 2 players shooting----------------------
            Player player2 = players2[i];

            double player2ConversionRate = (double) player2.getPensScored() / (double) player2.getPensTaken() * 100;              //rate based on pensTaken and pensScored
            double player2PossibilityOfScoring = calculatePlayersPossibilityOfScoring(player2, player2ConversionRate);      //possibility of scoring this penalty after position taken into consideration
            JOptionPane.showMessageDialog(null, "Up for " + chosenTeam2.getName() + " is " +
                    player2.getName() + "\n\nSome info to help you with your shot: \n Player's conversion rate is " + String.format("%.0f", player2ConversionRate) +
                    "%\nPosition is " + player2.getPosition() +
                    "\nChance of scoring this penalty is " + String.format("%.0f",  player2PossibilityOfScoring) +
                    "% \n\nLet's see how he does this evening...");

            JComboBox shotChoice2 = new JComboBox(shotOptions);
            shotChoice2.setSelectedIndex(-1);
            do {
                JOptionPane.showMessageDialog(null, shotChoice2, "Choose your shot", JOptionPane.QUESTION_MESSAGE);
                if (shotChoice2.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(null, "You must choose somewhere to shoot!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } while (shotChoice2.getSelectedIndex() < 0);

            //Algorithm to score/miss
            Random random2 = new Random();
            int randomNum2 = random2.nextInt(100 + 1);
            System.out.println(randomNum2);
            if(randomNum2 <= player1PossibilityOfScoring) {
                JOptionPane.showMessageDialog(null, "SCORE!!!!!", "He did it!", JOptionPane.PLAIN_MESSAGE);
                playerResults.append(player2.getName() + ": SCORE\n");
            }
            else if (randomNum2 > player1PossibilityOfScoring) {
                JOptionPane.showMessageDialog(null, "MISS!!", "Not this time!", JOptionPane.PLAIN_MESSAGE);
                playerResults.append(player2.getName() + ": MISS\n");
            }
        }//end for loop

    }

    private double calculatePlayersPossibilityOfScoring(Player player, double playerConversionRate) {
        double playerPossibilityOfScoring = 0.0;
        switch (player.getPosition()) {
            case "Defender":
                playerPossibilityOfScoring = playerConversionRate * 0.5;
                break;
            case "Midfield":
                playerPossibilityOfScoring = playerConversionRate * 0.75;
                break;
            case "Forward":
                playerPossibilityOfScoring = playerConversionRate;
                break;
        }
        return playerPossibilityOfScoring;
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
}
