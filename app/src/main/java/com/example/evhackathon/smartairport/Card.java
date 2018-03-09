package com.example.evhackathon.smartairport;

/**
 * Created by Varsha on 3/9/2018.
 */

public class Card {

    private String name;
    private String details;
    private String type;

    private static int ctr = 1;

    public Card() {
        this.name = "Lounge " + ctr;
        this.details = "First Floor";
        this.type = "Visa";
        ctr++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
