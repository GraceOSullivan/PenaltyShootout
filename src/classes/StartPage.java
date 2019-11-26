package classes;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class StartPage extends JFrame {

    //------------Private Instance Variables-------------------
    private JButton quitButton, playButton;
    private JTextArea instructionsText = new JTextArea();
    private JMenuItem viewStadium, viewTeam, viewPlayers, saveGame, viewSaved;

    public static void main(String args[]){
        StartPage startPage = new StartPage();
    } //end main()

    public StartPage(){

        //---------------------JMenuBar------------------------
        JMenuBar menuBar = new JMenuBar();
        JMenu instructions, stadiums, teams, players, file;

        //instructions
        instructions = new JMenu("Instructions");

        //stadiums
        stadiums = new JMenu("Stadiums");
        viewStadium = new JMenuItem("View Stadiums");
        stadiums.add(viewStadium);
        viewStadium.addActionListener(new MenuHandler());

        //teams
        teams = new JMenu("Teams");
        viewTeam = new JMenuItem("View Teams");
        teams.add(viewTeam);
        viewTeam.addActionListener(new MenuHandler());


        //players
        players = new JMenu("Players");
        viewPlayers = new JMenuItem("View Players");
        players.add(viewPlayers);
        viewPlayers.addActionListener(new MenuHandler());

        //file
        file = new JMenu("File");
        saveGame = new JMenuItem("Save Game");
        viewSaved = new JMenuItem("View Saved Games");
        file.add(saveGame);
        file.add(viewSaved);

        //Adding to menuBar
        menuBar.add(instructions);
        menuBar.add(stadiums);
        menuBar.add(teams);
        menuBar.add(players);
        menuBar.add(file);

        //--------------------JTextArea for Instructions--------------------
        instructionsText.append("Welcome to Penalty Shootout!" +
                                "\nFirst, choose your Stadium. Next, choose your team" +
                                "\n\nThen the shootout begins:" +
                                "\n- Each team gets 5 shots " +
                                "\n- Choose the corner you would like to aim towards \n- Enter the power you want behind your kick" +
                                "\n- The team with the most scores wins. If it's a draw it goes to Sudden Death!" +
                                "\n\nREMEMBER: " +
                                "\n- More power behind your kick = less accurate aim \n- Less power behind your kick = keeper more likely to save" +
                                "\n- Defenders have more powerful kicks (less accurate aim) \n-Forwards have better aim (less powerful)");
        instructionsText.setVisible(false);
        //MenuListener for instructions JMenu
      //  instructionsText.addMenuListener(new MyMenuListener());

        //----------------------Customize the Window--------------------------
        setTitle("Penalty Shootout");
        setSize(900, 600);
        setLocation(300,100);
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
        pane.add(playButton);

        pane.add(Box.createRigidArea(new Dimension(0, 10)));

        quitButton = new JButton("Quit");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(quitButton);

        //--------------Add Listeners for Buttons (private inner class)------------
        playButton.addActionListener(new ButtonHandler());
        quitButton.addActionListener((new ButtonHandler()));
    } //end constructor

    //---------Private inner-class handles clicks on buttons-----------
    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == playButton) {
                Game game = new Game();
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

    //----------Private inner-class handles clicks on JMenu Instructions)------------
    private class MyMenuListener implements MenuListener {
        public void menuSelected(MenuEvent e) {
            if(e.getSource() == instructionsText)
                instructionsText.setVisible(true);
        }

        //must implement all abstract methods
        public void menuDeselected(MenuEvent e) {}
        public void menuCanceled(MenuEvent e) {}
    }

    //---------Private inner-class handles clicks on MenuItems----------------
    private class MenuHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == viewStadium)
                //JOptionPane.showMessageDialog(null, anfield.toString() + "\n" +
                //        oldTraff.toString() + "\n" + santiago.toString(), "Stadiums", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Anfield: \nOld Trafford: \nSantiago Bernabéu Stadium:");

            else if(e.getSource() == viewTeam)
              //  JOptionPane.showMessageDialog(null, liverpool.toString() + "\n" +
              //          manu.toString() + "\n" + real.toString(), "Teams", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Liverpool: \nMan United: \nReal Madrid:");

            else if(e.getSource() == viewPlayers) {
              //  JOptionPane.showMessageDialog(null, "----------Liverpool Players----------\n" +
              //          milner.toString() + salah.toString() + firmino.toString + mane.toString + origi.toString +
              //          alisson.toString());
                JOptionPane.showMessageDialog(null, "Liverpool: \nPlayer1: \nPlayer2: \n......");

              //  JOptionPane.showMessageDialog(null, "----------Man United Players----------\n" +
               //         martial.toString() + rashford.toString() + pogba.toString + james.toString + lingard.toString +
               //         degea.toString());
                JOptionPane.showMessageDialog(null, "Man United: \nPlayer1: \nPlayer2: \n......");

              //  JOptionPane.showMessageDialog(null, "----------Real Madrid Players----------\n" +
               //         ramos.toString() + benzema.toString() + hazard.toString + kroos.toString + bale.toString +
               //         courtois.toString());
                JOptionPane.showMessageDialog(null, "Real Madrid: \nPlayer1: \nPlayer2: \n......");
            }
        }
    }
}


