package firstclass.secondaryclass;

import firstclass.School;

public class Kindergarten extends School {
    public String classification;
    private boolean hasPlayground=true;
    public Kindergarten(int area, int floors, String location,String name){
        super(area,floors,location,name);
        System.out.println("Creation of Kindergarten.");
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getClassification() {
        return classification;
    }
    public void setOwner(String owner){
        super.owner=owner;
    }
    @Override
    public String getOwner() {
        return super.getOwner();
    }
}
