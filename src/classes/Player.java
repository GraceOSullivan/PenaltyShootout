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
     * Accessor Method
     * @return copy of Players name
     */
    public String getName(){
        return name;
    }

    /**
     * Accessor Method
     * @return copy of Players position
     */
    public String getPosition(){
        return position;
    }

    /**
     * Accessor Method
     * @return copy of amount of penalties a Player has taken
     */
    public int getPensTaken() {
        return pensTaken;
    }

    /**
     * Accessor Method
     * @return copy of amount of penalties a Player has scored
     */
    public int getPensScored() {
        return pensScored;
    }

    /**
     * Accessor Method
     * @return copy of amount of Players date of birth
     */
    public String getDob() {
        return dob.get(GregorianCalendar.DATE) + "/" + dob.get(GregorianCalendar.MONTH) + "/" + dob.get(GregorianCalendar.YEAR);
    } //end accessor methods

    /**
     * Mutator method changes the value of attribute
     * @param name is Players first and last name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Mutator method changes the value of attribute
     * @param position is Players position on field
     */
    public void setPosition(String position){
        this.position = position;
    }

    /**
     * Mutator method changes the value of attribute
     * @param pensTaken is amount of penalties a Player has taken in their career
     */
    public void setPensTaken(int pensTaken) {
        this.pensTaken = pensTaken;
    }

    /**
     * Mutator method changes the value of attribute
     *  @param pensScored is amount of penalties a Player has scored in their career
     */
    public void setPensScored(int pensScored) {
        this.pensScored = pensScored;
    }

    /**
     * Mutator method changes the value of attribute
     * @param dob is a GregorianCalendar object representing Players date of birth
     */
    public void setDob(GregorianCalendar dob) {
        this.dob = dob;
    }//end mutator methods

    /**
     * String summary of Player object                                  // XDrive/JohnW/OOP2019/SampleCode5 Person.javA
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
