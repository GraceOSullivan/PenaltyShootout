package classes;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Player {
    private String name, position;
    private int pensTaken, pensScored;
    private GregorianCalendar dob;

    public Player(){
        this("Unknown", "Unknown", 0, 0, new GregorianCalendar(0, 0, 0));
    }

    public Player(String name, String position, int pensTaken, int pensScored, GregorianCalendar dob){
            setName(name);
            setPosition(position);
            setPensTaken(pensTaken);
            setPensScored(pensScored);
            setDob(dob);
    }

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
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public void setPensTaken(int pensTaken) {
        this.pensTaken = pensTaken;
    }

    public void setPensScored(int pensScored) {
        this.pensScored = pensScored;
    }

    public void setDob(GregorianCalendar dob) {
        this.dob = dob;
    }

    public String toString(){
        return ("\nPlayer Name: " + getName() +
                "\nPosition: " + getPosition() +
                "\nDOB: " + getDob() +
                "\nPenalties Taken: " + getPensTaken() +
                "\nPenalties Scored: " + getPensScored());
    }

}
