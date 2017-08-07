package kz.platonus.task1.service;

import kz.platonus.task1.factory.FormatFactory;
import kz.platonus.task1.interfaces.Format;

import java.util.function.Supplier;

public class Printer {

    public static void printSumInFormat(String format, double sumOfAreas){

        Supplier<Format> formatSupplier = null;
        try {
            formatSupplier = FormatFactory.getFormatSupplier(format, sumOfAreas);
        } catch (ClassNotFoundException e) {
            System.err.println("No such format");
        }

        if (formatSupplier != null){
            System.out.println(formatSupplier.get());
        }
    }
}
