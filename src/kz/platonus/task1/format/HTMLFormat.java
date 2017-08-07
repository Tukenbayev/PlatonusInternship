package kz.platonus.task1.format;

import kz.platonus.task1.interfaces.Format;

public class HTMLFormat implements Format {

    private double sumOfAreas;

    public HTMLFormat(){}

    public HTMLFormat(double sumOfAreas){
        this.sumOfAreas = sumOfAreas;
    }

    @Override
    public String toString() {
        return "<html> \n" +
                "  <head>\n   </head>\n" +
                "   <body>\n" +
                "       <span>Sum of Areas = " + sumOfAreas+"</span>\n" +
                "   </body>\n" +
                "</html>";
    }

}
