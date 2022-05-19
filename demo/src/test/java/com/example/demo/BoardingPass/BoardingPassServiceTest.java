package com.example.demo.BoardingPass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardingPassServiceTest {
    BoardingPassService boardingPassService;
    @BeforeEach
    void setUp()
    {
        boardingPassService = new BoardingPassService();
    }
    @Test
    void validateEmail() {
        assertEquals(true,boardingPassService.validateEmail("test@test.com"));
        assertEquals(true,boardingPassService.validateEmail("test@tester.test.com"));
        assertEquals(false,boardingPassService.validateEmail("incorrect"));
        assertEquals(false,boardingPassService.validateEmail("incorrect@incorrect..com"));
    }

    @Test
    void validateDate() {
        assertEquals(true,boardingPassService.validateDate("10-23-2022"));
        assertEquals(false,boardingPassService.validateDate("10/23/2022"));
        assertEquals(false,boardingPassService.validateDate("-1-23-2022"));
        assertEquals(false,boardingPassService.validateDate("20-23-2022"));
        assertEquals(false,boardingPassService.validateDate("2-230-2022"));
        assertEquals(false,boardingPassService.validateDate("2-23-2021"));


    }

    @Test
    void validatePhoneNumber() {
        assertEquals(true,boardingPassService.validatePhoneNumber("1231231234"));
        assertEquals(true,boardingPassService.validatePhoneNumber("(123) 123-1234"));
        assertEquals(true,boardingPassService.validatePhoneNumber("+1 (123) 123-1234"));
        assertEquals(false,boardingPassService.validatePhoneNumber("+1 (123) 123-12344"));
        assertEquals(false,boardingPassService.validatePhoneNumber("(123 123-1234"));
        assertEquals(false,boardingPassService.validatePhoneNumber("+122312344"));
    }

    @Test
    void validateTime() {
        assertEquals(true,boardingPassService.validateTime("0:59"));
        assertEquals(true,boardingPassService.validateTime("24:0"));
        assertEquals(true,boardingPassService.validateTime("12:30"));
        assertEquals(false,boardingPassService.validateTime("-1:59"));
        assertEquals(false,boardingPassService.validateTime("0:60"));
        assertEquals(false,boardingPassService.validateTime("25:59"));
    }

    @Test
    void makeBoardingPassFromString() {
        String line = "BoardingPass{name='Test', email='test@test.com', phoneNumber='777-777-7777', gender=Male," +
                " age=0, boardingPassNumber=1MQ8LU, date='2022-05-18', origin='DFW', destination='LAX'," +
                " departureTime='12:33', estimatedTimeOfArrival='15:33', ticketPrice=40.0}\n";
        BoardingPass pass = boardingPassService.makeBoardingPassFromString(line);
        assertEquals("Test",pass.getName());
        assertEquals("test@test.com",pass.getEmail());
        assertEquals("777-777-7777",pass.getPhoneNumber());
        assertEquals("Male",pass.getGender());
        assertEquals(0,pass.getAge());
        assertEquals("1MQ8LU",pass.getBoardingPassNumber());
        assertEquals("2022-05-18",pass.getDate());
        assertEquals("DFW",pass.getOrigin());
        assertEquals("LAX",pass.getDestination());
        assertEquals("12:33",pass.getDepartureTime());
        assertEquals("15:33",pass.getEstimatedTimeOfArrival());
        assertEquals(40.0,pass.getTicketPrice());
    }
}