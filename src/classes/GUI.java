package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    //private instance variables
    JButton quitButton, playButton;

    public static void main(String args[]){
        GUI gui = new GUI();
        gui.setVisible(true);

        gui.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                JOptionPane.showMessageDialog(null, "Closing Window", "Exit", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } // end windowClosing
        }// end anonymous inner class definition
        ); //end of addWindowListener method argument
    }

    public GUI(){
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

        //Customize the window
        setTitle("Penalty Shootout");
        setSize(900, 600);
        setLocation(300,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);

        //Customize the Content Pane
        Container pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        //Create and add Labels
        JLabel nameLabel = new JLabel("Penalty Shootout");
        nameLabel.setFont(new Font("Courier New", Font.BOLD, 42));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(nameLabel);

        pane.add(Box.createRigidArea(new Dimension(0, 40)));    //space between components   //http://www.fredosaurus.com

        ImageIcon image = new ImageIcon("C:/Users/Grace O'Sullivan/Desktop/PenaltyShootout/src/resources/introGoal.jpg");   //https://stackoverflow.com
        JLabel imgLabel = new JLabel(image);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(imgLabel);

        pane.add(Box.createRigidArea(new Dimension(0, 40)));

        //Create and add Buttons
        playButton = new JButton("Play");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(playButton);

        pane.add(Box.createRigidArea(new Dimension(0, 10)));

        quitButton = new JButton("Quit");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(quitButton);

        //Add listeners for the buttons - private inner class
        playButton.addActionListener(new ButtonHandler());
        quitButton.addActionListener((new ButtonHandler()));
    } //end constructor

    //Inner-class handles clicks on buttons
    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == playButton)
                JOptionPane.showMessageDialog(null, "Play Screen shows up");
            else {
                int ans = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit? ",
                        "Select an Option...", JOptionPane.YES_NO_OPTION);

                if(ans == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        }
    } //end private inner class ButtonHandler

}


