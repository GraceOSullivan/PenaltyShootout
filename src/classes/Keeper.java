package classes;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Keeper {
    private String name;
    private int totalPens, pensSaved;
    private GregorianCalendar dob;

    public Keeper(){
        this("Unknown", 0, 0, new GregorianCalendar());
    }//end no-argument constructor

    public Keeper(String name, int totalPens, int pensSaved, GregorianCalendar dob){
        setName(name);
        setPensSaved(pensSaved);
        setTotalPens(totalPens);
        setDob(dob);
    }//end full-argument constructor

    public String getName(){
        return name;
    }

    public int getTotalPens(){
        return totalPens;
    }

    public int getPensSaved() {
        return pensSaved;
    }

    public String getDob(){
        return dob.get(GregorianCalendar.DATE) + "/" + dob.get(GregorianCalendar.MONTH) + "/" + dob.get(GregorianCalendar.YEAR);
    }//end accessor methods

    public void setName(String name){
        this.name = name;
    }

    public void setPensSaved(int pensSaved){
        this.pensSaved = pensSaved;
    }

    public void setTotalPens(int totalPens){
        this.totalPens = totalPens;
    }

    public void setDob(GregorianCalendar dob){
        this.dob = dob;
    }//end mutator methods

    public String toString(){
        return ("\nName: " + getName() +
                "\nDOB: " + getDob() +
                "\nTotal Penalties: " + getTotalPens() +
                "\nPenalties Saved: " + getPensSaved());
    }//end toString()
}
