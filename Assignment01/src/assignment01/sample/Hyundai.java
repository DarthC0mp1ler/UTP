package assignment01.sample;

import assignment01.IAggregable;
import assignment01.IDeeplyCloneable;

public class Hyundai implements IAggregable<Hyundai, String>, IDeeplyCloneable<Hyundai> {

    private String serial_number;

    public Hyundai() { }

    public Hyundai(String serial_number){
        this.serial_number = serial_number;
    }

    public String getSerial_number(){
        return serial_number;
    }

    @Override
    public String aggregate(String intermediateResult) {
        if(intermediateResult == null){
            return serial_number;
        }
        return serial_number + " : " + intermediateResult;
    }

    @Override
    public Hyundai deepClone() {
        Hyundai clone = new Hyundai();
        clone.serial_number = serial_number;
        return clone;
    }
}
