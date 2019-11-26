package classes;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Keeper {
    private String name;
    private int pensSaved, pensMissed;
    private GregorianCalendar dob;

    public Keeper(){
        this("Unknown", 0, 0, new GregorianCalendar());
    }//end no-argument constructor

    public Keeper(String name, int pensSaved, int pensMissed, GregorianCalendar dob){
        setName(name);
        setPensSaved(pensSaved);
        setPensMissed(pensMissed);
        setDob(dob);
    }//end full-argument constructor

    public String getName(){
        return name;
    }

    public int getPensSaved() {
        return pensSaved;
    }

    public int getPensMissed(){
        return pensMissed;
    }

    public Calendar getDob(){
        return dob;
    }//end accessor methods

    public void setName(String name){
        this.name = name;
    }

    public void setPensSaved(int pensSaved){
        this.pensSaved = pensSaved;
    }

    public void setPensMissed(int pensMissed){
        this.pensMissed = pensMissed;
    }

    public void setDob(GregorianCalendar dob){
        this.dob = dob;
    }//end mutator methods

    public String toString(){
        return ("\nKeeper Name: " + getName() +
                "\nDOB: " + getDob() +
                "\nPenalties Saved: " + getPensSaved() +
                "\nPenalties Missed: " + getPensMissed());
    }//end toString()
}
