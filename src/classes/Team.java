package classes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Team  {
    String name;
    ArrayList<Player> players;

    public Team(){
        this("Unknown", new ArrayList<>());
    }

    public Team(String name, ArrayList<Player> players){
        setName(name);
        setPlayers(players);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String toString(){
        return("\nTeam Name: " + getName() +
               "\n-----Players Info-----" +
                "\n" + players);
    }
}
