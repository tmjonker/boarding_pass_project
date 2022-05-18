package com.example.demo.BoardingPass;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class BoardingPassService extends BoardingPass{
    private int idCount;
    private ArrayList<BoardingPass> passes;
    private String check;
    public BoardingPassService() {
        idCount = 0;
        passes = new ArrayList<>();
    }

    public BoardingPass searchFileForPass(String targetBoardingPassNumber)
    {
        ArrayList<String> lines;
        try
        {
            lines = (ArrayList<String>) Files.readAllLines(Paths.get("src/main/resources/RawBoardingPasses.txt"));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        for(var line : lines)
        {
            if(line.contains("boardingPassNumber="+targetBoardingPassNumber))
            {
                return makeBoardingPassFromString(line);
            }
        }
        return null;
    }
    public BoardingPass makeBoardingPassFromString(String line)
    {
        BoardingPass ret = new BoardingPass();
        String filteredLine = line.substring(13,line.length()-2);
        String[] fields = filteredLine.split(", ");

        for(var field: fields)
        {
            String[] temp = field.split("=");
            String removedMarks="";
            for(int i=0;i<temp[1].length();i++)
            {
                if(temp[1].charAt(i)!='\'')
                {
                    removedMarks+=temp[1].charAt(i);
                }
            }
            temp[1] = removedMarks;
            switch (temp[0]){
                case "name":
                    ret.setName(temp[1]);
                    break;
                case "email":
                    ret.setEmail(temp[1]);
                    break;
                case "phoneNumber":
                    ret.setPhoneNumber(temp[1]);
                    break;
                case "gender":
                    ret.setGender(temp[1]);
                    break;
                case "age":
                    ret.setAge(Integer.parseInt(temp[1]));
                    break;
                case "boardingPassNumber":
                    ret.setBoardingPassNumber(temp[1]);
                    break;
                case "date":
                    ret.setDate(temp[1]);
                    break;
                case "origin":
                    ret.setOrigin(temp[1]);
                    break;
                case "destination":
                    ret.setDestination(temp[1]);
                    break;
                case "departureTime":
                    ret.setDepartureTime(temp[1]);
                    break;
                case "estimatedTimeOfArrival":
                    ret.setEstimatedTimeOfArrival(temp[1]);
                    break;
                case "ticketPrice":
                    ret.setTicketPrice(Double.parseDouble(temp[1]));
                    break;
            }
        }
        return ret;
    }
    public boolean validateEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public boolean validateDate(String date)
    {
        int[] maxDays = {31,28,31,30,31,30,31,31,30,31,30,31};
        String[] arr = date.split("-");
        if(arr.length!=3) {
            return false;
        }
        if(Integer.parseInt(arr[0])<=0 || Integer.parseInt(arr[0])>12)
        {
            return false;
        }
        if(Integer.parseInt(arr[1])<=0||Integer.parseInt(arr[1])>maxDays[Integer.parseInt(arr[0])-1])
        {
            return false;
        }
        if(Integer.parseInt(arr[2])<2022)
        {
            return false;
        }
        return true;
    }
    public boolean validatePhoneNumber(String phoneNumber)
    {
        String patterns
                = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
        Pattern pat = Pattern.compile(patterns);
        return pat.matcher(phoneNumber).matches();
    }

    public boolean validateTime(String time)
    {
        String[] firstSplit= time.split(":");
        if(firstSplit.length!=2)
        {
            return false;
        }
        if(Integer.parseInt(firstSplit[0])<=0 || Integer.parseInt(firstSplit[0])>12)
        {
            return false;
        }
        String[] secondSplit = firstSplit[1].split(" ");
        if(secondSplit.length!=2)
        {
            return false;
        }
        if(Integer.parseInt(secondSplit[0])<0 || Integer.parseInt(firstSplit[0])>59)
        {
            return false;
        }
        if(!secondSplit[1].equals("AM") && !secondSplit[1].equals("PM"))
        {
            return false;
        }
        return true;
    }
    public void addPass(String origin)
    {
        String name ="";
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter your name");
        do{
            try {
                name = scan.nextLine();
                if(name.equals("")) {
                    throw new RuntimeException("Please enter a name");
                }
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }while(name.equals(""));



        String email ="";

        System.out.println("Please enter your email");
        do{
            try {
                email = scan.nextLine();
                if(email.equals("")|| !validateEmail(email)) {

                    throw new RuntimeException("Please enter a valid email");
                }
            }catch (Exception e)
            {
                email="";
                System.out.println(e.getMessage());
            }
        }while(email.equals(""));


        String phoneNumber ="";

        System.out.println("Please enter your phone number (numbers only)");
        do{
            try {
                phoneNumber = scan.nextLine();
                if(phoneNumber.equals("")|| !validatePhoneNumber(phoneNumber)) {

                    throw new RuntimeException("Please enter a valid phone number");
                }
            }catch (Exception e)
            {
                phoneNumber="";
                System.out.println(e.getMessage());
            }
        }while(phoneNumber.equals(""));


        String gender ="";

        System.out.println("Please enter your gender F for female, M for male");
        do{
            try {
                gender=scan.nextLine();
                if(!gender.equalsIgnoreCase("MALE")&&!gender.toUpperCase().equals("FEMALE")) {
                    throw new RuntimeException("Unexpected input");
                }
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }while(!gender.equalsIgnoreCase("MALE") &&!gender.equalsIgnoreCase("FEMALE"));



        int age=-1;

        System.out.println("Please enter your age");
        do{
            try {
                age=scan.nextInt();
                if(age<0||age>120) {

                    throw new RuntimeException("Enter a valid age");
                }
            }catch (Exception e)
            {
                age=-1;
                System.out.println(e.getMessage());
            }
        }while(age==-1);


        scan.nextLine();
        String date ="";

        System.out.println("Please enter the date in MM-DD-YYYY format");
        do{
            try {
                date = scan.nextLine();
                if(validateDate(date)==false) {

                    throw new RuntimeException("Please enter a date");
                }
            }catch (Exception e)
            {
                date="";
                System.out.println(e.getMessage());
            }
        }while(date.equals(""));
        String destination ="";

        System.out.println("Please enter your Destination");
        do{
            try {
                destination = scan.nextLine();
                if(destination.equals("")) {

                    throw new RuntimeException("Please enter a destination");
                }
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }while(destination.equals(""));
        String departureTime ="";

        System.out.println("Please enter your departure time. Ex. 9:30 PM");
        do{
            try {
                departureTime = scan.nextLine();
                if(!validateTime(departureTime)) {

                    throw new RuntimeException("Please enter a departure time");
                }
            }catch (Exception e)
            {
                departureTime="";
                System.out.println(e.getMessage());
            }
        }while(departureTime.equals(""));
        passes.add(new BoardingPass(name,
                email,
                phoneNumber,
                gender,
                age,
                chars(),
                date,
                origin,
                destination,
                departureTime));
    }

    public String check(ArrayList<BoardingPass> x, String y){//used to check if ID is taken
        //quick test case delete later
//        String nameS = "y";
//        String emailS = "t@gmail.com";
//        String phoneNumberS = "8880344456";
//        String genderS = "F";
//        int ageS = 20;
//        String boardingPassNumberS = "JQWX23";
//        String dateS = "5-20-2022";
//        String originS = "LAX";
//        String destinationS = "ATL";
//        String departureTimeS = "9:30 PM";
//        String eta = getEstimatedTimeOfArrival();
//        //passes.add(new BoardingPass(nameS, emailS, phoneNumberS, genderS, ageS, boardingPassNumberS, dateS, originS, destinationS, departureTimeS));

        for(int i = 0; i < x.size(); i++){
            if(passes.get(i).getBoardingPassNumber() == y){
                y = ""; //clear, change, and return new set
                y = chars();
                break;
            }
            else{
                System.out.println("No Match");
            }
        }

        return y;
    }

    public String chars(){//generate pass 6 character ID with letters and numbers
        String x = "";
        int roll;
        char sample = ' ';
        char sampleNum = ' ';

        for(int i = 0; i < 6; i++){
            roll = ThreadLocalRandom.current().nextInt(1,6);
            sample = (char) ThreadLocalRandom.current().nextInt(65, 90); //convert int into letter
            sampleNum = (char) ThreadLocalRandom.current().nextInt(48, 57); //convert int into number (ascii)

            if(roll == 1) { //want more letters than numbers
                x += sampleNum;
            }else{
                x += sample;
            }
        }

        x = check(passes, x);

        return x;
    }

    public BoardingPass findByBoardingPassNumber(String number)
    {
        System.out.println(passes.get(0));
        for(var pass : passes)
        {
            if(pass.getBoardingPassNumber().contains(number))
            {
                return pass;
            }
        }
        return null;
    }

    public ArrayList<BoardingPass> getPasses() {
        return passes;
    }
}
