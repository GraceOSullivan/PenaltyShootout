package classes;
/*
 * An instantiable class which models one Player, storing
 *  all that players details as one Player object
 */

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Represents a single Player within a team
 */
public class Player {
    private String name, position;
    private int pensTaken, pensScored;
    private GregorianCalendar dob;

    /*
     *No-argument constructor creates a default Player
     */
    public Player(){
        this("Unknown", "Unknown", 0, 0, new GregorianCalendar());
    }

    /*
     *Full-argument constructor to create a Player where all attributes are known
     */
    public Player(String name, String position, int pensTaken, int pensScored, GregorianCalendar dob){
            setName(name);
            setPosition(position);
            setPensTaken(pensTaken);
            setPensScored(pensScored);
            setDob(dob);
    }

    /*
     *Accessor methods
     * @return copy of the attribute                        //http://www.drjava.org/docs/user/ch10.html
     */
    public String getName(){
        return name;
    }

    public String getPosition(){
        return position;
    }

    public int getPensTaken() {
        return pensTaken;
    }

    public int getPensScored() {
        return pensScored;
    }

    public Calendar getDob() {
        return dob;
    } //end accessor methods

    /*
     *Mutator methods
     *Changes the value of attribute
     */
    //@param name is Players first and last name
    public void setName(String name){
        this.name = name;
    }

    //@param position is Players position on field
    public void setPosition(String position){
        this.position = position;
    }

    //@param pensTaken is amount of penalties a Player has taken in their career
    public void setPensTaken(int pensTaken) {
        this.pensTaken = pensTaken;
    }

    //@param pensScored is amount of penalties a Player has scored in their career
    public void setPensScored(int pensScored) {
        this.pensScored = pensScored;
    }

    //@param dob is a GregorianCalendar object representing Players date of birth
    public void setDob(GregorianCalendar dob) {
        this.dob = dob;
    }//end mutator methods

    /*
     *String summary of Player object                                  // XDrive/JohnW/OOP2019/SampleCode5 Person.java
     * @return values of Players attributes
     */
    public String toString(){
        return ("\nPlayer Name: " + getName() +
                "\nPosition: " + getPosition() +
                "\nDOB: " + getDob() +
                "\nPenalties Taken: " + getPensTaken() +
                "\nPenalties Scored: " + getPensScored());
    }

}
