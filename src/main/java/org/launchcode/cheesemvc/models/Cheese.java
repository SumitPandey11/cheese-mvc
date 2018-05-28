package org.launchcode.cheesemvc.models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull  // Validation
    @Size(min=1 , message="Description must not be empty")
    private String description ;

    private CheeseType type;

    @NotNull(message="Rating must not be empty, It must between 1 and 5")
    @Range(min=1,max=5, message="Rating must be between 1 and 5")
    private int rating;

    /*private int cheeseId;
    private static int nextId = 1;*/

    public Cheese() {
        /*cheeseId = nextId;
        nextId++;*/
    }

    public Cheese(String name, String description) {
        //this(); // call the default constructor for this class.
        this.name = name;
        this.description = description;
    }

    /*public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }*/

    public int getId() {
        return id;
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

    public CheeseType getType() {
        return type;
    }

    public void setType(CheeseType type) {
        this.type = type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
