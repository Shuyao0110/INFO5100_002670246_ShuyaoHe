package firstclass.secondaryclass;

import firstclass.Venue;

public class Theater extends Venue {
    private String name;
    public int roomAmount;
    public Theater(int area, int floors, String location) {
        super(area, floors, location);
    }
    public Theater(int area, int floors, String location,String name){
        super(area,floors,location);
        this.name=name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
