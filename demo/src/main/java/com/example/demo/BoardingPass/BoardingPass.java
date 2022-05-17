package com.example.demo.BoardingPass;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

public class BoardingPass {
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private int age;
    private String boardingPassNumber;
    private String date;
    private String origin;
    private String destination;
    private String departureTime;
    private String estimatedTimeOfArrival;
    private double ticketPrice = 80.00; //arbitrary base price
    private Map<String, Double> locations = new HashMap<>(); //making double to use math.round

    public BoardingPass() {
    }

    public BoardingPass(String name,
                        String email,
                        String phoneNumber,
                        String gender,
                        int age,
                        String boardingPassNumber,
                        String date,
                        String origin,
                        String destination,
                        String departureTime,
                        double ticketPrice,
                        String estimatedTimeOfArrival) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
        this.boardingPassNumber = boardingPassNumber;
        this.date = date;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.ticketPrice = ticketPrice;
        this.estimatedTimeOfArrival = estimatedTimeOfArrival;

        locations.put("ATL", 0.0);
        locations.put("DFW", 800.0);
        locations.put("DEN", 1400.0);
        locations.put("ORD", 725.0);
        locations.put("LAX", 2100.0);
        locations.put("CLT", 240.0);
        locations.put("LAS", 2000.0);
        locations.put("PHX", 1850.0);
        locations.put("MCO", 450.0);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBoardingPassNumber() {
        return boardingPassNumber;
    }

    public void setBoardingPassNumber(String boardingPassNumber) {
        this.boardingPassNumber = boardingPassNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getEstimatedTimeOfArrival() {
        return estimatedTimeOfArrival;
    }

    public String calcETA(String origin, String destination){
        String eta ="";
        String key = origin;
        String find = destination;
        double base = 0.0;
        double dest = 0.0;
        int time;

        for(Map.Entry<String, Double> i: locations.entrySet()){
            if(i.getKey() == key){
                base = i.getValue();
            }
            if(i.getKey() == find){
                dest = i.getValue();
            }
        }

        time = (int) Math.round(Math.abs((dest - base) / 400)); //get mile difference between origin and destination divided by speed of plane to get eta
        eta = String.valueOf(time);
        String dep = getDepartureTime();

        List<Integer> temp = new ArrayList<>();
        temp = Arrays.stream(dep.split(":")).map(x-> Integer.parseInt(x)).collect(Collectors.toList());

        int digit = temp.get(0) + time;

        if(digit > 24){
            digit = digit % 24;//get remainder
        }

        eta = digit + ":" + temp.get(1); // should be outputting time of arrival based on when leaving airport

        return eta;
    }

    public void setEstimatedTimeOfArrival(String estimatedTimeOfArrival) {
        this.estimatedTimeOfArrival = estimatedTimeOfArrival;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calcTicketPrice(double ticketPrice, int age, String gender){
        if(age <= 12){
            ticketPrice = ticketPrice * 0.5;
        }
        else if(age >= 60){
            ticketPrice = ticketPrice * 0.4; //60% off
        }

        if(gender.charAt(0) == 'F' || gender.charAt(0) == 'f'){
            ticketPrice = ticketPrice * 0.75; //25% off
        }

        return ticketPrice;

    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "BoardingPass{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", boardingPassNumber=" + boardingPassNumber +
                ", date='" + date + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", estimatedTimeOfArrival='" + estimatedTimeOfArrival + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
    public void writeToFile()
    {
        try{
            FileWriter fw = new FileWriter("src/main/resources/RawBoardingPasses.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.toString());
            bw.newLine();
            bw.close();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
