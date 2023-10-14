package firstclass.secondaryclass;

import firstclass.Venue;

public class Gymnasium extends Venue {
    public String usage = "playing basketball and swimming";
    public String scale;
    public Gymnasium(int area, int floors, String location, String scale){
        super(area,floors,location);
        this.scale=scale;
        System.out.println("Creation of Gymnasium.");
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getUsage() {
        return usage;
    }
    public void setScale(){
        this.scale=scale;
    }
}
