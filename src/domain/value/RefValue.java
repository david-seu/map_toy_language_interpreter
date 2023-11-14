package src.domain.value;

import src.domain.type.RefType;
import src.domain.type.Type;

public class RefValue implements Value{
    Integer address;
    Type locationType;


    public RefValue(int address, Type locationType){
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress(){
        return address;
    }

    public Type getType(){
        return new RefType(locationType);
    }

    public Object getVal(){

    }

    public String toString(){
        return "(" + Integer.toString(address) + ", " + locationType.toString() + ")";
    }

}
