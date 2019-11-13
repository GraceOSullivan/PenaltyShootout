package classes;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private static void createGUI(){
        //Create JMenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu instructions, stadiums, teams, players;
        JMenuItem addStadium, viewStadium, addTeam, viewTeam, addPlayer, viewPlayers;

        instructions = new JMenu("Instructions");           //instructions
        stadiums = new JMenu("Stadiums");                   //stadiums
        addStadium = new JMenuItem("Add Stadium");
        viewStadium = new JMenuItem("View Stadium");
        stadiums.add(addStadium);
        stadiums.add(viewStadium);
        teams = new JMenu("Teams");                         //teams
        addTeam = new JMenuItem("Add Team");
        viewTeam = new JMenuItem("View Team");
        teams.add(addTeam);
        teams.add(viewTeam);
        players = new JMenu("Players");                     //players
        addPlayer = new JMenuItem("Add Player");
        viewPlayers = new JMenuItem("View Players");
        players.add(addPlayer);
        players.add(viewPlayers);

        menuBar.add(instructions);
        menuBar.add(stadiums);
        menuBar.add(teams);
        menuBar.add(players);

        //Create JFrame
        JFrame frame = new JFrame();
        frame.setTitle("Penalty Shootout");
        frame.setSize(900, 600);
        frame.setLocation(300,100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addComponentsToPane(frame.getContentPane());

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    public static void addComponentsToPane(Container pane){
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        //Add Labels
        JLabel nameLabel = new JLabel("Penalty Shootout");
        nameLabel.setFont(new Font("Courier New", Font.BOLD, 42));
        addLabel(nameLabel, pane);

        pane.add(Box.createRigidArea(new Dimension(0, 40)));    //space between components   //http://www.fredosaurus.com

        ImageIcon image = new ImageIcon("C:/Users/Grace O'Sullivan/Desktop/PenaltyShootout/src/resources/introGoal.jpg");   //https://stackoverflow.com
        JLabel imgLabel = new JLabel(image);
        addLabel(imgLabel, pane);

        pane.add(Box.createRigidArea(new Dimension(0, 40)));

        //Add Buttons
        addButton("Play", pane);

        pane.add(Box.createRigidArea(new Dimension(0, 10)));

        addButton("Quit", pane);

    }

    public static void addButton(String text, Container container){
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }
     public static void addLabel(JLabel label, Container container){
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(label);
     }

     public static void main(String args[]){
        createGUI();
     }


}
