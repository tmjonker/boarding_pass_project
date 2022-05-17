package com.example.demo.BoardingPass;

import java.util.Scanner;

public class BoardingPassApp {
    public static void main(String[] args) {
        BoardingPassService boardingPassService = new BoardingPassService();
        //boardingPassService.addPass("LAX");
        //System.out.println(boardingPassService.findByBoardingPassNumber("XFS8KL"));
        Scanner scan =  new Scanner(System.in);
        char addNewPerson;
        do {

            boardingPassService.addPass("LAX");
            System.out.println("Add another person? y/n");
            addNewPerson = scan.nextLine().charAt(0);
        }while(Character.toLowerCase(addNewPerson)=='y');
        for(var pass: boardingPassService.getPasses())
        {
            pass.writeToFile();
        }
        System.out.println(boardingPassService.searchFileForPass("TKE50M"));
        System.out.println(boardingPassService.searchFileForPass("GVKW4R"));
        System.out.println(boardingPassService.searchFileForPass("7FCA6Q"));
    }
}