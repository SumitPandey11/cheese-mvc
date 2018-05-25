package org.launchcode.cheesemvc.models;

public class Cheese {

    private String name;
    private String description ;
    private int cheeseId;
    private static int nextId = 1;

    public Cheese() {
        cheeseId = nextId;
        nextId++;
    }

    public Cheese(String name, String description) {
        this(); // call the default constructor for this class.
        this.name = name;
        this.description = description;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
