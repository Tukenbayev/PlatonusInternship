package kz.platonus.task1.format;

import kz.platonus.task1.interfaces.Format;

public class JSONFormat implements Format {

    private double sumOfAreas;

    public JSONFormat(double sumOfAreas){
        this.sumOfAreas = sumOfAreas;
    }

    @Override
    public String toString() {
        return "JSONFormat{" +
                "sumOfAreas: " + sumOfAreas +
                " }";
    }

}
