package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class StartPage extends JFrame {

    //------------Private Instance Variables-------------------
    private JButton quitButton, playButton;
    private JTextArea instructionsText = new JTextArea();
    private JMenuItem viewInstructions, viewStadium, viewTeamPlayers, viewResults;

    //-----------------Create Stadiums----------------------
    private Stadium anfield = new Stadium("Anfield", 54074);
    private Stadium oldTraf = new Stadium("Old Trafford", 76000);
    private Stadium santiago = new Stadium("Santiago Bernabéu Stadium", 81044);


    public static void main(String args[]){
        StartPage start = new StartPage();
        start.setVisible(true);
    } //end main()

    public StartPage(){

        //---------------------JMenuBar------------------------
        JMenuBar menuBar = new JMenuBar();
        JMenu instructions, stadiums, teams, players, file;

        //instructions
        instructions = new JMenu("Instructions");
        viewInstructions = new JMenuItem("View Instructions");
        instructions.add(viewInstructions);
        viewInstructions.addActionListener(new MenuHandler());
        //stadiums
        stadiums = new JMenu("Stadiums");
        viewStadium = new JMenuItem("View Stadiums");
        stadiums.add(viewStadium);
        viewStadium.addActionListener(new MenuHandler());
        //teams
        teams = new JMenu("Teams");
        viewTeamPlayers = new JMenuItem("View Teams & Players");
        teams.add(viewTeamPlayers);
        viewTeamPlayers.addActionListener(new MenuHandler());
        //file
        file = new JMenu("File");
        viewResults = new JMenuItem("View Saved Games");
        viewResults.addActionListener(new MenuHandler());
        file.add(viewResults);

        //Adding to menuBar
        menuBar.add(instructions);
        menuBar.add(stadiums);
        menuBar.add(teams);
        menuBar.add(file);

        //--------------------JTextArea for Instructions--------------------
        instructionsText.append("Welcome to Penalty Shootout!" +
                                "\nFirst, choose your Stadium. Next, choose your team" +
                                "\n\nThen the shootout begins:" +
                                "\n- Each team gets 5 shots " +
                                "\n- Take note of the players position and possibility to score \n- Take note of keepers conversion rate " +
                                "\n- Choose the corner you would like to aim towards \n- The team with the most scores at the end wins." +
                                "\n\nREMEMBER: " +
                                "\n- Forwards are most likely to score \n- Midfielders are 25% less likely to score than a forward" +
                                "\n- Defenders are 50% less likely to score than a forward\n\n " +
                                "Best of luck! ");
        instructionsText.setVisible(false);

        //----------------------Customize the Window--------------------------
        setTitle("Penalty Shootout");
        setSize(650, 650);
        setLocation(300,20);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);

        //-----------------Customize the Content Pane--------------------
        Container pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        //----------------Create and add Labels-----------------------
        JLabel nameLabel = new JLabel("Penalty Shootout");
        nameLabel.setFont(new Font("Courier New", Font.BOLD, 42));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(nameLabel);

        pane.add(Box.createRigidArea(new Dimension(0, 40)));    //space between components   //http://www.fredosaurus.com

        ImageIcon image = new ImageIcon("Resources/introGoal.jpg");   //https://stackoverflow.com
        JLabel imgLabel = new JLabel(image);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(imgLabel);

        pane.add(Box.createRigidArea(new Dimension(0, 40)));

        //----------------Create and add Buttons------------------
        playButton = new JButton("Play");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.addActionListener(new ButtonHandler());
        pane.add(playButton);

        pane.add(Box.createRigidArea(new Dimension(0, 10)));

        quitButton = new JButton("Quit");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.addActionListener((new ButtonHandler()));
        pane.add(quitButton);
    } //end constructor StartPage()

    //---------Private inner-class handles clicks on buttons-----------
    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == playButton) {
                GameConfig gameConfig = new GameConfig();
                gameConfig.addStadiums(anfield, oldTraf, santiago);
                gameConfig.addTeams(buildLiverpoolTeam(), buildManuTeam(), buildRealTeam());
                Game game = new Game(gameConfig);
                game.setVisible(true);
            }
            else {
                int ans = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit? ",
                        "Select an Option...", JOptionPane.YES_NO_OPTION);
                if(ans == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        }
    } //end private inner class ButtonHandler

    //---------Private inner-class handles clicks on MenuItems----------------
    private class MenuHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == viewInstructions) {
                instructionsText.setVisible(true);
                JOptionPane.showMessageDialog(null, instructionsText);
            } else if(e.getSource() == viewStadium) {
                JOptionPane.showMessageDialog(null, "----------Stadiums----------\n\n" +
                        "---" + anfield.getName() + "---" + anfield.toString() + "\n\n" +
                        "---" + oldTraf.getName() + "---" + oldTraf.toString() + "\n\n" +
                        "---" + santiago.getName() + "---" + santiago.toString(), "View Stadiums", JOptionPane.INFORMATION_MESSAGE);
            } else if(e.getSource() == viewTeamPlayers) {
                JTextArea viewTeams = new JTextArea(buildLiverpoolTeam().toString() + "\n" +
                        buildManuTeam().toString() + "\n" + buildRealTeam().toString());
                viewTeams.setLineWrap(true);                                                    //Makes JTextArea scrollable https://stackoverflow.com/
                viewTeams.setWrapStyleWord(true);
               JScrollPane scrollPane1 = new JScrollPane(viewTeams);
               scrollPane1.setPreferredSize(new Dimension(300,500 ));
               viewTeams.setVisible(true);
               JOptionPane.showMessageDialog(null, scrollPane1);
            } else if(e.getSource() == viewResults) {
                try {
                    File file = new File("Resources/scores.dat");
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    ArrayList<Score> scores = (ArrayList<Score>) ois.readObject();
                    System.out.println(scores);
                    ois.close();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    private static Team buildLiverpoolTeam() {
        Player milner = new Player("James Milner", "Midfield", 34, 29, new GregorianCalendar(1986, Calendar.JANUARY, 4));
        Player salah = new Player("Mohamed Salah", "Forward", 16, 13, new GregorianCalendar(1992, Calendar.JUNE, 15));
        Player firmino = new Player("Roerto Firmino", "Forward", 9, 5, new GregorianCalendar(1991, Calendar.OCTOBER, 2));
        Player mane = new Player("Sadio Mané", "Forward", 6, 2, new GregorianCalendar(1992, Calendar.APRIL, 10));
        Player origi = new Player("Divock Origi", "Forward", 8, 4, new GregorianCalendar(1995, Calendar.APRIL, 18));
        Keeper alisson = new Keeper("Alisson Becker",  15, 11, new GregorianCalendar(1992, Calendar.OCTOBER, 2));
        Player[] liverpoolPlayers = new Player[]{milner, salah, firmino, mane, origi};
        return new Team("Liverpool", liverpoolPlayers, alisson);
    }

    private static Team buildManuTeam() {
        Player martial = new Player("Anthony Martial", "Forward", 12, 9, new GregorianCalendar(1995, Calendar.DECEMBER, 5));
        Player rashford = new Player("Marcus Rashford", "Forward", 10, 8, new GregorianCalendar(1997, Calendar.OCTOBER, 31));
        Player pogba = new Player("Paul Pogba", "Midfield", 18, 13, new GregorianCalendar(1993, Calendar.MARCH, 15));
        Player james = new Player("Daniel James", "Midfield", 1, 1, new GregorianCalendar(1997, Calendar.NOVEMBER, 10));
        Player lingard = new Player("Jesse Lingard", "Midfield", 2, 1, new GregorianCalendar(1992, Calendar.DECEMBER, 15));
        Keeper degea = new Keeper("David DeGea",  14, 10, new GregorianCalendar(1990, Calendar.NOVEMBER, 7));
        Player[] manuPlayers = new Player[]{martial, rashford, pogba, james, lingard};
        return new Team("Man United", manuPlayers, degea);
    }

    private static Team buildRealTeam() {
        Player ramos = new Player("Sergio Ramos", "Defender", 25, 22, new GregorianCalendar(1986, Calendar.MARCH, 30));
        Player benzema = new Player("Karim Benzema", "Forward", 16, 14, new GregorianCalendar(1987, Calendar.DECEMBER, 19));
        Player hazard = new Player("Eden Hazard", "Forward", 55, 48, new GregorianCalendar(1991, Calendar.JANUARY, 7));
        Player kroos = new Player("Toni Kroos", "Midfield", 4, 4, new GregorianCalendar(1990, Calendar.JANUARY, 4));
        Player bale = new Player("Gareth Bale", "Forward", 9, 7, new GregorianCalendar(1989, Calendar.JULY, 16));
        Keeper courtois = new Keeper("Thibaut Courtois",  10, 7, new GregorianCalendar(1992, Calendar.MAY, 11));
        Player[] realPlayers = new Player[]{ramos, benzema, hazard, kroos, bale};
        return new Team("Real Madrid", realPlayers, courtois);
    }
}


