package firstclass.secondaryclass;

import firstclass.School;


import java.util.ArrayList;

public class University extends School {
    public String name;
    public ArrayList<String> colleges;
    public University(ArrayList<String> colleges, String name){
        super();
        this.colleges=colleges;
        this.name=name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return  name;
    }
    public ArrayList getColleges(){
        return colleges;
    }
}
