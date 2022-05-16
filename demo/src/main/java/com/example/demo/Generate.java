package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class Generate {
    private HashMap<String, String> pass = new HashMap<>();
    private double price = 80.00; //base price
    private String eta = "";
    //create has-a relationship with input class -> Person person;

    // INITIAL CONSTRUCTOR -> public Generate(){} //this will populate data that is already on file instead of input

    public Generate(){ //constructor that takes in person input and outputs that info + ticket price + eta + boarding pass -> Generate(Person p) -> return person p, double, String
        String name = ""; //get name from input class (person.getName())
        String ID = chars();
        //this.person = person
        //setPrice(person);

        try {
            pass.put(name, ID); //add to database for write to file later on
        }catch (Exception e){
            e.printStackTrace();
        }

        //Person class info
        //Boarding pass number
        //ETA
        //Total Ticket Price
    }

    public String chars(){//generate pass ID
        String x = "";
        int roll;
        char sample = (char) ThreadLocalRandom.current().nextInt(65, 90); //convert int into letter
        char sampleNum = (char) ThreadLocalRandom.current().nextInt(48, 57); //convert int into number (ascii)

        for(int i = 0; i < 6; i++){
            roll = ThreadLocalRandom.current().nextInt(1,4);
            if(roll == 1) { //want more letters than numbers
                x += sampleNum;
            }else{
                x += sample;
            }
        }

        return x;
    }

    public HashMap<String, String> getPass() {
        return pass;
    }

    public void setPass(HashMap<String, String> pass) {
        this.pass = pass;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) { //get destination, origin, and age - calculate price (double price, int age, String dest, String origin, String gender)
        int age = 5;
        String gender = "male";

        if(age <= 12){
            price = price * 0.5;
        }
        else if(age >= 60){
            price = price * 0.4; //60% off
        }

        if(gender.contains("female")){
            price = price * 0.75; //25% off
        }

        this.price = price;
    }

    public String getEta() {
        return eta;
    }

    String calcETA(String origin, String destination){
        int hours = ThreadLocalRandom.current().nextInt(5, 9);
        int minutes = ThreadLocalRandom.current().nextInt(01, 59);


        String e = hours + " hours and " + minutes + " minutes.";
        return e;
    }

    public void setEta(String est) {
        String departure = "3:55";
        String origin = "LA";
        String destination = "ATL";
        est = calcETA(origin, destination);
        this.eta = est;
    }
}
