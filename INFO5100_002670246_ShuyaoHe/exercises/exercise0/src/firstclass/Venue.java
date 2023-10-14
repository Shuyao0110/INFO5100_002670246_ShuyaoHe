package firstclass;



public class Venue extends Building {
    public String wallColor;
    public int capacity;
    public String usage;

    public Venue(int area, int floors, String location) {
        super(area, floors, location);
        System.out.println("Creation of Venue.");
    }

    public Venue(int area,String usage,int capacity){
        super();
        System.out.println("Creation of Venue.");
        this.capacity=capacity;
        this.usage=usage;
    }
}
