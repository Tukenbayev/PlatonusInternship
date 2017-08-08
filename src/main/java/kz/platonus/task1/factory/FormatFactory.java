package kz.platonus.task1.factory;


import kz.platonus.task1.format.HTMLFormat;
import kz.platonus.task1.format.JSONFormat;
import kz.platonus.task1.format.XMLFormat;
import kz.platonus.task1.interfaces.Format;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public class FormatFactory {

    public static Supplier<Format> getFormatSupplier(String format, double sumOfAreas) throws ClassNotFoundException{

        Supplier<Format> formatSupplier = null;

        switch (format.toUpperCase()){
            case "HTML":
                formatSupplier = () -> new HTMLFormat(sumOfAreas);
                break;
            case "JSON":
                formatSupplier = () -> new JSONFormat(sumOfAreas);
                break;
            case "XML":
                formatSupplier = () -> new XMLFormat(sumOfAreas);
                break;
            default:
                throw new ClassNotFoundException();
        }



        return formatSupplier;
    }
}
