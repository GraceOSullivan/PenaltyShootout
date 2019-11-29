package classes;

import java.util.Arrays;

public class Team  {
    private String name;
    private Player[] players = new Player[4];
    private Keeper keeper;

    public Team(){
        this("Unknown", new Player[4], new Keeper());
    }//end no-argument constructor

    public Team(String name, Player[] players, Keeper keeper){
        setName(name);
        setPlayers(players);
        setKeeper(keeper);
    }//end full-argument constructor

    public String getName() {
        return name;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Keeper getKeeper(){
        return keeper;
    }//end accessor methods

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void setKeeper(Keeper keeper){
        this.keeper = keeper;
    }//end mutator methods

    public String toString(){
        String playerArray = Arrays.toString(getPlayers());
        String formattedPlayerArray = playerArray.toString()                //to remove [] amd commas when printing thr array
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();           //remove trailing spaces from partially initialized arrays
        return("\n--------------" +  getName() + "--------------" +
                "\n\n---Players---" +
                "\n" + formattedPlayerArray +
                "\n\n---Keeper---" +
                 getKeeper());
    }//end toString()
}
