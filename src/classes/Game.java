package classes;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JFrame {

    public static void main(String[] args){
    }

    protected Game(GameConfig gameConfig) {                 //takes in GameConfig to be able to take in stadiums/players/teams
        //Customize the window
        setTitle("Penalty Shootout");
        setSize(650, 650);
        setLocation(300,20);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Customize the Content Pane
        Container pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        //Create and add Labels
        pane.add(Box.createRigidArea(new Dimension(0, 20)));            //space between components   //http://www.fredosaurus.com
        //name label
        JLabel nameLabel = new JLabel("Penalty Shootout");
        nameLabel.setFont(new Font("Courier New", Font.BOLD, 42));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(nameLabel);
        pane.add(Box.createRigidArea(new Dimension(0, 20)));
        //image label
        ImageIcon image = new ImageIcon("Resources/gameGoal.jpg");
        JLabel imgLabel = new JLabel(image);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(imgLabel);
        pane.add(Box.createRigidArea(new Dimension(0, 20)));

        setVisible(true);                   //shows Game screen

        //   -----------------------------Selecting Stadium -------------------------------
        Stadium chosenStadium = selectStadium(gameConfig);

        //   -----------------------------Selecting Teams ------------------------------------
        ArrayList<Team> teamList = gameConfig.getTeams();                            //Team array of team objects from GameConfig
        String[] teamNames = new String[teamList.size()];                            //String array of team names for JComboBox

        for(int i=0; i<teamList.size(); i++){
            teamNames[i] = teamList.get(i).getName();                               //populates teamNames array with team objects names
        }

        Team chosenTeam1 = selectTeam1(teamList, teamNames);
        Team chosenTeam2 = selectTeam2(teamList, teamNames);

        //--------------------Introduction JTextArea----------------------
        JTextArea intro = new JTextArea("Welcome everyone to " + chosenStadium.getName() + " for the Penalty Shootout of the decade!!\n\n" +
                "We have a full attendance of " + chosenStadium.getCapacity() + " here at today's shootout between " + chosenTeam1.getName() +
                " and " + chosenTeam2.getName() + ".\n\nBoth teams have had a great season to date but it all comes down \nto this shootout." +
                "\n\nWill it be " + chosenTeam1.getName() +"'s win or " + chosenTeam2.getName() + "'s win? \nStay tuned to find out!", 10, 1 );
        intro.setVisible(true);
        JOptionPane.showMessageDialog(null, intro);

        //-----------------Team Lineups JTextArea--------------------
        JTextArea team1Lineup = new JTextArea("Here is the lineup for " + chosenTeam1.getName() + "\n" +
                chosenTeam1.toString());
        team1Lineup.setLineWrap(true);
        team1Lineup.setWrapStyleWord(true);
        JScrollPane scrollPane1 = new JScrollPane(team1Lineup);
        scrollPane1.setPreferredSize( new Dimension( 200, 400 ) );
        team1Lineup.setVisible(true);
        JOptionPane.showMessageDialog(null, scrollPane1);

        JTextArea team2Lineup = new JTextArea("Here is the lineup for " + chosenTeam2.getName() + " today: \n" +
                chosenTeam2.toString());
        team2Lineup.setLineWrap(true);
        team2Lineup.setWrapStyleWord(true);
        JScrollPane scrollPane2 = new JScrollPane(team2Lineup);
        scrollPane2.setPreferredSize( new Dimension( 200, 400 ) );
        team2Lineup.setVisible(true);
        JOptionPane.showMessageDialog(null, scrollPane2);

        //-----------------------------Shooting Begins-----------------------------------
        Player[] players1 = chosenTeam1.getPlayers();               //fills chosenTeam1 with array of players          //Stephanie Collins
        Player[] players2 = chosenTeam2.getPlayers();               //fills chosenTeam2 with array of players
        Keeper keeper1 = chosenTeam1.getKeeper();
        Keeper keeper2 = chosenTeam2.getKeeper();

        //JTextArea for results to be shown on content pane
        JLabel results = new JLabel("Results");
        results.setFont(new Font("Courier New", Font.BOLD, 36));
        results.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(results);
        JTextArea playerResults = new JTextArea(10, 1);
        playerResults.setVisible(true);
        pane.add(playerResults);

        int team1Score = 0, team2Score =0;

        for(int i=0; i<players1.length; i++){

            //------------------------Team 1 players shooting----------------------
            Player player1 = players1[i];
            double player1ConversionRate = (double) player1.getPensScored() / (double) player1.getPensTaken() * 100;             //rate based on pensTaken and pensScored
            double keeper1ConversionRate = (double) keeper1.getPensSaved() / (double) keeper1.getTotalPens() * 100;
            double player1PossibilityOfScoring = calculatePlayersPossibilityOfScoring(player1, player1ConversionRate, keeper1ConversionRate);         //possibility of scoring this penalty after position taken into consideration
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
                JOptionPane.showMessageDialog(null, "GOAL!!!!!", "He did it!", JOptionPane.PLAIN_MESSAGE);
                playerResults.append(player1.getName() + ": GOAL\n");
                team1Score++;
            } else if (randomNum1 > player1PossibilityOfScoring){
                JOptionPane.showMessageDialog(null, "MISS!!", "Not this time!", JOptionPane.PLAIN_MESSAGE);
                playerResults.append(player1.getName() + ": MISS\n");
            }

            //------------------------Team 2 players shooting----------------------
            Player player2 = players2[i];
            double player2ConversionRate = (double) player2.getPensScored() / (double) player2.getPensTaken() * 100;              //rate based on pensTaken and pensScored
            double keeper2ConversionRate = (double) keeper2.getPensSaved() / (double) keeper2.getTotalPens() * 100;
            double player2PossibilityOfScoring = calculatePlayersPossibilityOfScoring(player2, player2ConversionRate, keeper2ConversionRate);      //possibility of scoring this penalty after position taken into consideration
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
                JOptionPane.showMessageDialog(null, "GOAL!!!!!", "He did it!", JOptionPane.PLAIN_MESSAGE);
                playerResults.append(player2.getName() + ": GOAL\n");
                team2Score++;
            } else if (randomNum2 > player1PossibilityOfScoring) {
                JOptionPane.showMessageDialog(null, "MISS!!", "Not this time!", JOptionPane.PLAIN_MESSAGE);
                playerResults.append(player2.getName() + ": MISS\n");
            }
        }//end for loop

        String winner = null;
        if(team1Score>team2Score) {
            winner = chosenTeam1.getName();
        } else if(team2Score>team1Score) {
            winner = chosenTeam2.getName();
        }
        else {
            JTextArea draw = new JTextArea("AND IT'S A DRAW! \n\n Replay will be held next week!");
            JOptionPane.showMessageDialog(null, draw, "Draw", JOptionPane.INFORMATION_MESSAGE);
        }

        String result = chosenTeam1.getName() + ": " + team1Score + "\t" + chosenTeam2.getName() + ": " + team2Score;
        JTextArea finalResult = new JTextArea("WINNER: " + winner + "\n\n" + result);
        JOptionPane.showMessageDialog(null, finalResult, "Winner", JOptionPane.INFORMATION_MESSAGE);

        //---------------------Writing to file-------------------------
        try {
            File file = new File("Resources/scores.dat");
            FileOutputStream fos = new FileOutputStream(file, true);                    //low-level
            ObjectOutputStream oos = new ObjectOutputStream(fos);                               //high-level
            Score score = new Score();
            score.setTeam1Name(chosenTeam1.getName());
            score.setTeam1Score(team1Score);
            score.setTeam2Name(chosenTeam2.getName());
            score.setTeam2Score(team2Score);
            ArrayList<Score> scores = new ArrayList<>();
            scores.add(score);                                                       //Adds score to array list - still only printing first entry though
            oos.writeObject(scores);                                                 //writing to file
            oos.close();                                                             //stops wasting memory - tidies up memory after writing to file
        }
        catch(IOException ex){
            ex.printStackTrace();
        }

    }//end constructor Game()

    private Team selectTeam2(ArrayList<Team> teamList, String[] teamNames) {
        JComboBox team2 = new JComboBox(teamNames);
        team2.setSelectedIndex(-1);
        do {
            JOptionPane.showMessageDialog(null, team2, "Please choose Team 2", JOptionPane.QUESTION_MESSAGE);
            if (team2.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "You must choose a Team!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (team2.getSelectedIndex() < 0);
        return teamList.get(team2.getSelectedIndex());
    }

    private Team selectTeam1(ArrayList<Team> teamList, String[] teamNames) {
        JComboBox team1 = new JComboBox(teamNames);
        team1.setSelectedIndex(-1);
        do {
            JOptionPane.showMessageDialog(null, team1, "Please choose Team 1", JOptionPane.QUESTION_MESSAGE);
            if (team1.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "You must choose a Team!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (team1.getSelectedIndex() < 0);
        return teamList.get(team1.getSelectedIndex());
    }

    private Stadium selectStadium(GameConfig gameConfig) {
        ArrayList<Stadium> stadiumList = gameConfig.getStadiums();                 //Stadium ArrayList of stadium objects from GameConfig
        String[] stadiumNames = new String[stadiumList.size()];                   //String array of stadium names for JComboBox
        for (int i=0; i<stadiumList.size(); i++) {
            stadiumNames[i] = stadiumList.get(i).getName();                         //populates stadiumNames with stadium objects names  Daniel O'Sullivan
        }
        JComboBox stadium = new JComboBox(stadiumNames);
        stadium.setSelectedIndex(-1);                           //No placeholder before stadium chosen
        do {
            JOptionPane.showMessageDialog(null, stadium, "Please choose a Stadium", JOptionPane.QUESTION_MESSAGE);
            if (stadium.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "You must choose a Stadium!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (stadium.getSelectedIndex() < 0);
        return stadiumList.get(stadium.getSelectedIndex());
    }

    private double calculatePlayersPossibilityOfScoring(Player player, double playerConversionRate, double keeperConversionRate) {
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
}
