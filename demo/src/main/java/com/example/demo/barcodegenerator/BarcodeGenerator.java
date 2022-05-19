package com.example.demo.barcodegenerator;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import java.awt.image.BufferedImage;

public class BarcodeGenerator {

    public static BufferedImage generateCode128BarcodeImage(String barcodeText) throws Exception {
        Barcode barcode = BarcodeFactory.createCode128(barcodeText);

        return BarcodeImageHandler.getImage(barcode);
    }
}
