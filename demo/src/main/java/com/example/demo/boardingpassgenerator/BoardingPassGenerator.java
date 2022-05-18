package com.example.demo.boardingpassgenerator;

import com.example.demo.BoardingPass.BoardingPass;
import com.example.demo.barcodegenerator.BarcodeGenerator;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class BoardingPassGenerator {

    BoardingPass boardingPass;

    public BoardingPassGenerator(BoardingPass boardingPass) {

        this.boardingPass = boardingPass;
    }

    public void generatePdf() {

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("boarding-pass.pdf"));
        } catch (Exception ex) {

            ex.printStackTrace();
        }

        document.open();

        Paragraph name = new Paragraph("Name: " + boardingPass.getName()+ "\n");
        Paragraph email = new Paragraph("Email Address: " + boardingPass.getEmail()+ "\n");
        Paragraph phone = new Paragraph("Phone Number: " + boardingPass.getPhoneNumber()+ "\n");
        Paragraph gender = new Paragraph("Gender: " + boardingPass.getGender()+ "\n");
        Paragraph age = new Paragraph("Age: " + Integer.toString(boardingPass.getAge())+ "\n\n");

        Paragraph bpn = new Paragraph("Boarding Pass Number: " + boardingPass.getBoardingPassNumber()+ "\n");
        Paragraph date = new Paragraph("Departure Date: " + boardingPass.getDate()+ "\n");
        Paragraph origin = new Paragraph("Origin: " + boardingPass.getOrigin()+ "\n");
        Paragraph destination = new Paragraph("Destination: " + boardingPass.getDestination()+ "\n");
        Paragraph eta = new Paragraph("ETA: " + boardingPass.getEstimatedTimeOfArrival()+ "\n");
        Paragraph time = new Paragraph("Departure Time: " + boardingPass.getDepartureTime()+ "\n\n");

        Paragraph price = new Paragraph("Ticket Price: " + Double.toString(boardingPass.getTicketPrice()));

        BufferedImage bi = null;
        try {
            bi = BarcodeGenerator.generateEAN13BarcodeImage(boardingPass.getBoardingPassNumber());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Image image = null;

        try {
            ImageIO.write(bi, "png", baos);
            image = Image.getInstance(baos.toByteArray());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            document.add(image);
            document.add(name);
            document.add(email);
            document.add(phone);
            document.add(gender);
            document.add(age);
            document.add(bpn);
            document.add(date);
            document.add(origin);
            document.add(destination);
            document.add(eta);
            document.add(time);
            document.add(price);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        document.close();
    }


}