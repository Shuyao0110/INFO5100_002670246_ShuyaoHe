package firstclass.secondaryclass;

import firstclass.School;

public class TeachingBuilding extends School {
    public String name;
    public int roomAmount;
    public TeachingBuilding(String name,int roomAmount){
        this.name=name;
        this.roomAmount=roomAmount;
    }
    public TeachingBuilding(int area, int floors, String location) {
        super(area, floors, location);
    }

    @Override
    public String getName() {
        return name;
    }
}
