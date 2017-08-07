package kz.platonus.task1.format;

import kz.platonus.task1.interfaces.Format;

public class XMLFormat implements Format {

    private double sumOfAreas;

    public XMLFormat(){}

    public XMLFormat(double sumOfAreas){
        this.sumOfAreas = sumOfAreas;
    }

    @Override
    public String toString() {
        return "<sumOfAreas> "+sumOfAreas+" </sumOfAreas>";
    }

}
