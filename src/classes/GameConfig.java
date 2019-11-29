package classes;

import java.util.ArrayList;

//This class was created to be able to use the same stadium/player/team objects in multiple classes ie StartPage and Game

public class GameConfig {
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Stadium> stadiums = new ArrayList<>();
    private ArrayList<Team> teams = new ArrayList<>();

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Stadium> getStadiums() {
        return stadiums;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void addStadiums(Stadium... stadiums) {                   //... any number of stadiums
        for(Stadium stadium: stadiums) {
            this.stadiums.add(stadium);
        }
    }

    public void addPlayers(Player player) {
        players.add(player);
    }

    public void addTeams(Team... teams) {
        for(Team team: teams) {
            this.teams.add(team);
        }
    }
}
