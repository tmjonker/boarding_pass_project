package com.example.demo.BoardingPass;

public class BoardingPassApp {
    public static void main(String[] args) {
        BoardingPassService boardingPassService = new BoardingPassService();
        boardingPassService.addPass("LAX");
        System.out.println(boardingPassService.findByBoardingPassNumber("XFS8KL"));
    }
}