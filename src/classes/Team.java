package classes;

public class Team  {
    String name;
    Player players[] = new Player[4];
    Keeper keeper;

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
        return("\nTeam Name: " + getName() +
                "\n-----Keeper Info-----" +
                "\n" + getKeeper() +
               "\n-----Players Info-----" +
                "\n" + getPlayers());
    }//end toString()
}
