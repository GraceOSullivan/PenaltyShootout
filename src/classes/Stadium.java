package classes;

public class Stadium {
    private String name;
    private int capacity;

    public Stadium(){
        this("Unknown", 0);
    }//end no-argument constructor

    public Stadium(String name, int capacity){
        setName(name);
        setCapacity(capacity);
    }//end full-argument constructor

    public String getName(){
        return name;
    }

    public int getCapacity(){
        return capacity;
    }//end accessor methods

    public void setName(String name){
        this.name = name;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }//end mutator methods

    public String toString(){
        return("\nStadium name: " + getName() +
                "\nCapacity: " + getCapacity());
    }//end toString()
}
