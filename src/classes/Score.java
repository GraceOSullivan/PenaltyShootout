package classes;

import java.io.Serializable;

public class Score implements Serializable {
    public Score() {
    }

    private String team1Name;
    private int team1Score;
    private String team2Name;
    private int team2Score;

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(int team1Score) {
        this.team1Score = team1Score;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(int team2Score) {
        this.team2Score = team2Score;
    }

    public String toString() {
        return "------Score-----" +
                getTeam1Name() + " " + getTeam1Score() +
                "\t" + getTeam2Name() + getTeam2Score();
    }
}
