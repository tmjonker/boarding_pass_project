package com.example.demo.BoardingPass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardingPassTest {
    BoardingPass boardingPass;
    @BeforeEach
    void setUp(){
        boardingPass = new BoardingPass("Name",
                "email@rmail.com",
                "123-123-1234",
                "Male",
                20,
                "test12",
                "5-22-2022",
                "LAX",
                "MCO",
                "0:00");
    }

    @Test
    void calcETA() {
        boardingPass.setDepartureTime("00:00");
        assertEquals("04:00",boardingPass.calcETA("LAX","MCO"));
        assertEquals("04:00",boardingPass.calcETA("MCO","LAX"));
        assertEquals("02:00",boardingPass.calcETA("LAX","DEN"));
        assertEquals("03:00",boardingPass.calcETA("ORD","LAX"));
        assertEquals("00:00",boardingPass.calcETA("LAX","LAX"));
    }

    @Test
    void calcTicketPrice() {
        assertEquals(100,boardingPass.calcTicketPrice(100,30,"M"));
        assertEquals(50,boardingPass.calcTicketPrice(100,12,"M"));
        assertEquals(37.5,boardingPass.calcTicketPrice(100,12,"F"));
        assertEquals(40,boardingPass.calcTicketPrice(100,100,"M"));
        assertEquals(30,boardingPass.calcTicketPrice(100,100,"F"));
    }
}