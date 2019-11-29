package classes;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * An instantiable class which models one Player, storing
 * all that players details as one Player object
 * @author Grace O'Sullivan
 */
public class Player {
    private String name, position;
    private int pensTaken, pensScored;
    private GregorianCalendar dob;

    /**
     * No-argument constructor creates a default Player  ie Player p = new Player();
     */
    public Player(){
        this("Unknown", "Unknown", 0, 0, new GregorianCalendar());
    }

    /**
     * Full-argument constructor to create a Player where all attributes are known
     */
    public Player(String name, String position, int pensTaken, int pensScored, GregorianCalendar dob){
            setName(name);
            setPosition(position);
            setPensTaken(pensTaken);
            setPensScored(pensScored);
            setDob(dob);
    }

    /**
     * returns the first and last name of Player
     * @return copy of Players first and last name
     */
    public String getName(){
        return name;
    }

    /**
     * returns the position of Player
     * @return copy of Players position
     */
    public String getPosition(){
        return position;
    }

    /**
     * returns the number of penalties taken by Player
     * @return copy of number of penalties a Player has taken
     */
    public int getPensTaken() {
        return pensTaken;
    }

    /**
     * returns the number of penalties scored by Player
     * @return copy of number of penalties a Player has scored
     */
    public int getPensScored() {
        return pensScored;
    }

    /**
     * returns the date of birth of Player
     * @return copy of Players date of birth
     */
    public String getDob() {
        return dob.get(GregorianCalendar.DATE) + "/" + dob.get(GregorianCalendar.MONTH) + "/" + dob.get(GregorianCalendar.YEAR);
    } //end accessor methods

    /**
     * sets name to a Players first and last name
     * @param name is Players first and last name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * sets position to a Players position on field
     * @param position is Players position on field
     */
    public void setPosition(String position){
        this.position = position;
    }

    /**
     * sets pensTaken to number of penalties a Player has taken in their career
     * @param pensTaken is amount of penalties a Player has taken in their career
     */
    public void setPensTaken(int pensTaken) {
        this.pensTaken = pensTaken;
    }

    /**
     * sets pensScored to number of penalties a Player has scored in their career
     *  @param pensScored is amount of penalties a Player has scored in their career
     */
    public void setPensScored(int pensScored) {
        this.pensScored = pensScored;
    }

    /**
     * sets dob to Players date of birth
     * @param dob is a GregorianCalendar object representing Players date of birth
     */
    public void setDob(GregorianCalendar dob) {
        this.dob = dob;
    }//end mutator methods

    /**
     * returns a String summary of Player object
     * @return values of Players attributes
     */
    public String toString(){
        return ("\nName: " + getName() +
                "\nPosition: " + getPosition() +
                "\nDOB: " + getDob() +
                "\nPenalties Taken: " + getPensTaken() +
                "\nPenalties Scored: " + getPensScored() + "\n");
    }

}
