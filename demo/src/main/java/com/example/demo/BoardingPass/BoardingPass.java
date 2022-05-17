package com.example.demo.BoardingPass;



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
                        String departureTime) {
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

    /*
    public String calcETA(){

    }
     */
    public void setEstimatedTimeOfArrival(String estimatedTimeOfArrival) {
        this.estimatedTimeOfArrival = estimatedTimeOfArrival;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calcTicketPrice(double ticketPrice, int age, char gender){
        if(age <= 12){
            ticketPrice = ticketPrice * 0.5;
        }
        else if(age >= 60){
            ticketPrice = ticketPrice * 0.4; //60% off
        }

        if(gender == 'F' || gender == 'f'){
            ticketPrice = ticketPrice * 0.75; //25% off
        }

        return ticketPrice;

    }

    public void setTicketPrice(double ticketPrice) {
        ticketPrice = calcTicketPrice(getTicketPrice(), getAge(), getGender().charAt(0));
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
}
