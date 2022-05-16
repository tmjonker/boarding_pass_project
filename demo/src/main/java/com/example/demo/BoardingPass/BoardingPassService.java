package com.example.demo.BoardingPass;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BoardingPassService {
    private int idCount;
    private ArrayList<BoardingPass> passes;
    public BoardingPassService() {
        idCount = 0;
        passes = new ArrayList<>();
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
        Pattern pat = Pattern.compile("(0/91)?[7-9][0-9]{9}");
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


        char gender =' ';

        System.out.println("Please enter your gender F for female, M for male");
        do{
            try {
                gender=scan.next().charAt(0);
                if(gender!='F'&&gender!='M') {
                    throw new RuntimeException("Unexpected input");
                }
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            gender = Character.toUpperCase(gender);
        }while(gender !='F' && gender!='M');



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
                ++idCount,
                date,
                origin,
                destination,
                departureTime));
    }
    public BoardingPass findByBoardingPassNumber(int number)
    {
        for(var pass : passes)
        {
            if(pass.getBoardingPassNumber()==number)
            {
                return pass;
            }
        }
        return null;
    }
}
